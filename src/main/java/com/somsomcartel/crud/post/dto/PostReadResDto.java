package com.somsomcartel.crud.post.dto;

import com.somsomcartel.crud.post.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostReadResDto {
    private String postTitle;
    private String postText;
    private LocalDateTime postTime;
    private String postImage;
    private String username;

    public static PostReadResDto fromEntity(Post post) {
        return PostReadResDto.builder()
                .postTitle(post.getPostTitle())
                .postText(post.getPostText())
                .postTime(post.getPostTime())
                .username(post.getUser().getUserId())
                .build();
    }
}
