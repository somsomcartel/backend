package com.somsomcartel.crud.post.dto;

import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    private String postTitle;
    private String postText;
    private String postImage;

    public Post toEntity(User user) {
       Post post = Post.builder()
                .postTitle(postTitle)
                .postText(postText)
                .postTime(LocalDateTime.now())
                .postImage(postImage)
                .build();

       post.setUser(user);
       return post;
    }
}
