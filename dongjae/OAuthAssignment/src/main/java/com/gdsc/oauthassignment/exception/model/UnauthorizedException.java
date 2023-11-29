package com.gdsc.oauthassignment.exception.model;

import com.gdsc.oauthassignment.exception.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode error, String message) {
        super(error, message);
    }
}
