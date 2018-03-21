package com.picfood.server.service;

import com.picfood.server.entity.Comment;

import com.picfood.server.entity.Upvote;
import java.util.List;
public interface CommentService {
    public Comment makeComment(String uid, String postId, String content);

    public List<Comment> getComment(String postId);

    public void deleteComment(String commentId);

    List<Comment> getCommentByPostId(String postId);

}
