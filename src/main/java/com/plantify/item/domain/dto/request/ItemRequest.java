package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;

public record ItemRequest(
        String name,
        Long price,
        String imageUri,
        String category
) {

    public Item toEntity(Long userId) {
        return Item.builder()
                .name(name)
                .userId(userId)
                .price(price)
                .imageUri(imageUri)
                .category(toCategory())
                .build();
    }

    private Category toCategory() {
        return Category.valueOf(category.toUpperCase());
    }
}