package com.somsomcartel.crud.global.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorDetails {
    private String code;
    private String description;

    @Builder
    public ErrorDetails(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
