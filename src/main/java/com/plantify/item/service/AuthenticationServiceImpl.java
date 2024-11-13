package com.plantify.item.service;

import com.plantify.item.controller.client.AuthServiceClient;
import com.plantify.item.domain.dto.response.UserResponse;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.plantify.item.global.exception.ApplicationException.createAuthException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthServiceClient authServiceClient;
    private final List<String> roles;

    @Override
    public void validateRole(String authorizationHeader) {
        UserResponse userInfo = getUserInfo(authorizationHeader);
        String role = userInfo.role();
        if (!roles.contains(role)) {
            throw new ApplicationException(ItemErrorCode.ITEM_ACCESS_DENIED);
        }
    }

    @Override
    public UserResponse getUserInfo(String authorizationHeader) {
        ApiResponse<UserResponse> response = authServiceClient.getUserInfo(authorizationHeader);
        if (response.getStatus() == HttpStatus.OK) {
            return response.getData();
        } else {
            throw createAuthException(response.getStatus());
        }
    }

}

