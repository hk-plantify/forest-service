package com.plantify.forest.domain.dto.response;


import com.plantify.forest.domain.entity.Category;
import com.plantify.forest.domain.entity.Item;

import java.time.LocalDateTime;

public record ItemResponse(
        Long itemId,
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
                item.getName(),
                item.getPrice(),
                item.getImageUri(),
                item.getCategory(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
