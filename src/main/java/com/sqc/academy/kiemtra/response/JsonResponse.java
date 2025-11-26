package com.sqc.academy.kiemtra.response;

import com.sqc.academy.bai3.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {
    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponse.<T>builder().code(200).message("SUCCESS").data(data).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<T>builder().code(201).message("CREATED").data(data).build());
    }

    public static ResponseEntity<ApiResponse<Void>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.<Void>builder().code(204).message("NO CONTENT").data(null).build());
    }

    public static ResponseEntity<ApiResponse<Void>> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.<Void>builder().code(400).message(message).data(null).build());
    }

    public static ResponseEntity<ApiResponse<Void>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<Void>builder().code(404).message(message).data(null).build());
    }
}
