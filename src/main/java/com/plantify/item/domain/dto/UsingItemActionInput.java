package com.plantify.item.domain.dto;

import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.UsingItem;

public record UsingItemActionInput(
        String action,
        Long usingItemId,
        Long myItemId,
        Double posX,
        Double posY
) {

    public UsingItem CreateUsingItem(MyItem myItem) {
        return UsingItem.builder()
                .myItem(myItem)
                .posX(posX != null ? posX : 0.0)
                .posY(posY != null ? posY : 0.0)
                .build();
    }

    public UsingItem UpdateUsingItem(UsingItem usingItem) {
        return usingItem.toBuilder()
                .posX(posX != null ? posX : usingItem.getPosX())
                .posY(posY != null ? posY : usingItem.getPosY())
                .build();
    }
}


