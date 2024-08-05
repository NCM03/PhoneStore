package fa.training.phonestore.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import fa.training.phonestore.entity.Account;
import fa.training.phonestore.entity.Role;
import fa.training.phonestore.sercurity.SecurityConstraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class JwtUtils {

    public Account decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token.replace(SecurityConstraints.BEARER, ""));
            String jsonAccount = jwt.getSubject();




            // Nếu bạn lưu thêm thông tin nào khác trong token, bạn có thể lấy ra ở đây
            // Ví dụ:
            // String email = jwt.getClaim("email").asString();
            // account.setEmail(email);

            return parseAccountString(jsonAccount);
        } catch (JWTVerificationException exception) {
            // Token không hợp lệ hoặc đã hết hạn
            return null;
        }
    }
    public static Account parseAccountString(String accountString) {
        Account account = new Account();

        // Biểu thức chính quy để phân tích chuỗi
        Pattern pattern = Pattern.compile(
                "Account\\(accountId=(\\d+), username=([^,]+), password=([^,]+), role=Role\\(RoleId=(\\d+), roleName=([^\\)]+)\\), isActive=(true|false)\\)"
        );
        Matcher matcher = pattern.matcher(accountString);

        if (matcher.find()) {
            // Gán giá trị cho đối tượng Account từ các nhóm của biểu thức chính quy
            account.setAccountId(Integer.parseInt(matcher.group(1)));
            account.setUsername(matcher.group(2));
            account.setPassword(matcher.group(3));

            // Tạo đối tượng Role và gán các giá trị
            Role role = new Role();
            role.setRoleId(Integer.parseInt(matcher.group(4)));
            role.setRoleName(matcher.group(5));
            account.setRole(role);

            // Gán giá trị cho thuộc tính isActive
            account.setActive(Boolean.parseBoolean(matcher.group(6)));
        }

        return account;
    }
}
