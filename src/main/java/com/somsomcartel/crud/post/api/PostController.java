package com.somsomcartel.crud.post.api;

import com.somsomcartel.crud.global.common.ApiResponse;
import com.somsomcartel.crud.post.application.PostService;
import com.somsomcartel.crud.post.dto.PostCreateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<ApiResponse<?>> createPost(@ModelAttribute PostCreateReqDto postCreateReqDto) {
        postService.createPost(postCreateReqDto);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("post create success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


}
