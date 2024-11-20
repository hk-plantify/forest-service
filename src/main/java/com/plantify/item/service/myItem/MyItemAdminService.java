package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.response.MyItemAdminResponse;

import java.util.List;

public interface MyItemAdminService {


    List<MyItemAdminResponse> getAllMyItems();
    void deleteMyItem(Long myItemId);
    List<MyItemAdminResponse> getMyItemsByUserId(Long userId);
}
