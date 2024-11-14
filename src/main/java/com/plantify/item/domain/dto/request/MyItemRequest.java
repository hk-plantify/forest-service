package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.Status;

public record MyItemRequest(Long itemId, Status status) {

    public MyItem toEntity(Long kakaoId, Item item) {
        return MyItem.builder()
                .userId(kakaoId)
                .item(item)
                .status(status)
                .build();
    }
}
