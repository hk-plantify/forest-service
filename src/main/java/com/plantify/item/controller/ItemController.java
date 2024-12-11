package com.plantify.item.controller;

import com.plantify.item.domain.dto.request.ItemPurchaseRequest;
import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;
import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.domain.entity.Category;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ItemController {

    private final ItemService itemService;

    // 누구나
    @GetMapping("/items")
    public ApiResponse<List<ItemResponse>> getAllItems() {
        List<ItemResponse> allItems = itemService.getAllItems();
        return ApiResponse.ok(allItems);
    }

    // 누구나
    @GetMapping("/items/{category}")
    public ApiResponse<List<ItemResponse>> getItemsByCategory(@PathVariable Category category) {
        List<ItemResponse> items = itemService.getItemsByCategory(category);
        return ApiResponse.ok(items);
    }

    // 유저: 아이템 구매
    @PostMapping("/items/purchase")
    public ApiResponse<List<MyItemResponse>> purchaseItem(@RequestBody ItemPurchaseRequest request) {
        List<MyItemResponse> response = itemService.purchaseItem(request);
        return ApiResponse.ok(response);
    }

    @PostMapping("/admin/items")
    public ApiResponse<ItemResponse> createItem(@RequestBody ItemRequest request) {
        ItemResponse response = itemService.addItem(request);
        return ApiResponse.ok(response);
    }

    @PutMapping("/admin/items/{itemId}")
    public ApiResponse<ItemResponse> updateItem( @PathVariable Long itemId, @RequestBody ItemRequest request) {
        ItemResponse response = itemService.updateItem(itemId, request);
        return ApiResponse.ok(response);
    }

    @DeleteMapping("/admin/items/{itemId}")
    public ApiResponse<Void> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ApiResponse.ok();
    }
}
