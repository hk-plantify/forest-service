package com.plantify.item.controller.myItem;

import com.plantify.item.domain.dto.request.MyItemUserRequest;
import com.plantify.item.domain.dto.response.MyItemUserResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.myItem.MyItemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/my-items")
public class MyItemUserController {

    private final MyItemUserService myItemUserService;

    // 자신의 모든 사용중 내 아이템
    @GetMapping
    public ResponseEntity<ApiResponse<List<MyItemUserResponse>>> getMyItemsByUser() {
        List<MyItemUserResponse> myItems = myItemUserService.getMyItemsByUser();
        return ResponseEntity.ok(ApiResponse.ok(myItems));
    }

    // 내 아이템 생성
    @PostMapping
    public ResponseEntity<ApiResponse<MyItemUserResponse>> createMyItem(
            @RequestBody MyItemUserRequest request) {
        MyItemUserResponse response = myItemUserService.createMyItem(request);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
