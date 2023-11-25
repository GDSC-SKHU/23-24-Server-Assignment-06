package com.gdsc.googleloginexample.exception.model;

import com.gdsc.googleloginexample.exception.ErrorCode;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
