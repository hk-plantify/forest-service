package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.PurchaseItem;
import com.plantify.item.domain.entity.Status;

public record MyItemRequest(Status status) {

    public static PurchaseItem toEntity(Long kakaoId, Item item) {
        return PurchaseItem.builder()
                .userId(kakaoId)
                .item(item)
                .status(status)
                .build();
    }
}
