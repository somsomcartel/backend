package com.somsomcartel.crud.post.api;

import com.somsomcartel.crud.global.common.ApiResponse;
import com.somsomcartel.crud.post.application.PostService;
import com.somsomcartel.crud.post.dto.PostRequestDto;
import com.somsomcartel.crud.post.dto.PostResponseDto;
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
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<ApiResponse<?>> createPost(@Valid @ModelAttribute PostRequestDto postCreateReqDto,
                                                     BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        postService.createPost(postCreateReqDto);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("post create success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/post")
    public ResponseEntity<ApiResponse<?>> readPost() {
        List<PostResponseDto> postList = postService.readPost();
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(postList)
                .message("post read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<?>> readDetailPost(@PathVariable("postId") Integer postId) {
        PostResponseDto post = postService.readDetailPost(postId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(post)
                .message("post read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@Valid @ModelAttribute PostRequestDto postCreateReqDto,
                                                     BindingResult bindingResult,
                                                     @PathVariable("postId") Integer postId) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        postService.updatePost(postCreateReqDto, postId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("post update success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@PathVariable("postId") Integer postId) {
        postService.deletePost(postId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("post delete success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
