package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.request.MyItemPurchaseRequest;
import com.plantify.item.domain.dto.request.MyItemUserRequest;
import com.plantify.item.domain.dto.response.MyItemUserResponse;

import java.util.List;

public interface MyItemUserService {

    List<MyItemUserResponse> getMyItemsByUser();
    MyItemUserResponse purchaseItem(MyItemPurchaseRequest request);
}
