package com.plantify.item.service.usingItem;

import com.plantify.item.domain.dto.request.UsingItemAdminRequest;
import com.plantify.item.domain.dto.response.UsingItemAdminResponse;

import java.util.List;

public interface UsingItemService {

    List<UsingItemAdminResponse> getAllUsingItems();
    UsingItemAdminResponse updateUsingItemPos(Long usingItemId, UsingItemAdminRequest request);
    void deleteUsingItem(Long usingItemId);
    List<UsingItemAdminResponse> getAllUsingItemsByUserId(Long userId);
}
