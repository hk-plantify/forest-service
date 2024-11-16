package com.plantify.item.domain.dto.request;

public record ActivityLogRequest(
        String targetType,
        Long targetId,
        String actionType,
        Long userId,
        boolean isDeleted,
        Long modifiedBy
) {
}
