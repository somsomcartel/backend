package com.somsomcartel.crud.comment.application;

import com.somsomcartel.crud.comment.dao.CommentRepository;
import com.somsomcartel.crud.comment.domain.Comment;
import com.somsomcartel.crud.comment.dto.CommentEditRequestDto;
import com.somsomcartel.crud.comment.dto.CommentRequestDto;
import com.somsomcartel.crud.comment.dto.CommentResponseDto;
import com.somsomcartel.crud.comment.exception.CommentNotFoundException;
import com.somsomcartel.crud.user.dao.UserRepository;
import com.somsomcartel.crud.user.domain.User;
import com.somsomcartel.crud.post.dao.PostRepository;
import com.somsomcartel.crud.post.domain.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /** 댓글 작성 */
    public CommentResponseDto save(CommentRequestDto commentRequestDto, Integer postId, String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        commentRequestDto.setPost(post);
        commentRequestDto.setUser(user);

        Comment comment = commentRepository.save(Comment.toEntity(commentRequestDto));
        return CommentResponseDto.toDto(comment);
    }

    /** 댓글 수정 */
    public CommentResponseDto update(Integer commentId, CommentEditRequestDto commentEditRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.update(commentEditRequestDto.getCommentText());
        return CommentResponseDto.toDto(comment);
    }

    /** 댓글 삭제 */
    public void delete(Integer postId, Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        commentRepository.delete(comment);
    }

    /** 댓글 조회 */
    public List<CommentResponseDto> findAll(Integer postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentResponseDto> dtoList = new ArrayList<>();

        for (Comment c : comments) {
            CommentResponseDto dto = CommentResponseDto.toDto(c);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
