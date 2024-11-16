package com.plantify.item.service;

public interface AuthenticationService {

    boolean validateAdminRole();
    void validateOwnership(Long ownerId);
    Long getUserId();
}
