package fa.training.phonestore.Utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import fa.training.phonestore.Entity.Account;
import fa.training.phonestore.Entity.Role;
import fa.training.phonestore.Sercurity.SecurityConstraints;


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
    private static Account parseAccountString(String accountString) {
        Account account = new Account();
        Role role = new Role();

        // Loại bỏ "Account(" ở đầu và ")" ở cuối
        String trimmed = accountString.substring(8, accountString.length() - 1);

        // Tách các trường
        String[] fields = trimmed.split(", ");

        for (String field : fields) {
            String[] keyValue = field.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];

                switch (key) {
                    case "accountId":
                        account.setAccountId(Integer.parseInt(value));
                        break;
                    case "username":
                        account.setUsername(value);
                        break;
                    case "password":
                        account.setPassword(value);
                        break;
                    case "isActive":
                        account.setActive(Boolean.parseBoolean(value));
                        break;
                    case "role":
                        // Xử lý trường role
                        String roleTrimmed = value.substring(5, value.length() - 1); // Loại bỏ "Role(" và ")"
                        String[] roleFields = roleTrimmed.split(", ");
                        for (String roleField : roleFields) {
                            String[] roleKeyValue = roleField.split("=");
                            if (roleKeyValue.length == 2) {
                                if ("RoleId".equals(roleKeyValue[0])) {
                                    role.setRoleId(Integer.parseInt(roleKeyValue[1]));
                                } else if ("roleName".equals(roleKeyValue[0])) {
                                    role.setRoleName(roleKeyValue[1]);
                                }
                            }
                        }
                        account.setRole(role);
                        break;
                }
            }
        }

        return account;
    }
}
