package com.picfood.server.repository;

import com.picfood.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shawn on 2018/3/16.
 */
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserId(String id);

    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE CONCAT('%',LOWER(:name),'%')")
    public List<User> findUsersByName(@Param("name") String name);
}
