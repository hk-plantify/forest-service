package com.plantify.item.controller.myItem;

import com.plantify.item.domain.dto.response.MyItemAdminResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.myItem.MyItemAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/items/my-items")
public class MyItemAdminController {

    private final MyItemAdminService myItemAdminService;

    // 모든 내 아이템 조회
    @GetMapping
    public ApiResponse<List<MyItemAdminResponse>> getAllMyItems() {
        List<MyItemAdminResponse> response = myItemAdminService.getAllMyItems();
        return ApiResponse.ok(response);
    }

    // 모든 내 아이템 삭제
    @DeleteMapping("/{myItemId}")
    public ApiResponse<Void> deleteMyItem(@PathVariable Long myItemId) {
        myItemAdminService.deleteMyItem(myItemId);
        return ApiResponse.ok();
    }

    // 특정 사용자 모든 내 아이템
    @GetMapping("/users/{userId}")
    public ApiResponse<List<MyItemAdminResponse>> getMyItemsByUserId(@PathVariable Long userId) {
        List<MyItemAdminResponse> response = myItemAdminService.getMyItemsByUserId(userId);
        return ApiResponse.ok(response);
    }
}
