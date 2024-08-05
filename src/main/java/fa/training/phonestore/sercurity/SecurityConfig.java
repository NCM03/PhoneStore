    package fa.training.phonestore.sercurity;

    import fa.training.phonestore.service.CustomUserDetailService;
    import lombok.AllArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;

    @Configuration
    @AllArgsConstructor
    @EnableWebSecurity
    public class SecurityConfig {
        CustomAuthenticationManager customAuthenticationManager;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
            authenticationFilter.setFilterProcessesUrl("/authenticate");

            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeRequests()
                    .requestMatchers( "/Account/Admin","/Account/take-Activities","/Account/GetAllAccount","/Account/GetDetails").hasAuthority("admin")

                    .requestMatchers("/Login", "/Logout", "/ValidAuthenticate"
                                ,"/Account/checkUsername","/Account/Register"
                                ,"/getPassword","forgotpassword","/GetBackPass","/Account/reset-password", "/**","/uploads").permitAll()
                    .requestMatchers("/Account/ChangePassword","/Customer/Profile","/Customer/Application","/Customer/Request").hasAuthority("customer")
                    .requestMatchers("/Employee/Home","/Employee/RequestDetail").hasAuthority("employee")
                    .anyRequest().authenticated()


                    .and()

                    .exceptionHandling()
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.sendRedirect("/AccessDenied");
                    })
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.sendRedirect("/ValidAuthenticate");
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