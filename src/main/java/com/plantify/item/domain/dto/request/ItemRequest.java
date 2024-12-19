package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;

public record ItemRequest(
        String name,
        Long price,
        String imageUri,
        Category category
) {

    public Item toEntity(Long userId) {
        return Item.builder()
                .name(name)
                .userId(userId)
                .price(price)
                .imageUri(imageUri)
                .category(category)
                .build();
    }

    public Item updatedItem(Item item) {
        return item.toBuilder()
                .name(this.name())
                .price(this.price())
                .imageUri(this.imageUri())
                .category(this.category)
                .build();
    }
}