package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.ImageResponseDto;
import com.somsomcartel.crud.post.dto.PostRequestDto;
import com.somsomcartel.crud.post.dto.PostResponseDto;
import com.somsomcartel.crud.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostManageService {

    private final PostService postService;
    private final ImageService imageService;

    private final PostRepository postRepository;

    //private final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    @Transactional
    public ImageResponseDto createPost(PostRequestDto postRequestDto) {
        //String userId = jwt.getClaimAsString("sub");
        //int postId = postService.createPost(postRequestDto, userId);

        int postId = postService.createPost(postRequestDto, "asdf");

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
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        //authenticate(post);

        postService.updatePost(postRequestDto, postId);

        String ImageName = generateNewImageName(postRequestDto.getPostImage(), post.getPostImage());
        if(ImageName != null) {
            post.updatePostImage(ImageName);
            return imageService.createPutPresignedUrl(ImageName);
        }

        return null;
    }

    private String generateNewImageName(String newImage, String currentImage) {
        if(newImage == null) {
            return null;
        }

        String newExtension = newImage.substring(newImage.lastIndexOf("."));

        if(currentImage == null) {
            return UUID.randomUUID().toString() + newExtension;
        } else {
            return currentImage.substring(0, currentImage.lastIndexOf(".")) + newExtension;
        }
    }


    @Transactional
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        //authenticate(post);

        if(post.getPostImage() != null) {
            imageService.deleteImage(post.getPostImage());
        }

        postService.deletePost(postId);
    }

//    private void authenticate(Post post) {
//        String userId = jwt.getClaimAsString("sub");
//        if(!post.getUser().getUserId().equals(userId)) {
//            throw new AccessDeniedException("access denied");
//        }
//    }

}
