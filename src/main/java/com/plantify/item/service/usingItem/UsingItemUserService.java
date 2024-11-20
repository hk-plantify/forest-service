package com.plantify.item.service.usingItem;

import com.plantify.item.domain.dto.request.UsingItemUserRequest;
import com.plantify.item.domain.dto.response.UsingItemUserResponse;

import java.util.List;

public interface UsingItemUserService {

    List<UsingItemUserResponse> getAllUsingItemsByUser();
    UsingItemUserResponse createUsingItem(UsingItemUserRequest request);
    UsingItemUserResponse updateUsingItemPos(Long usingItemId, UsingItemUserRequest request);
    void deleteUsingItem(Long usingItemId);
}
