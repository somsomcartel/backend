package com.somsomcartel.crud.comment.api;

import com.somsomcartel.crud.comment.application.CommentService;
import com.somsomcartel.crud.comment.dto.CommentRequestDto;
import com.somsomcartel.crud.comment.dto.CommentResponseDto;
import com.somsomcartel.crud.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> readComment(@PathVariable("postId") Integer postId, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        List<CommentResponseDto> commentList = commentService.readComment(postId, page);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(commentList)
                .message("comment read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponse<?>> createComment(@Valid @ModelAttribute CommentRequestDto commentRequestDto,
                                                        BindingResult bindingResult, @PathVariable("postId") Integer postId) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        commentService.createComment(commentRequestDto, postId);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("comment create success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<ApiResponse<?>> updateComment(@PathVariable("commentId") Integer commentId,
                                                        @Valid @ModelAttribute CommentRequestDto commentRequestDto,
                                                        BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        commentService.updateComment(commentId, commentRequestDto);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("comment update success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<ApiResponse<?>> deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("comment delete success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
