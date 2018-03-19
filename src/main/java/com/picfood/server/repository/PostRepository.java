package com.picfood.server.repository;

import com.picfood.server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
    public Post findByPostId(String id);
    public void deleteByPostId(String postId);
}
