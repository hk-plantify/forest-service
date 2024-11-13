package com.plantify.item.service;

import com.plantify.item.domain.dto.response.UserResponse;

public interface AuthenticationService {

    void validateRole(String authorizationHeader);
    UserResponse getUserInfo(String authorizationHeader);
}
