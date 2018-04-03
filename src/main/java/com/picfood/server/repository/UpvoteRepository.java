package com.picfood.server.repository;

import com.picfood.server.entity.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpvoteRepository extends JpaRepository<Upvote, String> {
    public void deleteByUpvoteId(String upvoteId);
    public List<Upvote> findAllByUserId(String user_id);
    public List<Upvote> findAllByUserIdAndPostId(String userId, String postId);
    public void deleteAllByPostId(String postId);
}
