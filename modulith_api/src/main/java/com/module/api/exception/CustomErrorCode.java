package com.module.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    // 400 BAD_REQUEST
    NOT_NULL(HttpStatus.BAD_REQUEST, "필수 입력값입니다."),
    INVALID_CERTIFICATION_NUMBER(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다."),
    NOT_AUTHORIZED(HttpStatus.BAD_REQUEST, "권한이 없는 사용자입니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."),

    // 401 UNAUTHORIZED
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    TOKEN_TAMPERED(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다"),


    // 404 NOT_FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    CHATTING_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 날짜에 채팅 내역이 없습니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),

    // 409 CONFLICT
    CONFLICT_EMAIL(HttpStatus.CONFLICT, "이미 등록 된 이메일입니다."),
    CONFLICT_NICKNAME(HttpStatus.CONFLICT, "이미 사용 중인 닉네임입니다."),


    // 500 INTERNAL_SERVER_ERROR
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
