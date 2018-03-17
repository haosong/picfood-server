package com.picfood.server.repository;

import com.picfood.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shawn on 2018/3/16.
 */
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserId(Long id);
}
