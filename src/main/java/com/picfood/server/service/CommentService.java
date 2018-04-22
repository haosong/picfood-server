package com.picfood.server.service;

import com.picfood.server.entity.Comment;

import com.picfood.server.entity.DTO.CommentDTO;
import com.picfood.server.entity.Upvote;

import java.util.Date;
import java.util.List;
public interface CommentService {
    public Comment makeComment(String uid, String postId, String content);

    public List<CommentDTO> getComments(String postId);

    public void deleteComment(String commentId);

    public CommentDTO convertToDTO(Comment comment);

    long getCommentCountByPostId(String postId);

    public List<Comment> getCommentByUserId(String userId);

    public List<Comment> getCommentByUserId(String userId, Date time);
}
