package com.somsomcartel.crud.comment.dto;

import com.somsomcartel.crud.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private String commentText;
    private String userName;
    private Integer postId;

    @Builder
    public CommentResponseDto(String commentText, String userName, Integer postId) {
        this.commentText = commentText;
        this.userName = userName;
        this.postId = postId;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment.getCommentText(), comment.getUser().getUserName(), comment.getPost().getPostId());
    }
}
