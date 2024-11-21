package com.somsomcartel.crud.post.api;

import com.somsomcartel.crud.global.common.ApiResponse;
import com.somsomcartel.crud.post.application.PostService;
import com.somsomcartel.crud.post.dto.PostCreateReqDto;
import com.somsomcartel.crud.post.dto.PostReadResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/post")
    public ResponseEntity<ApiResponse<?>> readPost() {
        List<PostReadResDto> postList = postService.readPost();
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(postList)
                .message("post read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
