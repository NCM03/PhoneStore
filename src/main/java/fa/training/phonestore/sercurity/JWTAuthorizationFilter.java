    package fa.training.phonestore.sercurity;

    import com.auth0.jwt.JWT;
    import com.auth0.jwt.algorithms.Algorithm;
    import com.auth0.jwt.exceptions.JWTVerificationException;
    import com.auth0.jwt.interfaces.DecodedJWT;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.Cookie;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;
    import java.util.Collections;
    import java.util.List;

    public class JWTAuthorizationFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = extractToken(request);

            if (token != null) {
                try {
                    Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.SECRET_KEY);
                    DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
                    String role = jwt.getClaim("role").asString();

                    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (JWTVerificationException exception) {
                    SecurityContextHolder.clearContext();
                }
            }

            filterChain.doFilter(request, response);
        }

        private String extractToken(HttpServletRequest request) {
            // Kiểm tra token trong header
            String header = request.getHeader(SecurityConstraints.AUTHORIZATION);
            if (header != null && header.startsWith(SecurityConstraints.BEARER)) {
                return header;
            }

            // Kiểm tra token trong session
            HttpSession session = request.getSession(false);
            if (session != null) {
                Object tokenObj = session.getAttribute("jwtToken");
                if (tokenObj != null) {
                    return tokenObj.toString();
                }
            }

            // Kiểm tra token trong cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("remember-me-token".equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }

            return null;
        }
    }