package com.picfood.server.service;

import com.picfood.server.entity.Upvote;
import org.springframework.stereotype.Service;

public interface UpvoteService {
    public Upvote upvote(String uid, String postId);
    public void deleteUpvote(String upvoteId, String postId);
}
