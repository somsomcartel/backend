package com.somsomcartel.crud.post.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.PostCreateReqDto;
import com.somsomcartel.crud.post.dto.PostReadResDto;
import com.somsomcartel.crud.user.dao.UserRepository;
import com.somsomcartel.crud.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    //private final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    @Transactional
    public void createPost(PostCreateReqDto postCreateReqDto) {

        //String userId = jwt.getClaimAsString("sub");
        //User user = userRepository.findById(userId).get();

        // TODO: 로그인 작업 완료 후 삭제 예정
        User user = userRepository.findById("asdf").get();

        Post post = postCreateReqDto.toEntity(user);

        postRepository.save(post);
    }

    public List<PostReadResDto> readPost() {
        List<Post> postList =  postRepository.findAll();

        return postList.stream()
                .map(PostReadResDto::fromEntity)
                .toList();
    }
}
