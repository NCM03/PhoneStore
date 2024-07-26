package fa.training.phonestore.Helper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class HelperToken {
    public static String getToken(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object sessionToken = session.getAttribute("jwtToken");
            if (sessionToken != null) {
                return sessionToken.toString();
            }
        }

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
