package com.picfood.server.controller;
import java.util.*;

import com.picfood.server.entity.Comment;
import com.picfood.server.entity.Post;
import com.picfood.server.service.CommentService;
import com.picfood.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.picfood.server.config.JwtUtil.USER_ID;

@RestController
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/post")
    public Post post(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> postMap) {
        return postService.createPost(userId, postMap);
    }

    @PostMapping("/delete/post")
    public void deletePost(@RequestBody Map<String, String> map) {
        postService.deletePost(map.get("postId"));
    }

    @GetMapping("/api/post/{postId}")
    public Post getPost(@PathVariable("postId") String postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/api/post/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable("postId") String postId){
        return commentService.getCommentByPostId(postId);
    }
}
