package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.PostRequestDto;
import com.somsomcartel.crud.post.dto.PostResponseDto;
import com.somsomcartel.crud.post.exception.PostNotFoundException;
import com.somsomcartel.crud.user.dao.UserRepository;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Integer createPost(PostRequestDto postRequestDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Post post = postRequestDto.toEntity(user);
        postRepository.save(post);

        return post.getPostId();
    }

    public List<PostResponseDto> readPost(Integer page) {
        Page<Post> postList =  postRepository.findAll(PageRequest.of(page, 10));

        return postList.stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    public PostResponseDto readDetailPost(Integer postId) {
        Post post =  postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return PostResponseDto.fromEntity(post);
    }

    @Transactional
    public void updatePost(PostRequestDto postRequestDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.updatePost(postRequestDto);
    }

    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
    }
}
