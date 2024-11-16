package com.plantify.item.service;

import com.plantify.item.client.ActivityLogServiceClient;
import com.plantify.item.domain.dto.request.ActivityLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternalServiceImpl implements InternalService {

    private final ActivityLogServiceClient activityLogServiceClient;

    @Override
    public void recordActivityLog(String targetType, Long targetId, String actionType, Long adminId) {
        ActivityLogRequest request = new ActivityLogRequest(
                targetType,
                targetId,
                actionType,
                adminId,
                false,
                null
        );
        activityLogServiceClient.recordActivityLog(request);
    }
}
