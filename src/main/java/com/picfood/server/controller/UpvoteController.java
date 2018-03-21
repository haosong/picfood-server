package com.picfood.server.controller;

import com.picfood.server.entity.Upvote;
import com.picfood.server.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.picfood.server.config.JwtUtil.USER_ID;

@RestController
public class UpvoteController {
    private final UpvoteService upvoteService;

    @Autowired
    public UpvoteController(UpvoteService upvoteService) {
        this.upvoteService = upvoteService;
    }

    @PostMapping("/upvote")
    public Upvote like(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> likeMap) {
        return upvoteService.upvote(userId, likeMap.get("postId"));
    }

    @PostMapping("/delete/like")
    public void deleteLike(@RequestBody Map<String, String> map) {
        upvoteService.deleteUpvote(map.get("likeId"), map.get("postId"));
    }
}
