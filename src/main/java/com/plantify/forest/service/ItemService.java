package com.plantify.forest.service;

import com.plantify.forest.domain.dto.request.ItemRequest;
import com.plantify.forest.domain.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {

    List<ItemResponse> getAllItems();
    ItemResponse addItem(String authorizationHeader, ItemRequest request);
    ItemResponse updateItem(String authorizationHeader, Long itemId, ItemRequest request);
    void deleteItem(String authorizationHeader, Long itemId);
}
