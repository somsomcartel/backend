package com.somsomcartel.crud.post.api;

import com.somsomcartel.crud.comment.application.CommentService;
import com.somsomcartel.crud.comment.dto.CommentResponseDto;
import com.somsomcartel.crud.global.common.ApiResponse;
import com.somsomcartel.crud.post.application.PostManageService;
import com.somsomcartel.crud.post.application.PostService;
import com.somsomcartel.crud.post.dto.ImageResponseDto;
import com.somsomcartel.crud.post.dto.PostCommentResponseDto;
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
    private final PostManageService postManageService;
    private final CommentService commentService;

    @PostMapping("/post")
    public ResponseEntity<ApiResponse<?>> createPost(@Valid @ModelAttribute PostRequestDto postRequestDto,
                                                     BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        ImageResponseDto imageResponseDto = postManageService.createPost(postRequestDto);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(imageResponseDto)
                .message("post create success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping("/post")
    public ResponseEntity<ApiResponse<?>> readPost(@RequestParam(value = "page", defaultValue = "0") Integer page) {
        List<PostResponseDto> postList = postService.readPost(page);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(postList)
                .message("post read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<?>> readDetailPost(@PathVariable("postId") Integer postId,
                                                         @RequestParam(value = "page", defaultValue = "0") Integer page) {
        PostResponseDto post = postManageService.readDetailPost(postId);
        List<CommentResponseDto> commentList = commentService.readComment(postId, page);

        PostCommentResponseDto postCommentResponseDto = PostCommentResponseDto.builder()
                .postResponseDto(post)
                .commentList(commentList)
                .build();

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(postCommentResponseDto)
                .message("post read success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @PatchMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<?>> updatePost(@Valid @ModelAttribute PostRequestDto postRequestDto,
                                                     BindingResult bindingResult,
                                                     @PathVariable("postId") Integer postId) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        ImageResponseDto imageResponseDto = postManageService.updatePost(postRequestDto, postId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .data(imageResponseDto)
                .message("post update success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable("postId") Integer postId) {
        postManageService.deletePost(postId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("post delete success")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok().body(apiResponse);
    }
}
