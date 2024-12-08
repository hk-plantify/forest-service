package com.plantify.item.controller;

import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.myItem.MyItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MyItemController {

    private final MyItemService myItemService;

    // 유저: 자신의 모든 내 아이템 조회
    @GetMapping("/v1/items/my-items")
    public ApiResponse<List<MyItemResponse>> getMyItemsByUser() {
        List<MyItemResponse> myItems = myItemService.getMyItemsByUser();
        return ApiResponse.ok(myItems);
    }

    // 모든 내 아이템 조회
    @GetMapping("/v1/admin/items/my-items")
    public ApiResponse<List<MyItemResponse>> getAllMyItems() {
        List<MyItemResponse> response = myItemService.getAllMyItems();
        return ApiResponse.ok(response);
    }

    // 모든 내 아이템 삭제
    @DeleteMapping("/v1/admin/items/my-items/{myItemId}")
    public ApiResponse<Void> deleteMyItem(@PathVariable Long myItemId) {
        myItemService.deleteMyItem(myItemId);
        return ApiResponse.ok();
    }

    // 특정 사용자 모든 내 아이템
    @GetMapping("/v1/admin/items/my-items/users/{userId}")
    public ApiResponse<List<MyItemResponse>> getMyItemsByUserId(@PathVariable Long userId) {
        List<MyItemResponse> response = myItemService.getMyItemsByUserId(userId);
        return ApiResponse.ok(response);
    }
}
