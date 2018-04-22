package com.picfood.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.picfood.server.entity.Comment;

import com.picfood.server.entity.DTO.CommentDTO;
import com.picfood.server.entity.Post;
import com.picfood.server.entity.Upvote;
import com.picfood.server.entity.User;
import com.picfood.server.repository.CommentRepository;
import com.picfood.server.repository.PostRepository;
import com.picfood.server.repository.UpvoteRepository;
import com.picfood.server.repository.UserRepository;
import com.picfood.server.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment makeComment(String uid, String postId, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommenterId(uid);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    public List<CommentDTO> getComments(String postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        User commenter = userRepository.findByUserId(comment.getCommenterId());
        if (commenter == null) {
            throw new NoSuchElementException("User doesn't exist.");
        }
        commentDTO.setCommenter(commenter.getName());
        commentDTO.setCommenterAvatar(commenter.getAvatar());
        Post post = postRepository.findByPostId(comment.getPostId());
        commentDTO.setPosterName(post.getUserName());
        return commentDTO;
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteByCommentId(commentId);
    }

    @Override
    public long getCommentCountByPostId(String postId) {
        return commentRepository.getCommentCountByPostId(postId);
    }

    public List<Comment> getCommentByUserId(String userId) {
        return commentRepository.findAllByCommenterId(userId);
    }

    public List<Comment> getCommentByUserId(String userId, Date time) {
        return commentRepository.findFirst20ByCommenterIdAndTimeBeforeOrderByTimeDesc(userId, time);
    }

}
