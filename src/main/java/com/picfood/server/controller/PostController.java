package com.picfood.server.controller;

import com.picfood.server.entity.PostMsg;
import com.picfood.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import static com.picfood.server.config.JwtUtil.USER_ID;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) { this.postService = postService; }

    @PostMapping("/post")
    public Object post(@RequestHeader(value = USER_ID) String userId, @RequestBody PostMsg post) {
        return postService.createPost(userId, post);
    }

    @PostMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable("postId") String postId) {
        postService.deletePost(postId);
    }

}
