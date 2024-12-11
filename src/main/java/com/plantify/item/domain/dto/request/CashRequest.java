package com.plantify.item.domain.dto.request;

public record CashRequest(
        Long userId,
        Long amount,
        String type
) {
}
