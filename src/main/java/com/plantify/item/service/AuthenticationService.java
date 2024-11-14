package com.plantify.item.service;

public interface AuthenticationService {

    boolean validateAdminRole(String authorizationHeader);
    void validateOwnership(String authorizationHeader, Long ownerId);
    Long getKakaoId(String authorizationHeader);
    String getRole(String authorizationHeader);
}
