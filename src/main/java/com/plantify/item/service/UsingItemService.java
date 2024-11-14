package com.plantify.item.service;

import com.plantify.item.domain.dto.request.UsingItemRequest;
import com.plantify.item.domain.dto.response.UsingItemResponse;

import java.util.List;

public interface UsingItemService {

    List<UsingItemResponse> getAllUsingItems(String authorizationHeader);
    UsingItemResponse addUsingItem(String authorizationHeader, UsingItemRequest request);
    UsingItemResponse updateUsingItem(String authorizationHeader, Long usingItemId, UsingItemRequest request);
    void deleteUsingItem(String authorizationHeader, Long usingItemId);
}
