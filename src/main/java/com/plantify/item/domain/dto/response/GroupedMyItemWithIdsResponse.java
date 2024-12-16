package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.MyItem;

public record GroupedMyItemWithIdsResponse(
        Long myItemId,
        Long itemId,
        String itemName,
        String imageUri,
        Category category,
        Long quantity
) {
    public static GroupedMyItemWithIdsResponse  from(MyItem myItem) {
        return new GroupedMyItemWithIdsResponse(
                myItem.getMyItemId(),
                myItem.getMyItemId(),
                myItem.getItem().getName(),
                myItem.getItem().getImageUri(),
                myItem.getItem().getCategory(),
                myItem.getQuantity()
        );
    }
}

