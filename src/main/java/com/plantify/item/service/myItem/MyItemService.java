package com.plantify.item.service.myItem;

import com.plantify.item.domain.dto.response.GroupedMyItemWithIdsResponse;
import com.plantify.item.domain.dto.response.MyItemResponse;
import com.plantify.item.domain.entity.Category;

import java.util.List;

public interface MyItemService {

    List<GroupedMyItemWithIdsResponse> getMyItemsByUser(Category category);
    List<MyItemResponse> getAllMyItems();
    void deleteMyItem(Long myItemId);
    List<MyItemResponse> getMyItemsByUserId(Long userId);
}
