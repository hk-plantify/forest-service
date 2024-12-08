package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.MyItem;

public record MyItemResponse(
        Long myItemId,
        Long itemId,
        String itemName,
        Long quantity,
        String category
) {
    public static MyItemResponse from(MyItem myItem) {
        return new MyItemResponse(
                myItem.getMyItemId(),
                myItem.getItem().getItemId(),
                myItem.getItem().getName(),
                myItem.getQuantity(),
                myItem.getItem().getCategory().name()
        );
    }
}