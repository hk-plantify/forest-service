package com.plantify.item.controller;

import com.plantify.item.domain.dto.request.UsingItemAdminRequest;
import com.plantify.item.domain.dto.response.UsingItemAdminResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.usingItem.UsingItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/items/my-items/using-items")
public class UsingItemController {

    private final UsingItemService usingItemService;

    // 모든 사용중 아이템 조회
    @GetMapping
    public ApiResponse<List<UsingItemAdminResponse>> getAllUsingItems() {
        List<UsingItemAdminResponse> usingItems = usingItemService.getAllUsingItems();
        return ApiResponse.ok(usingItems);
    }

    // 사용중 아이템 수정
    @PutMapping("/{usingItemId}")
    public ApiResponse<UsingItemAdminResponse> updateUsingItemPos(
            @PathVariable Long usingItemId, @RequestBody UsingItemAdminRequest request) {
        UsingItemAdminResponse response = usingItemService.updateUsingItemPos(usingItemId, request);
        return ApiResponse.ok(response);
    }

    // 사용중 아이템 삭제
    @DeleteMapping("/{usingItemId}")
    public ApiResponse<Void> deleteUsingItem(@PathVariable Long usingItemId) {
        usingItemService.deleteUsingItem(usingItemId);
        return ApiResponse.ok();
    }

    // 특정 사용자 모든 사용중 아이템 조회
    @GetMapping("/users/{userId}")
    public ApiResponse<List<UsingItemAdminResponse>> getAllUsingItemsByUserId(@PathVariable Long userId) {
        List<UsingItemAdminResponse> usingItems = usingItemService.getAllUsingItemsByUserId(userId);
        return ApiResponse.ok(usingItems);
    }
}
