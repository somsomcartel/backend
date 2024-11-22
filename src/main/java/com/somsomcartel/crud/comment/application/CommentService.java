package com.somsomcartel.crud.comment.application;

import com.somsomcartel.crud.comment.dao.CommentRepository;
import com.somsomcartel.crud.comment.domain.Comment;
import com.somsomcartel.crud.comment.dto.CommentRequestDto;
import com.somsomcartel.crud.comment.dto.CommentResponseDto;
import com.somsomcartel.crud.comment.exception.CommentNotFoundException;
import com.somsomcartel.crud.user.dao.UserRepository;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.user.exception.UserNotFoundException;
import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void createComment(CommentRequestDto commentRequestDto, Integer postId) {
        // TODO: 로그인 정보에 따라 user 초기화
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comment comment = commentRequestDto.toEntity(post, user);
        commentRepository.save(comment);
    }

    public void updateComment(Integer commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.updateComment(commentRequestDto.getCommentText());
    }

    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }

    public List<CommentResponseDto> readComment(Integer postId, Integer page) {
        Page<Comment> commentList = commentRepository.findByPost(postRepository.getReferenceById(postId), PageRequest.of(page, 10));

        return commentList.stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }
}
