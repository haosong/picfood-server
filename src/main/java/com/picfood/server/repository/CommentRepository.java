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

    public void deleteAllByPostId(String postId);

    @Query("SELECT COUNT(c) FROM Comment AS c WHERE postId = :postId GROUP BY postId")
    long getCommentCountByPostId(@Param("postId") String pid);

    public List<Comment> findAllByCommenterId(String user_id);

    @Query(value = "SELECT c.* FROM comment c WHERE c.commenter_id = ?1 AND c.time < ?2 ORDER BY time DESC LIMIT 20", nativeQuery = true)
    public List<Comment> findFirst20ByCommenterIdAndTimeBeforeOrderByTimeDesc(String commenter_id, Date time);

}
