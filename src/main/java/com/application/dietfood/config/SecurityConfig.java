package com.application.dietfood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()  // Enable CORS
            .csrf().disable()  // Disable CSRF for testing purposes; re-enable in production with proper configuration
            .authorizeRequests()
            .requestMatchers("/auth/**").permitAll()  // Allow all requests to /auth/** without authentication
            .requestMatchers("/diet/**").permitAll()
            .requestMatchers("/nutrition/**").permitAll()
            .anyRequest().authenticated();  // All other requests require authentication
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200");  // Allow requests from 
        config.addAllowedMethod("*");  // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        config.addAllowedHeader("*");  // Allow all headers (e.g., Content-Type, Authorization)
        config.setAllowCredentials(true);  // Allow credentials (e.g., cookies, authorization headers)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // Apply this CORS configuration to all endpoints
        return new CorsFilter(source);
    }
}
