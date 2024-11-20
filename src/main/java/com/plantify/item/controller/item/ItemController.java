package com.plantify.item.controller.item;

import com.plantify.item.domain.dto.request.ItemRequest;
import com.plantify.item.domain.dto.response.ItemResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.item.ItemService;
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
    public ResponseEntity<ApiResponse<ItemResponse>> createItem(@RequestBody ItemRequest request) {
        ItemResponse response = itemService.addItem(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ApiResponse<ItemResponse>> updateItem( @PathVariable Long itemId, @RequestBody ItemRequest request) {
        ItemResponse response = itemService.updateItem(itemId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
