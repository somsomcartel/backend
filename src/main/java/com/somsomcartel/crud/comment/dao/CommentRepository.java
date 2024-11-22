package com.somsomcartel.crud.comment.dao;

import com.somsomcartel.crud.comment.domain.Comment;
import com.somsomcartel.crud.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByPost(Post post, Pageable pageable);
}
