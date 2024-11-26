package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.ImageResponseDto;
import com.somsomcartel.crud.post.dto.PostRequestDto;
import com.somsomcartel.crud.post.dto.PostResponseDto;
import com.somsomcartel.crud.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostManageService {

    private final PostService postService;
    private final ImageService imageService;

    private final PostRepository postRepository;

    @Transactional
    public ImageResponseDto createPost(PostRequestDto postRequestDto) {
        int postId = postService.createPost(postRequestDto);

        String imageName = postRequestDto.getPostImage();
        if(imageName != null) {
            String uuid = UUID.randomUUID().toString();
            String extension = "." + imageName.substring(imageName.lastIndexOf(".") + 1);

            ImageResponseDto imageResponseDto = imageService.createPutPresignedUrl(uuid + extension);
            imageService.createPostImageKey(postId, imageResponseDto.getKey());

            return imageResponseDto;
        }

        return null;
    }

    public PostResponseDto readDetailPost(Integer postId) {
        PostResponseDto postResponseDto = postService.readDetailPost(postId);

        String imageName = postResponseDto.getPostImage();
        if(imageName != null) {
            String presignedURL = imageService.createGetPresignedUrl(imageName);
            postResponseDto.setPostImage(presignedURL);
        }

        return postResponseDto;
    }

    @Transactional
    public ImageResponseDto updatePost(PostRequestDto postRequestDto, Integer postId) {
        postService.updatePost(postRequestDto, postId);

        String imageName = postRequestDto.getPostImage();
        if(imageName != null) {
            return imageService.createPutPresignedUrl(postRepository.findById(postId)
                    .orElseThrow(PostNotFoundException::new)
                    .getPostImage()
            );
        }

        return null;
    }

    @Transactional
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        if(post.getPostImage() != null) {
            imageService.deleteImage(post.getPostImage());
        }

        postService.deletePost(postId);
    }

}
