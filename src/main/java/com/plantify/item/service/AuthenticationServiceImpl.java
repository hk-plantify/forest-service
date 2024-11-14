package com.plantify.item.service;

import com.plantify.item.client.UserInfoProvider;
import com.plantify.item.global.exception.ApplicationException;
import com.plantify.item.global.exception.errorcode.ItemErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserInfoProvider userInfoProvider;
    private final List<String> adminRoles;

    @Override
    public boolean validateAdminRole(String authorizationHeader) {
        String role = getRole(authorizationHeader);
        if (adminRoles.contains(role)) {
            return true;
        }
        throw new ApplicationException(ItemErrorCode.ITEM_ACCESS_DENIED);
    }

    @Override
    public void validateOwnership(String authorizationHeader, Long ownerId) {
        Long kakaoId = getKakaoId(authorizationHeader);
        if (!kakaoId.equals(ownerId)) {
            throw new ApplicationException(ItemErrorCode.ITEM_ACCESS_DENIED);
        }
    }

    @Override
    public Long getKakaoId(String authorizationHeader) {
        return userInfoProvider.getUserInfo(authorizationHeader).kakaoId();
    }

    @Override
    public String getRole(String authorizationHeader) {
        return userInfoProvider.getUserInfo(authorizationHeader).role();
    }
}