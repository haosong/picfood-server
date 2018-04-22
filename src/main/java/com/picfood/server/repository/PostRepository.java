package com.picfood.server.repository;

import com.picfood.server.entity.Post;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, String> {
    public Post findByPostId(String id);

    public void deleteByPostId(String postId);

    @Query("select p.imageUrl from Post as p where p.dishId = :dishId")
    public List<String> findImagesByDishId(@Param("dishId") String id);

    public List<Post> findAllByDishId(String dishId);

    public List<Post> findAllByCreatorId(String userId);

    @Query(value = "SELECT p.* FROM post p WHERE p.creator_id = ?1 AND p.time < ?2 ORDER BY time DESC LIMIT 20", nativeQuery = true)
    public List<Post> findFirst20ByCreatorIdAndTimeBeforeOrderByTimeDesc(String userId, Date time);

}
