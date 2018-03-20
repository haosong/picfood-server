package com.picfood.server.repository;

import com.picfood.server.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, String> {
    public void deleteByLikeId(String likeId);
}
