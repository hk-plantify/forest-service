package com.plantify.item.global.exception.errorcode;

import com.plantify.item.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum ItemErrorCode implements ErrorCode {

    ITEM_ACCESS_DENIED(HttpStatus.FORBIDDEN, "아이템에 대한 접근 권한이 없습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."),
    ITEM_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 아이템입니다."),
    INVALID_ITEM_DATA(HttpStatus.BAD_REQUEST, "유효하지 않은 아이템 데이터입니다."),
    ITEM_CREATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "아이템 생성에 실패했습니다."),
    ITEM_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "아이템 업데이트에 실패했습니다."),
    ITEM_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "아이템 삭제에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
