package com.picfood.server.repository;

import com.picfood.server.entity.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UpvoteRepository extends JpaRepository<Upvote, String> {
    public void deleteByUpvoteId(String upvoteId);

    public List<Upvote> findAllByUserId(String user_id);

    @Query(value = "SELECT u.* FROM upvote u WHERE u.user_id = ?1 AND u.time < ?2 ORDER BY time DESC LIMIT 20", nativeQuery = true)
    public List<Upvote> findFirst20ByUserIdAndTimeBeforeOrderByTimeDesc(String user_id, Date time);

    public List<Upvote> findAllByUserIdAndPostId(String userId, String postId);

    public void deleteAllByPostId(String postId);
}
