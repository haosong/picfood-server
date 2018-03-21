package com.picfood.server.repository;

import com.picfood.server.entity.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteRepository extends JpaRepository<Upvote, String> {
    public void deleteByUpvoteId(String upvoteId);
}
