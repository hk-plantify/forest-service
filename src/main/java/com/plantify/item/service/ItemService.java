package com.plantify.item.service;

import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {

    List<ItemResponse> getAllItems();
    ItemResponse addItem(String authorizationHeader, ItemRequest request);
    ItemResponse updateItem(String authorizationHeader, Long itemId, ItemRequest request);
    void deleteItem(String authorizationHeader, Long itemId);
}
