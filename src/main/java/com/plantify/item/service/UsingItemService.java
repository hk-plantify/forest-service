package com.plantify.item.service;

import com.plantify.item.domain.dto.request.UsingItemRequest;
import com.plantify.item.domain.dto.response.UsingItemResponse;

import java.util.List;

public interface UsingItemService {

    List<UsingItemResponse> getAllUsingItems();
    UsingItemResponse addUsingItem(UsingItemRequest request);
    UsingItemResponse updateUsingItem(Long usingItemId, UsingItemRequest request);
    void deleteUsingItem(Long usingItemId);
}
