package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.PurchaseItem;
import com.plantify.item.domain.entity.Status;

import java.time.LocalDateTime;

public record MyItemResponse(
        Long purchaseItemId,
        Long itemId,
        String itemName,
        Long price,
        String imageUri,
        Status status,
        LocalDateTime purchaseDate
) {

    public static MyItemResponse from(PurchaseItem purchaseItem) {
        return new MyItemResponse(
                purchaseItem.getPurchaseItemId(),
                purchaseItem.getItem().getItemId(),
                purchaseItem.getItem().getName(),
                purchaseItem.getItem().getPrice(),
                purchaseItem.getItem().getImageUri(),
                purchaseItem.getStatus(),
                purchaseItem.getPurchaseDate()
        );
    }
}