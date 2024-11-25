package com.plantify.item.controller.usingItem;

import com.plantify.item.domain.dto.request.UsingItemUserRequest;
import com.plantify.item.domain.dto.response.UsingItemUserResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.usingItem.UsingItemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/my-items/using-items")
public class UsingItemUserController {

    private final UsingItemUserService usingItemUserService;

    // 자신의 모든 사용중 내 아이템 조회
    @GetMapping
    public ApiResponse<List<UsingItemUserResponse>> getAllUsingItemsByUser() {
        List<UsingItemUserResponse> usingItems = usingItemUserService.getAllUsingItemsByUser();
        return ApiResponse.ok(usingItems);
    }

    // 사용중 내 아이템 생성
    @PostMapping
    public ApiResponse<UsingItemUserResponse> createUsingItem(
            @RequestBody UsingItemUserRequest request) {
        UsingItemUserResponse response = usingItemUserService.createUsingItem(request);
        return ApiResponse.ok(response);
    }

    // 사용중 내 아이템 위치 변경
    @PutMapping("/{usingItemId}")
    public ApiResponse<UsingItemUserResponse> updateUsingItemPos(
            @PathVariable Long usingItemId, @RequestBody UsingItemUserRequest request) {
        UsingItemUserResponse response = usingItemUserService.updateUsingItemPos(usingItemId, request);
        return ApiResponse.ok(response);
    }

    // 사용중 내 아이템 삭제
    @DeleteMapping("/{usingItemId}")
    public ApiResponse<Void> deleteUsingItem(@PathVariable Long usingItemId) {
        usingItemUserService.deleteUsingItem(usingItemId);
        return ApiResponse.ok();
    }
}
