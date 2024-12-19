package com.plantify.item.domain.dto;

import com.plantify.item.domain.entity.UsingItem;

import java.time.LocalDateTime;

public record UsingItemOutput(
        Long id,
        Long myItemId,
        Long userId,
        String imageUri,
        Double posX,
        Double posY,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UsingItemOutput from(UsingItem usingItem) {
        return new UsingItemOutput(
                usingItem.getUsingItemId(),
                usingItem.getMyItem().getMyItemId(),
                usingItem.getMyItem().getUserId(),
                usingItem.getMyItem().getItem().getImageUri(),
                usingItem.getPosX(),
                usingItem.getPosY(),
                usingItem.getMyItem().getItem().getCategory().name(),
                usingItem.getCreatedAt(),
                usingItem.getUpdatedAt()
        );
    }
}
