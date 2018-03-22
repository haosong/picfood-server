package com.picfood.server.repository;

import java.util.*;
import com.picfood.server.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
    public List<Comment> findAllByPostId(String pid);
    public Comment findByCommentId(String commentId);
    public void deleteByCommentId(String commentId);

}
