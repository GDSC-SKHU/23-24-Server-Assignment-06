package com.gdsc.oauth2example.execption;

public class InvalidAuthorException extends RuntimeException{
    public InvalidAuthorException(String message) { super(message);}

    public InvalidAuthorException() {
        this("작성자 형식이 올바르지 않습니다.");
    }
}
