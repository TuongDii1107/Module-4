package com.sqc.academy.bai3.response;

import org.springframework.http.ResponseEntity;

public class JsonResponse {
    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.ok(ApiResponse.<T>builder().code(200).message("SUCCESS").data(data).build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(201).body(ApiResponse.<T>builder().code(201).message("CREATED").data(data).build());
    }

    public static ResponseEntity<ApiResponse<Void>> noContent(String message) {
        return ResponseEntity.status(204).body(ApiResponse.<Void>builder().code(204).message(message).data(null).build());
    }

    public static ResponseEntity<ApiResponse<Void>> okNoData() {
        return ResponseEntity.ok(ApiResponse.<Void>builder().code(200).message("SUCCESS").data(null).build());
    }
}
