package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dto.ImageResponseDto;
import com.somsomcartel.crud.post.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostManageService {

    private final PostService postService;
    private final ImageService imageService;

    @Transactional
    public void createPost(PostRequestDto postRequestDto) {
        int postId = postService.createPost(postRequestDto);

        String imageName = postRequestDto.getPostImage();
        if(imageName != null) {
            ImageResponseDto imageResponseDto = imageService.createPresignedUrl(imageName);
            imageService.createPostImageKey(postId, imageResponseDto.getKey());
        }
    }

}
