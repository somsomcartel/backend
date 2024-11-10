package com.somsomcartel.crud.comment.api;

import com.somsomcartel.crud.comment.application.CommentService;
import com.somsomcartel.crud.comment.dto.CommentEditRequestDto;
import com.somsomcartel.crud.comment.dto.CommentRequestDto;
import com.somsomcartel.crud.comment.dto.CommentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    /** 댓글 조회 */
    @GetMapping("/{postId}")
    public List<CommentResponseDto> getCommentInPost(@PathVariable Integer postId) {
        return commentService.findAll(postId);
    }

    /** 댓글 입력 */
    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto> commentPost(@PathVariable Integer postId, @RequestBody CommentRequestDto commentRequestDto,
                                      @RequestParam("userName") String userName) {
        CommentResponseDto commentResponseDto = commentService.save(commentRequestDto, postId, userName);
        return ResponseEntity.ok(commentResponseDto);
    }

    /** 댓글 수정 */
    @PatchMapping("/{postId}/{commentId}")
    public ResponseEntity<CommentResponseDto> commentEdit(@PathVariable Integer postId, @PathVariable Integer commentId, @RequestBody CommentEditRequestDto commentEditRequestDto) {
        CommentResponseDto commentResponseDto = commentService.update(commentId, commentEditRequestDto);
        return ResponseEntity.ok(commentResponseDto);
    }

    /** 댓글 삭제 */
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<Integer> commentDelete(@PathVariable Integer postId, @PathVariable Integer commentId) {
        commentService.delete(postId, commentId);
        return ResponseEntity.ok(commentId);
    }
}
