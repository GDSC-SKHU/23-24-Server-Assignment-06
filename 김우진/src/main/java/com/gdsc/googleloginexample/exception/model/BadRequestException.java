package com.gdsc.googleloginexample.exception.model;

import com.gdsc.googleloginexample.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
