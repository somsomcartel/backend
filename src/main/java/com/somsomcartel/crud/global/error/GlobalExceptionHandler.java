package com.somsomcartel.crud.global.error;

import com.somsomcartel.crud.comment.exception.CommentNotFoundException;
import com.somsomcartel.crud.global.common.ApiResponse;
import com.somsomcartel.crud.post.exception.PostNotFoundException;
import com.somsomcartel.crud.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Common Exception
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(makeApiResponse(ErrorCode.COMMON_INVALID_INPUT));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException (HttpRequestMethodNotSupportedException  e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(makeApiResponse(ErrorCode.COMMON_INVALID_HTTP_METHOD));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(makeApiResponse(ErrorCode.COMMON_ACCESS_DENIED));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoResourceFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(makeApiResponse(ErrorCode.COMMON_URL_NOT_FOUND));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(makeApiResponse(ErrorCode.COMMON_SERVER_ERROR));
    }

    // Custom Exception
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(makeApiResponse(e.getErrorCode()));
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> handlePostNotFoundException(PostNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(makeApiResponse(e.getErrorCode()));
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> handleCommentNotFoundException(CommentNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(makeApiResponse(e.getErrorCode()));
    }

    private ApiResponse<?> makeApiResponse(ErrorCode errorCode) {
        return ApiResponse.builder()
                .message("error occurred")
                .success(false)
                .timestamp(LocalDateTime.now())
                .errorDetails(ErrorDetails.from(errorCode))
                .build();
    }

}
