package com.plantify.item.controller.usingItem;

import com.plantify.item.domain.dto.request.UsingItemAdminRequest;
import com.plantify.item.domain.dto.response.UsingItemAdminResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.usingItem.UsingItemAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/items/my-items/using-items")
public class UsingItemAdminController {

    private final UsingItemAdminService usingItemAdminService;

    // 모든 사용중 아이템 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsingItemAdminResponse>>> getAllUsingItems() {
        List<UsingItemAdminResponse> usingItems = usingItemAdminService.getAllUsingItems();
        return ResponseEntity.ok(ApiResponse.ok(usingItems));
    }

    // 사용중 아이템 수정
    @PutMapping("/{usingItemId}")
    public ResponseEntity<ApiResponse<UsingItemAdminResponse>> updateUsingItemPos(
            @PathVariable Long usingItemId, @RequestBody UsingItemAdminRequest request) {
        UsingItemAdminResponse response = usingItemAdminService.updateUsingItemPos(usingItemId, request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 사용중 아이템 삭제
    @DeleteMapping("/{usingItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteUsingItem(@PathVariable Long usingItemId) {
        usingItemAdminService.deleteUsingItem(usingItemId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    // 특정 사용자 모든 사용중 아이템 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<UsingItemAdminResponse>>> getAllUsingItemsByUserId(@PathVariable Long userId) {
        List<UsingItemAdminResponse> usingItems = usingItemAdminService.getAllUsingItemsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok(usingItems));
    }
}
