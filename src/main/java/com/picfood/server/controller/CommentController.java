package com.picfood.server.controller;

import java.util.*;

import com.picfood.server.entity.Comment;

import com.picfood.server.entity.DTO.CommentDTO;
import com.picfood.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.picfood.server.config.JwtUtil.USER_ID;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comment")
    public Object makeComment(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> commentMap) {
        Comment comment = commentService.makeComment(userId, commentMap.get("postId"), commentMap.get("content"));
        try{
            return commentService.convertToDTO(comment);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/delete/comment")
    public void deleteComment(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> map) {
        commentService.deleteComment(map.get("commentId"));
    }

    @GetMapping("/api/comments/{postId}")
    public Object getComments(@PathVariable("postId") String postId) {
        try{
            return commentService.getComments(postId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
