package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.UsingItem;

public record UsingItemAdminRequest(Double posX, Double posY) {

    public UsingItem toEntity(UsingItem usingItem) {
        return UsingItem.builder()
                .usingItemId(usingItem.getUsingItemId())
                .myItem(usingItem.getMyItem())
                .posX(posX)
                .posY(posY)
                .build();
    }
}
