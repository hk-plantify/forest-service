package com.plantify.forest.controller.client;

import com.plantify.forest.domain.dto.response.UserResponse;
import com.plantify.forest.global.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${auth.service.url}")
public interface AuthServiceClient {

    @PostMapping("/v1/auth/validate-token")
    ApiResponse<UserResponse> getUserInfo(@RequestHeader("Authorization") String authorizationHeader);
}
