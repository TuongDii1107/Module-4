package com.sqc.academy.exception;

import com.sqc.academy.ApiRespone;
import com.sqc.academy.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?>  handleApiException(ApiException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiRespone.builder()
                        .code(ErrorCode.STUDENT_NOT_FOUND.getCode())
                        .message(ErrorCode.STUDENT_NOT_FOUND.getMessage())
                        .build()
        );
    }
}
