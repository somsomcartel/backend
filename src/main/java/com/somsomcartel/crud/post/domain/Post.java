package com.somsomcartel.crud.post.domain;

import com.somsomcartel.crud.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false, length = 50)
    private String postTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String postText;

    @CreatedDate
    private LocalDateTime postTime;

    private String postImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public void setUser(User user) {
        if(this.user != null) {
            this.user.getPostList().remove(this);
        }

        this.user = user;
        user.getPostList().add(this);
    }

    @Builder
    public Post(String postTitle, String postText, LocalDateTime postTime, String postImage) {
        this.postTitle = postTitle;
        this.postText = postText;
        this.postTime = postTime;
        this.postImage = postImage;
    }
}
