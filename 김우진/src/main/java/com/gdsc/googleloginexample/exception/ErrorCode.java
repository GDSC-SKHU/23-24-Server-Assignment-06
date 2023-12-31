package com.gdsc.googleloginexample.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // 400 Bad Request
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "필수적인 요청 값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 헤더값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 파라미터값이 입력되지 않았습니다."),
    REQUEST_METHOD_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 메소드가 잘못됐습니다."),
    VALIDATION_REQUEST_FAIL_USERINFO_EXCEPTION(HttpStatus.BAD_REQUEST,"사용자 정보를 받아오는데 실패했습니다."),

    // 401 Unauthorized
    UNAUTHORIZED_EMAIL_EXCEPTION(HttpStatus.UNAUTHORIZED, "이메일 인증이 되지 않은 유저"),
    UNAUTHORIZED_INVALID_EXCEPTION(HttpStatus.UNAUTHORIZED, "게시글 사용자와 수정을 요청한 사용자가 일치하지 않습니다"),
    INVALID_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),

    // 403 Forbidden
    FORBIDDEN_AUTH_EXCEPTION(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),

    // 404 NOT FOUND
    NOT_FOUND_ID_EXCEPTION(HttpStatus.NOT_FOUND, "찾을 수 없는 ID"),
    NOT_FOUND_POST_EXCEPTION(HttpStatus.NOT_FOUND, "찾을 수 없는 게시글"),
    NOT_FOUND_EMAIL_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 이메일"),

    // 409 Conflict
    ALREADY_EXIST_EMAIL_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다"),
    ALREADY_EXIST_SUBJECT_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 제목입니다"),

    // 500 Internal Server Exception
    INTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다."),

    // 503 Service Unavailable
    FAILED_GET_TOKEN_EXCEPTION(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "구글 엑세스 토큰을 가져오는데 실패했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode(){
        return httpStatus.value();
    }
}

