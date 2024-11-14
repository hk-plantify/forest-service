package com.plantify.item.client;

import com.plantify.item.domain.dto.response.UserResponse;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.AuthErrorCode;
import com.plantify.item.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoProvider {

    private final AuthServiceClient authServiceClient;

    public UserResponse getUserInfo(String authorizationHeader) {
        ApiResponse<UserResponse> response = authServiceClient.getUserInfo(authorizationHeader);
        if (response.getStatus() == HttpStatus.OK) {
            return response.getData();
        } else {
            throw switch (response.getStatus()) {
                case UNAUTHORIZED -> new ApplicationException(AuthErrorCode.INVALID_TOKEN);
                case FORBIDDEN -> new ApplicationException(AuthErrorCode.ACCESS_TOKEN_NULL);
                default -> new ApplicationException(AuthErrorCode.UNSUPPORTED_TOKEN);
            };
        }
    }
}