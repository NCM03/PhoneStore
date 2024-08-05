package fa.training.phonestore.helper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class HelperToken {
    public static String getToken(HttpServletRequest request){

        // Kiểm tra token trong cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("remember-me-token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        // Nếu không tìm thấy token
        return null;
    }
}
