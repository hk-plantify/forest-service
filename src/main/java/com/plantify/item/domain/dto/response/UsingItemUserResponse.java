package com.plantify.item.domain.dto.response;


import com.plantify.item.domain.entity.UsingItem;

public record UsingItemUserResponse(
        Long usingItemId,
        Long myItemId,
        Double posX,
        Double posY
) {

    public static UsingItemUserResponse from(UsingItem usingItem) {
        return new UsingItemUserResponse(
                usingItem.getUsingItemId(),
                usingItem.getMyItem().getMyItemId(),
                usingItem.getPosX(),
                usingItem.getPosY()
        );
    }
}
