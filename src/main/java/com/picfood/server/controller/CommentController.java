package com.picfood.server.controller;

import java.util.*;

import com.picfood.server.entity.Comment;

import com.picfood.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.picfood.server.config.JwtUtil.USER_ID;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public Object makeComment(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> commentMap) {
        return commentService.makeComment(userId, commentMap.get("postId"), commentMap.get("content"));
    }

    @PostMapping("/delete/comment")
    public void deleteComment(@RequestBody Map<String, String> map) {
        commentService.deleteComment(map.get("commentId"));
    }

    @GetMapping("/api/comments/{postId}")
    public List<Comment> getComment(@PathVariable("postId") String postId) {
        return commentService.getComment(postId);
    }
}
