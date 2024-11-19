package com.somsomcartel.crud.user.dao;

import com.somsomcartel.crud.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
