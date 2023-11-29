package com.gdsc.googleloginexample.exception.model;

import com.gdsc.googleloginexample.exception.ErrorCode;


public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}