package com.plantify.item.controller.myItem;

import com.plantify.item.domain.dto.response.MyItemAdminResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.myItem.MyItemAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/items/my-items")
public class MyItemAdminController {

    private final MyItemAdminService myItemAdminService;

    // 모든 내 아이템 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<MyItemAdminResponse>>> getAllMyItems() {
        List<MyItemAdminResponse> response = myItemAdminService.getAllMyItems();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    // 모든 내 아이템 삭제
    @DeleteMapping("/{myItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteMyItem(@PathVariable Long myItemId) {
        myItemAdminService.deleteMyItem(myItemId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    // 특정 사용자 모든 내 아이템
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<MyItemAdminResponse>>> getMyItemsByUserId(@PathVariable Long userId) {
        List<MyItemAdminResponse> response = myItemAdminService.getMyItemsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
