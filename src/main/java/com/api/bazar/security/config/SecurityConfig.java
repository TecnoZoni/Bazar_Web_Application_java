package com.api.bazar.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                // Permisos para vistas públicas
                .requestMatchers(
                        "/",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api/**",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()
                // Permisos para el login
                .requestMatchers(
                        "/admin/login",
                        "/admin/login-error"
                ).permitAll()
                // Todas las rutas administrativas requieren rol ADMIN
                .requestMatchers(
                        "/cliente/**",
                        "/venta/**",
                        "/producto/**"
                ).hasRole("ADMIN")
                // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/admin/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/admin/login-error")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/admin/login?logout")
                .permitAll()
                )
                .exceptionHandling(exception -> exception
                .accessDeniedPage("/admin/access-denied") // Página para acceso denegado
                )
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .httpBasic(Customizer.withDefaults()) // Para la API
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
