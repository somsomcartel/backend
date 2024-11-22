package com.somsomcartel.crud.comment.dto;

import com.somsomcartel.crud.comment.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponseDto {
    private String commentText;
    private LocalDateTime commentTime;
    private String userName;
    private Integer postId;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentText(comment.getCommentText())
                .commentTime(comment.getCommentTime())
                .userName(comment.getUser().getUserId())
                .postId(comment.getPost().getPostId())
                .build();
    }
}