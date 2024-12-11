package com.plantify.item.service.item;

import com.plantify.item.domain.dto.request.ItemPurchaseRequest;
import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;
import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.domain.entity.Category;

import java.util.List;

public interface ItemService {

    List<ItemResponse> getAllItems();
    List<ItemResponse> getItemsByCategory(Category category);
    List<MyItemResponse> purchaseItem(ItemPurchaseRequest request);
    ItemResponse addItem(ItemRequest request);
    ItemResponse updateItem(Long itemId, ItemRequest request);
    void deleteItem(Long itemId);
}
