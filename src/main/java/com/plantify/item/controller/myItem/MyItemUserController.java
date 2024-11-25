package com.plantify.item.controller.myItem;

import com.plantify.item.domain.dto.request.MyItemPurchaseRequest;
import com.plantify.item.domain.dto.response.MyItemUserResponse;
import com.plantify.item.global.response.ApiResponse;
import com.plantify.item.service.myItem.MyItemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/items/my-items")
public class MyItemUserController {

    private final MyItemUserService myItemUserService;

    // 자신의 모든 내 아이템 조회
    @GetMapping
    public ApiResponse<List<MyItemUserResponse>> getMyItemsByUser() {
        List<MyItemUserResponse> myItems = myItemUserService.getMyItemsByUser();
        return ApiResponse.ok(myItems);
    }

    // 내 아이템 생성
    @PostMapping("/purchase")
    public ApiResponse<MyItemUserResponse> purchaseItem(
            @RequestBody MyItemPurchaseRequest request) {
        MyItemUserResponse response = myItemUserService.purchaseItem(request);
        return ApiResponse.ok(response);
    }
}
