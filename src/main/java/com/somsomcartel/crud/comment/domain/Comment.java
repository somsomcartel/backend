package com.somsomcartel.crud.comment.domain;

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

    @Column(nullable = false, length = 300)
    private String commentText;

    @CreatedDate
    private LocalDateTime commentTime;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Comment(String commentText, LocalDateTime commentTime, Post post, User user) {
        this.commentText = commentText;
        this.commentTime = commentTime;
        this.post = post;
        this.user = user;
    }

    public void updateComment(String commentText) {
        this.commentText = commentText;
    }
}
