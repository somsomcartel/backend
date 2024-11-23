package com.somsomcartel.crud.global.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorDetails {
    private String code;
    private String description;

    private ErrorDetails(final ErrorCode errorCode) {
        this.description = errorCode.getDescription();
        this.code = errorCode.getCode();
    }

    public ErrorDetails(final ErrorCode code, final String description) {
        this.description = description;
        this.code = code.getCode();
    }

    public static ErrorDetails from(final ErrorCode code) {
        return new ErrorDetails(code);
    }

    public static ErrorDetails of(final ErrorCode code, final String message) {
        return new ErrorDetails(code, message);
    }
}
