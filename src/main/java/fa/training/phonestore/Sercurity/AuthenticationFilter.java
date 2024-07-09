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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private CustomAuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {

            Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);

            Authentication auth = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
            return authenticationManager.authenticate(auth);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        Account account = (Account) auth.getPrincipal();
        String token = JWT.create()
                .withSubject(auth.getName())
                .withClaim("role", account.getRole().getRoleName())  // Thêm role vào token
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET_KEY));
        response.addHeader(SecurityConstraints.AUTHORIZATION, SecurityConstraints.BEARER + token);
    }

    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}