package com.plantify.item.config;

import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.AuthErrorCode;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getCredentials() != null) {
                String token = String.valueOf(authentication.getCredentials());
                requestTemplate.header("Authorization", "Bearer " + token);
                log.info("Added Authorization header: Bearer {}", token);
            } else {
                log.warn("Authentication or credentials is null. Cannot add Authorization header.");
            }
        };
    }
}