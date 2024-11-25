package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.MyItem;


public record MyItemUserRequest(
        Long itemId
) {
    public MyItem toEntity(Long userId, Item item) {
        return MyItem.builder()
                .item(item)
                .userId(userId)
                .build();
    }
}