package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.UsingItem;

public record UsingItemResponse(
        Long usingItemId,
        Long myItemId,
        Double posX,
        Double posY
) {

    public static UsingItemResponse from(UsingItem usingItem) {
        return new UsingItemResponse(
                usingItem.getUsingItemId(),
                usingItem.getMyItem().getMyItemId(),
                usingItem.getPosX(),
                usingItem.getPosY()
        );
    }
}
