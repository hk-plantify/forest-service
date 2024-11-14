package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.UsingItem;

public record UsingItemRequest(
        Long myItemId,
        Double posX,
        Double posY
) {

    public UsingItem toEntity(MyItem myItem) {
        return UsingItem.builder()
                .myItem(myItem)
                .posX(posX)
                .posY(posY)
                .build();
    }
}
