package com.plantify.forest.domain.dto.request;

import com.plantify.forest.domain.entity.Category;
import com.plantify.forest.domain.entity.Item;

public record ItemRequest(
        String name,
        Long price,
        String imageUri,
        Category category
) {

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .imageUri(imageUri)
                .category(category)
                .build();
    }
}
