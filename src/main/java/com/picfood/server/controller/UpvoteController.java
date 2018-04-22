package com.picfood.server.controller;

import com.picfood.server.entity.Upvote;
import com.picfood.server.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.picfood.server.config.JwtUtil.USER_ID;

@RestController
public class UpvoteController {
    private final UpvoteService upvoteService;

    @Autowired
    public UpvoteController(UpvoteService upvoteService) {
        this.upvoteService = upvoteService;
    }

    @PostMapping("/api/upvote")
    public Upvote upvote(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> map) {
        return upvoteService.upvote(userId, map.get("postId"));
    }

    @PostMapping("/api/delete/upvote")
    public String deleteUpvote(@RequestBody Map<String, String> map) {
        upvoteService.deleteUpvote(map.get("upvoteId"), map.get("postId"));
        return "delete success";
    }

    @GetMapping("/api/hasUpvoted")
    public String hasUpvoted(@RequestParam String UserId, @RequestParam String PostId) {
        return upvoteService.hasUpvoted(UserId, PostId);
    }
}
