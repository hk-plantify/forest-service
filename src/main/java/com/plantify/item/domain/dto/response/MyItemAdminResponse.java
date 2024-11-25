package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.MyItem;

public record MyItemAdminResponse(
        Long myItemId,
        Long itemId,
        String itemName,
        Long userId)
{
    public static MyItemAdminResponse from(MyItem myItem) {
        return new MyItemAdminResponse(
                myItem.getMyItemId(),
                myItem.getItem().getItemId(),
                myItem.getItem().getName(),
                myItem.getUserId()
        );
    }
}