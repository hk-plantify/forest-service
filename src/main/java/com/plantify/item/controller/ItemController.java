package com.plantify.item.controller;

import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getAllItems() {
        List<ItemResponse> allItems = itemService.getAllItems();
        return ResponseEntity.ok(ApiResponse.ok(allItems));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemResponse>> createItem(
            @RequestHeader("Authorization") String authorizationHeader, @RequestBody ItemRequest request) {
        ItemResponse response = itemService.addItem(authorizationHeader, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ApiResponse<ItemResponse>> updateItem(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Long itemId, @RequestBody ItemRequest request) {
        ItemResponse response = itemService.updateItem(authorizationHeader, itemId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(
            @RequestHeader("Authorization") String authorizationHeader, @PathVariable Long itemId) {
        itemService.deleteItem(authorizationHeader, itemId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
