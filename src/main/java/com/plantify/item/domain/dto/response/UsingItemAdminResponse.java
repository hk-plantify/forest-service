package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.UsingItem;

import java.time.LocalDateTime;

public record UsingItemAdminResponse(
        Long usingItemId,
        Long myItemId,
        Long userId,
        Double posX,
        Double posY,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UsingItemAdminResponse from(UsingItem usingItem) {
        return new UsingItemAdminResponse(
                usingItem.getUsingItemId(),
                usingItem.getMyItem().getMyItemId(),
                usingItem.getMyItem().getUserId(),
                usingItem.getPosX(),
                usingItem.getPosY(),
                usingItem.getCreatedAt(),
                usingItem.getUpdatedAt()
        );
    }
}