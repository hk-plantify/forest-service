package com.plantify.item.service;

import com.plantify.item.domain.dto.request.MyItemRequest;
import com.plantify.item.domain.dto.response.MyItemResponse;

import java.util.List;

public interface MyItemService {

    List<MyItemResponse> getAllMyItems();
    MyItemResponse addMyItem(MyItemRequest request);
    MyItemResponse updateMyItem(Long myItemId, MyItemRequest request);
    void deleteItem(Long myItemId);
}
