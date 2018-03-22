package com.picfood.server.repository;

import java.util.*;
import com.picfood.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, String> {
    public List<Comment> findAllByPostId(String pid);
    public Comment findByCommentId(String commentId);
    public void deleteByCommentId(String commentId);

    @Query("SELECT COUNT(c) FROM Comment AS c WHERE postId = :postId GROUP BY postId")
    long getCommentCountByPostId(@Param("postId") String pid);

}
