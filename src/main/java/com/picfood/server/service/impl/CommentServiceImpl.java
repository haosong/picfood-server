package com.picfood.server.service.impl;

import com.picfood.server.entity.Comment;
import com.picfood.server.entity.Like;
import com.picfood.server.entity.Post;
import com.picfood.server.repository.CommentRepository;
import com.picfood.server.repository.LikeRepository;
import com.picfood.server.repository.PostRepository;
import com.picfood.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, LikeRepository likeRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
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

    public Like like(String uid, String postId) {
        Like like = new Like();
        like.setUserId(uid);
        like.setPostId(postId);
        return likeRepository.save(like);
    }

    public void deleteLike(String likeId, String postId) {
        likeRepository.deleteByLikeId(likeId);
        Post post = postRepository.findByPostId(postId);
        post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
    }
}
