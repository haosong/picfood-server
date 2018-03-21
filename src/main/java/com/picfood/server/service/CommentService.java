package com.picfood.server.service;

import com.picfood.server.entity.Comment;

import com.picfood.server.entity.Upvote;
import java.util.List;
public interface CommentService {
    public Comment makeComment(String uid, String postId, String content);

    public List<Comment> getComment(String postId);

    public void deleteComment(String commentId);

    public Upvote upvote(String uid, String postId);
    public void deleteUpvote(String upvoteId, String postId);

    List<Comment> getCommentByPostId(String postId);

}
