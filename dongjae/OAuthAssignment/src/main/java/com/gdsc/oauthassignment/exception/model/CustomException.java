package com.gdsc.oauthassignment.exception.model;

import com.gdsc.oauthassignment.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;

    public CustomException(ErrorCode error, String message) {
        super(message);
        this.errorCode = error;
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatusCode();
    }
}