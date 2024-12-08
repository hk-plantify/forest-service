package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.response.MyItemResponse;

import java.util.List;

public interface MyItemService {

    List<MyItemResponse> getMyItemsByUser();
    List<MyItemResponse> getAllMyItems();
    void deleteMyItem(Long myItemId);
    List<MyItemResponse> getMyItemsByUserId(Long userId);
}
