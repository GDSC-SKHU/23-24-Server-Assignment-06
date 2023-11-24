package com.gdsc.oauth2example.execption;

public class InvalidContentException extends RuntimeException{
    public InvalidContentException(String message) { super(message);}

    public InvalidContentException() {
        this("제목 형식이 올바르지 않습니다.");
    }
}
