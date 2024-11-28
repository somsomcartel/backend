package com.somsomcartel.crud.user.application;

import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.dto.PostResponseDto;
import com.somsomcartel.crud.user.dao.UserRepository;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    //private final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public List<PostResponseDto> readPost(Integer page) {
        //String userId = jwt.getClaimAsString("sub");
        User user = userRepository.findById("asdf").orElseThrow(UserNotFoundException::new);

        Page<Post> postList =  postRepository.findByUser(user, PageRequest.of(page, 10));

        return postList.stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }
}
