package com.plantify.forest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET, "/v1/items")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST, "/v1/items/**")
                        .hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/items/**")
                        .hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/items/**")
                        .hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/items/**")
                        .hasAnyRole("MANAGER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/v1/myItems")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/myItems/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/myItems/**")
                        .hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/myItems/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/v1/myItem/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/v1/myItem/using")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/v1/myItem/using/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/myItem/using/**")
                        .hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/myItem/using/**")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")

                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
