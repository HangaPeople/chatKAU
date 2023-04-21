package com.chatkau.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, "2000", "요청에 성공하였습니다."),


    DUPLICATED_USERID(false, "4000", "이미 가입된 아이디입니다."),
    USERID_ERROR(false, "4001", "존재하지 않는 아이디입니다."),
    PASSWORD_ERROR(false, "4002", "비밀번호가 틀립니다."),


    DATABASE_CONNECTION_ERROR(false, "5000", "DB관련 에러 발생."),
    PARSE_EXCEPTION_ERROR(false, "5001", "파싱 작업 중 에러 발생");

    private final boolean isSuccess;
    private final String code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, String code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
