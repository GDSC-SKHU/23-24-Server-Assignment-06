package com.gdsc.oauthassignment.exception.model;

import com.gdsc.oauthassignment.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode error, String message) {
        super(error, message);
    }
}
