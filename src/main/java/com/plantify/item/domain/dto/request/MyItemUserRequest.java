package com.plantify.item.domain.dto.request;

import com.plantify.item.domain.entity.Item;
import com.plantify.item.domain.entity.MyItem;

<<<<<<< HEAD:src/main/java/com/plantify/item/domain/dto/request/MyItemRequest.java
public record MyItemRequest(Long itemId, Status status) {

    public MyItem toEntity(Long userId, Item item) {
        return MyItem.builder()
                .userId(userId)
=======
public record MyItemUserRequest(
        Long itemId,
        Long userId
) {
    public MyItem toEntity(Item item) {
        return MyItem.builder()
>>>>>>> feat/myItem-service:src/main/java/com/plantify/item/domain/dto/request/MyItemUserRequest.java
                .item(item)
                .userId(userId)
                .build();
    }
}