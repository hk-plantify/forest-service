package com.plantify.item.service;

import com.plantify.item.domain.dto.request.MyItemRequest;
import com.plantify.item.domain.dto.response.MyItemResponse;

import java.util.List;

public interface MyItemService {

    List<MyItemResponse> getAllMyItems(String authorizationHeader);
    MyItemResponse addMyItem(String authorizationHeader, MyItemRequest myItemRequest);
    MyItemResponse updateMyItem(String authorizationHeader, Long myItemId, MyItemRequest request);
    void deleteItem(String authorizationHeader, Long myItemId);
}
