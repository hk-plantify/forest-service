package com.plantify.item.domain.dto.response;

import java.time.LocalDateTime;

public record CashResponse(
        Long cashBalance,
        Long accumulatedCash,
        Long redeemedCash,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
