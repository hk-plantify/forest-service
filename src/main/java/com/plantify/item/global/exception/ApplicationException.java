package com.plantify.item.global.exception;

import com.plantify.item.global.exception.errorcode.AuthErrorCode;
import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;

    public ApplicationException(ErrorCode errorCode) {
        httpStatus = errorCode.getHttpStatus();
        message = errorCode.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return null;
    }
}
