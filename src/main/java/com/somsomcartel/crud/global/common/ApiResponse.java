package com.somsomcartel.crud.global.common;

import com.somsomcartel.crud.global.error.ErrorDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
    private boolean success;
    private LocalDateTime timestamp;
    private ErrorDetails errorDetails;

    @Builder
    public ApiResponse(String message, T data, boolean success, LocalDateTime timestamp, ErrorDetails errorDetails) {
        this.message = message;
        this.data = data;
        this.success = success;
        this.timestamp = timestamp;
        this.errorDetails = errorDetails;
    }
}
