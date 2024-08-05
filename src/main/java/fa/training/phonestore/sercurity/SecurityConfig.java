package fa.training.phonestore.sercurity;

import fa.training.phonestore.service.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    CustomAuthenticationManager customAuthenticationManager;
    CustomUserDetailService customerUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeRequests()
                .requestMatchers("/Account/Admin", "/Account/take-Activities", "/Account/GetAllAccount").hasAuthority("admin")

                .requestMatchers("/Login", "/Logout", "/ValidAuthenticate"
                        , "/Account/checkUsername", "/Account/Register"
                        , "/getPassword", "forgotpassword", "/GetBackPass", "/Account/reset-password", "/**").permitAll()
                .requestMatchers("/Account/getAll", "/Account/ChangePassword", "/Customer/Profile").hasAuthority("customer")

                .anyRequest().authenticated()


                .and()

                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/AccessDenied");
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/Login");
                })

                .and()

                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)

                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key("remember-me-key")
                .tokenValiditySeconds((int) SecurityConstraints.REMEMBER_ME_EXPIRATION / 1000)
        ;

        return http.build();
    }


}