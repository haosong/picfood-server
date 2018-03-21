package com.picfood.server.service;

import com.picfood.server.entity.Comment;

public interface CommentService {
    public Comment makeComment(String uid, String postId, String content);

    public Comment getComment(String commentId);

    public void deleteComment(String commentId);
}
