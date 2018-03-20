package com.picfood.server.service.impl;

import com.picfood.server.entity.Comment;
import com.picfood.server.repository.CommentRepository;
import com.picfood.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment makeComment(String uid, String postId, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(uid);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    public Comment getComment(String commentId) {
        return commentRepository.findByCommentId(commentId);
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteByCommentId(commentId);
    }
}
