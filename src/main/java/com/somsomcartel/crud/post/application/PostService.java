package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.PostRequestDto;
import com.somsomcartel.crud.post.dto.PostResponseDto;
import com.somsomcartel.crud.user.dao.UserRepository;
import com.somsomcartel.crud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    //private final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    @Transactional
    public void createPost(PostRequestDto postCreateReqDto) {

        //String userId = jwt.getClaimAsString("sub");
        //User user = userRepository.findById(userId).get();

        // TODO: 로그인 작업 완료 후 삭제 예정
        User user = userRepository.findById("asdf").get();

        Post post = postCreateReqDto.toEntity(user);

        postRepository.save(post);
    }

    public List<PostResponseDto> readPost() {
        List<Post> postList =  postRepository.findAll();

        return postList.stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    public PostResponseDto readDetailPost(Integer postId) {
        Post post =  postRepository.findById(postId).get();
        return PostResponseDto.fromEntity(post);
    }

    @Transactional
    public void updatePost(PostRequestDto postCreateReqDto, Integer postId) {
        Post post = postRepository.findById(postId).get();
        // TODO: 로그인 작업 완료 후 검증 단계 추가
        post.updatePost(postCreateReqDto);
    }

    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).get();
        // TODO: 로그인 작업 완료 후 검증 단계 추가
        postRepository.delete(post);
    }
}
