package com.picfood.server.service;

import com.picfood.server.entity.Upvote;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UpvoteService {
    public Upvote upvote(String uid, String postId);
    public void deleteUpvote(String upvoteId, String postId);
    public List<Upvote> getUpvoteByUserId(String userId);
    public String hasUpvoted(String userId, String postId);
}
