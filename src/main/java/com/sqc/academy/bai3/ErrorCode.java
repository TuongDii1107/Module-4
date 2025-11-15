package com.sqc.academy.bai3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND(40401, "Employee is not exist!", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXISTED(40402, "Department is not existed!", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus status;
}
