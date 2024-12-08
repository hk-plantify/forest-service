package com.plantify.item.domain.dto;

import com.plantify.item.domain.entity.UsingItem;

import java.time.LocalDateTime;

public record UsingItemOutput(
        Long id,
        Long myItemId,
        Double posX,
        Double posY,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UsingItemOutput from(UsingItem usingItem) {
        return new UsingItemOutput(
                usingItem.getUsingItemId(),
                usingItem.getMyItem().getMyItemId(),
                usingItem.getPosX(),
                usingItem.getPosY(),
                usingItem.getCreatedAt(),
                usingItem.getUpdatedAt()
        );
    }
}
