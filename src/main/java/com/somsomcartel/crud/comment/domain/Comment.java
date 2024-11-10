package com.somsomcartel.crud.comment.domain;

import com.somsomcartel.crud.comment.dto.CommentRequestDto;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.post.domain.Post;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String commentText;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime commentTime;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Comment(String commentText, Post post, User user) {
        this.commentText = commentText;
        this.post = post;
        this.user = user;
    }

    public static Comment toEntity(CommentRequestDto commentRequestDto) {
        return Comment.builder()
                .commentText(commentRequestDto.getCommentText())
                .post(commentRequestDto.getPost())
                .user(commentRequestDto.getUser())
                .build();
    }

    public void update(String commentText) {
        this.commentText = commentText;
    }
}
