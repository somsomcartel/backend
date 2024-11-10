package com.somsomcartel.crud.comment.dto;

import com.somsomcartel.crud.comment.domain.Comment;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.post.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    private String commentText;
    private User user;
    private Post post;

    @Builder
    public CommentRequestDto(String commentText, User user, Post post) {
        this.commentText = commentText;
        this.user = user;
        this.post = post;
    }

    public CommentRequestDto toDto(Comment comment) {
        return new CommentRequestDto(comment.getCommentText(), comment.getUser(), comment.getPost());
    }
}
