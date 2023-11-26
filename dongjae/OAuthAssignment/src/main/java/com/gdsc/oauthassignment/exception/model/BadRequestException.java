package com.gdsc.oauthassignment.exception.model;

import com.gdsc.oauthassignment.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode error, String message) {
        super(error, message);
    }
}
