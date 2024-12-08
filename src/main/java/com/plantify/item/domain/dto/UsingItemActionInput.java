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
                .posX(posX)
                .posY(posY)
                .build();
    }

    public UsingItem UpdateUsingItem(UsingItem usingItem) {
        return usingItem.toBuilder()
                .posX(posX)
                .posY(posY)
                .build();
    }
}


