package com.plantify.item.service;

import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {

    List<ItemResponse> getAllItems();
    ItemResponse addItem(ItemRequest request);
    ItemResponse updateItem(Long itemId, ItemRequest request);
    void deleteItem(Long itemId);
}
