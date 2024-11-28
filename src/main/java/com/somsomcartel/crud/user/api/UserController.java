package com.somsomcartel.crud.user.api;

import com.somsomcartel.crud.global.common.ApiResponse;
import com.somsomcartel.crud.post.application.PostService;
import com.somsomcartel.crud.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final PostService postService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<?>> readPost(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        List<PostResponseDto> postList = postService.readPost(page);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(postList)
                .message("mypage read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
