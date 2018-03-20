package com.picfood.server.service;

import com.picfood.server.entity.Comment;
import com.picfood.server.entity.Like;

public interface CommentService {
    public Comment makeComment(String uid, String postId, String content);
    public Comment getComment(String commentId);
    public void deleteComment(String commentId);
    public Like like(String uid, String postId);
    public void deleteLike(String likeId, String postId);
}
