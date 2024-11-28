package com.somsomcartel.crud.post.dao;

import com.somsomcartel.crud.post.domain.Post;
import com.somsomcartel.crud.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByUser(User user, Pageable pageable);
}
