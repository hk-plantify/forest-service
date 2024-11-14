package com.plantify.item.controller;

import com.plantify.item.domain.dto.request.UsingItemRequest;
import com.plantify.item.domain.dto.response.UsingItemResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.UsingItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/my-items/using")
public class UsingItemController {

    private final UsingItemService usingItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsingItemResponse>>> getAllUsingItems(
            @RequestHeader("Authorization") String authorizationHeader) {
        List<UsingItemResponse> allUsingItems = usingItemService.getAllUsingItems(authorizationHeader);
        return ResponseEntity.ok(ApiResponse.ok(allUsingItems));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsingItemResponse>> createUsingItem(
            @RequestHeader("Authorization") String authorizationHeader, @RequestBody UsingItemRequest request) {
        UsingItemResponse response = usingItemService.addUsingItem(authorizationHeader, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PutMapping("/{usingItemId}")
    public ResponseEntity<ApiResponse<UsingItemResponse>> updateUsingItem(
            @RequestHeader("Authorization") String authorizationHeader, Long usingItemId, @RequestBody UsingItemRequest request) {
        UsingItemResponse response = usingItemService.updateUsingItem(authorizationHeader, usingItemId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/{usingItemId}")
    public ResponseEntity<ApiResponse<UsingItemResponse>> deleteUsingItem(
        @RequestHeader("Authorization") String authorizationHeader, @PathVariable Long usingItemId) {
        usingItemService.deleteUsingItem(authorizationHeader, usingItemId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}

