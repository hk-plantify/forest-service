package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.MyItem;

public record MyItemUserResponse(
        Long myItemId,
        Long itemId,
        String itemName,
        Long quantity)
{
    public static MyItemUserResponse from(MyItem myItem) {
        return new MyItemUserResponse(
                myItem.getMyItemId(),
                myItem.getItem().getItemId(),
                myItem.getItem().getName(),
                myItem.getQuantity()
        );
    }
}