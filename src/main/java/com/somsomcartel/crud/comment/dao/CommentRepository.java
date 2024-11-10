package com.somsomcartel.crud.comment.dao;

import com.somsomcartel.crud.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
