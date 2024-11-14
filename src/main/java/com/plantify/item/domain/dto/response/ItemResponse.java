package com.plantify.item.domain.dto.response;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;

import java.time.LocalDateTime;

public record ItemResponse(
        Long itemId,
        Long userId,
        String name,
        Long price,
        String imageUri,
        Category category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getItemId(),
                item.getUserId(),
                item.getName(),
                item.getPrice(),
                item.getImageUri(),
                item.getCategory(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
