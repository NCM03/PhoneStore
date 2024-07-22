package fa.training.phonestore.Sercurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import fa.training.phonestore.Entity.Account;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private CustomAuthenticationManager authenticationManager;

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();
        if (url.equals("/Login") || url.startsWith("/Login/")) {
            return false;
        }
        return super.requiresAuthentication(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!requiresAuthentication(request, response)) {
            return null;
        }
        try {
            Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
//            boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember-me"));
            Authentication auth = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse authentication request body", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());
//        boolean rememberMe = Boolean.parseBoolean(request.getParameter("remember-me"));
//        long expirationTime = rememberMe ? SecurityConstraints.REMEMBER_ME_EXPIRATION : SecurityConstraints.TOKEN_EXPIRATION;

        Account account = (Account) auth.getPrincipal();
        String token = JWT.create()
                .withSubject(auth.getName())
                .withClaim("role", account.getRole().getRoleName())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET_KEY));
        response.addHeader(SecurityConstraints.AUTHORIZATION, SecurityConstraints.BEARER + token);
        // Trả về token trong response body
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