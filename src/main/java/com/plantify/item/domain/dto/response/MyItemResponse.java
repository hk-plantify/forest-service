package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.MyItem;

public record MyItemResponse(
        Long myItemId,
        Long itemId,
        String itemName,
        String imageUri,
        Category category,
        Long quantity
) {
    public static MyItemResponse from(MyItem myItem) {
        return new MyItemResponse(
                myItem.getMyItemId(),
                myItem.getItem().getItemId(),
                myItem.getItem().getName(),
                myItem.getItem().getImageUri(),
                myItem.getItem().getCategory(),
                myItem.getQuantity()
        );
    }
}