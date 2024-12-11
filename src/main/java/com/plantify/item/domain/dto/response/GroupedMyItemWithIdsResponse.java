package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;

import java.util.List;

public record GroupedMyItemWithIdsResponse(
        Long itemId,
        String itemName,
        String imageUri,
        Category category,
        List<Long> myItemIds
) {
    public static GroupedMyItemWithIdsResponse from(Item item, List<Long> myItemIds) {
        return new GroupedMyItemWithIdsResponse(
                item.getItemId(),
                item.getName(),
                item.getImageUri(),
                item.getCategory(),
                myItemIds
        );
    }
}

