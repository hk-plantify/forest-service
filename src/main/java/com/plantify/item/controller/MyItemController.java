package com.plantify.item.controller;

import com.plantify.item.domain.dto.request.MyItemRequest;
import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.MyItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/my-items")
public class MyItemController {

    private final MyItemService myItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MyItemResponse>>> getAllMyItems() {
        List<MyItemResponse> allMyItems = myItemService.getAllMyItems();
        return ResponseEntity.ok(ApiResponse.ok(allMyItems));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MyItemResponse>> createMyItem(@RequestBody MyItemRequest request) {
        MyItemResponse response = myItemService.addMyItem(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/{myItemId}")
    public ResponseEntity<ApiResponse<MyItemResponse>> updateMyItem(@PathVariable Long myItemId, @RequestBody MyItemRequest request) {
        MyItemResponse response = myItemService.updateMyItem(myItemId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/{myItemId}")
    public ResponseEntity<ApiResponse<MyItemResponse>> deleteMyItem(@PathVariable Long myItemId) {
        myItemService.deleteItem(myItemId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
