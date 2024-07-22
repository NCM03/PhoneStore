package fa.training.phonestore.sercurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
       HttpSession session = request.getSession();
       if(session.getAttribute("token") == null){
           filterChain.doFilter(request, response);
           return;}
        String header = session.getAttribute("token").toString();
        if (header == null || !header.startsWith(SecurityConstraints.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = header.replace(SecurityConstraints.BEARER, "");
            Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.SECRET_KEY);
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
            String role = jwt.getClaim("role").asString();

            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
            Authentication authentication = new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JWTVerificationException exception) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}