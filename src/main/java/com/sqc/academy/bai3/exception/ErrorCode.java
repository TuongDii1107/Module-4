package com.sqc.academy.bai3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND(1001, "Employee not found", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_FOUND(2001, "Department not found", HttpStatus.NOT_FOUND),
    BAD_REQUEST(4000, "Bad request", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5000, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
