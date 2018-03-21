package com.picfood.server.repository;

import com.picfood.server.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, String> {
    public Post findByPostId(String id);
    public void deleteByPostId(String postId);

    @Query("select p.imageId from Post as p where p.dishId = :dishId")
    public List<String> findImagesByDishId(@Param("dishId") String id);

}
