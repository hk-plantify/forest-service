package com.plantify.item.config;

import com.plantify.item.client.AuthServiceClient;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.AuthErrorCode;
import com.plantify.item.util.UserInfoProvider;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == null) {
                throw new ApplicationException(AuthErrorCode.INVALID_TOKEN);
            }
            String token = authentication.getCredentials().toString();
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
}
