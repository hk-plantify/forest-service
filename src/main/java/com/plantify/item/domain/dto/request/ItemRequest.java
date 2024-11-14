package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Category;
import com.plantify.item.domain.entity.Item;

public record ItemRequest(
        String name,
        Long price,
        String imageUri,
        Category category
) {

    public Item toEntity(Long kakaoId) {
        return Item.builder()
                .name(name)
                .userId(kakaoId)
                .price(price)
                .imageUri(imageUri)
                .category(category)
                .build();
    }
}
