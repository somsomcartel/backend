package com.somsomcartel.crud.comment.dto;

import com.somsomcartel.crud.comment.domain.Comment;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.post.domain.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {
    @NotBlank
    @Size(max = 300)
    private String commentText;

    public Comment toEntity(Post post, User user) {
        Comment comment = Comment.builder()
                .commentText(commentText)
                .commentTime(LocalDateTime.now())
                .build();

        comment.setPost(post);
        comment.setUser(user);

        return comment;
    }
}
