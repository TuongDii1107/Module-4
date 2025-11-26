package com.sqc.academy.bai3.exception;

import com.sqc.academy.bai3.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException ex) {
        ErrorCode code = ex.getErrorCode();
        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(code.getCode())
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(code.getStatus()).body(resp);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        ApiResponse<Void> resp = ApiResponse.<Void>builder().code(ErrorCode.BAD_REQUEST.getCode()).message("Validation failed").data(null).build();
        return ResponseEntity.badRequest().body(resp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleOther(Exception ex) {
        ApiResponse<Void> resp = ApiResponse.<Void>builder().code(ErrorCode.INTERNAL_ERROR.getCode()).message(ex.getMessage()).data(null).build();
        return ResponseEntity.status(500).body(resp);
    }
}