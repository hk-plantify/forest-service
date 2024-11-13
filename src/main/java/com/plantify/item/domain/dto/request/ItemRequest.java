package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;

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
