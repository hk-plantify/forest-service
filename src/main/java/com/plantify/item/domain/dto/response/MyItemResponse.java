package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.MyItem;
import com.plantify.item.domain.entity.Status;

import java.time.LocalDateTime;

public record MyItemResponse(
        Long myItemId,
        Long itemId,
        String itemName,
        Long price,
        String imageUri,
        Status status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static MyItemResponse from(MyItem myItem) {
        return new MyItemResponse(
                myItem.getMyItemId(),
                myItem.getItem().getItemId(),
                myItem.getItem().getName(),
                myItem.getItem().getPrice(),
                myItem.getItem().getImageUri(),
                myItem.getStatus(),
                myItem.getCreatedAt(),
                myItem.getUpdatedAt()
        );
    }
}