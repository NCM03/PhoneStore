package fa.training.phonestore.sercurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import fa.training.phonestore.entity.Account;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private CustomAuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!requiresAuthentication(request, response)) {
            return null;
        }
        try {
            Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
    boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember-me"));
            Authentication auth = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse authentication request body", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember-me"));
        long expirationTime = rememberMe ? SecurityConstraints.REMEMBER_ME_EXPIRATION : SecurityConstraints.TOKEN_EXPIRATION;

        Account account = (Account) auth.getPrincipal();
        String token = JWT.create()
                .withSubject(auth.getName())
                .withClaim("role", account.getRole().getRoleName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET_KEY));

        if (rememberMe) {
            Cookie cookie = new Cookie("remember-me-token", token);
            cookie.setMaxAge((int) (expirationTime / 1000));
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        response.addHeader(SecurityConstraints.AUTHORIZATION, SecurityConstraints.BEARER + token);
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + SecurityConstraints.BEARER + token + "\", \"role\":\"" + account.getRole().getRoleName() + "\"}");
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}