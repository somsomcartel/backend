package com.somsomcartel.crud.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // Common
    COMMON_INVALID_INPUT("INVALID_INPUT", "The input value is invalid."),
    COMMON_INVALID_HTTP_METHOD("INVALID_HTTP_METHOD", "The HTTP method is not allowed."),
    COMMON_ACCESS_DENIED("ACCESS_DENIED", "You do not have permission to access this resource."),
    COMMON_URL_NOT_FOUND("URL_NOT_FOUND", "The requested URL does not exist."),
    COMMON_SERVER_ERROR("SERVER_ERROR", "A server error has occurred."),

    //User
    USER_NOT_FOUND("USER_NOT_FOUND", "The user does not exist."),
    // Post
    POST_NOT_FOUND("POST_NOT_FOUND", "The post does not exist."),
    // Comment
    COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "The comment page does not exist.")
    ;

    private final String code;
    private final String description;
}
