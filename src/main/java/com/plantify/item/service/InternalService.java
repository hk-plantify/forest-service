package com.plantify.item.service;

public interface InternalService {

    void recordActivityLog(String targetType, Long targetId, String actionType, Long adminId);
}
