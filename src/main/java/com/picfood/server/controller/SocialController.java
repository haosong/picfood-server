package com.picfood.server.controller;

import com.picfood.server.entity.*;
import com.picfood.server.service.CommentService;
import com.picfood.server.service.PostService;
import com.picfood.server.service.SocialService;
import com.picfood.server.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.picfood.server.config.JwtUtil.USER_ID;

/**
 * Created by shawn on 2018/3/21.
 */
@RestController
public class SocialController {

    private final SocialService socialService;
    private final UpvoteService upvoteService;
    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public SocialController(SocialService socialService,UpvoteService upvoteService,PostService postService,CommentService commentService) {
        this.socialService = socialService;
        this.upvoteService = upvoteService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/api/follow")
    public Object follow(@RequestHeader(value = USER_ID) String userId, @RequestParam String id) {
        return socialService.follow(userId, id);
    }

    @PostMapping("/api/unfollow")
    public Object unfollow(@RequestHeader(value = USER_ID) String userId, @RequestParam String id) {
        return socialService.unfollow(userId, id);
    }

    @GetMapping("/api/followers")
    public List<User> getFollowers(@RequestHeader(value = USER_ID) String userId) {
        return socialService.getFollowers(userId);
    }

    @GetMapping("/api/followings")
    public List<User> getFollowings(@RequestHeader(value = USER_ID) String userId) {
        return socialService.getFollowings(userId);
    }

    @GetMapping("/api/followers/{id}")
    public List<User> getFollowers(@RequestHeader(value = USER_ID) String userId, @PathVariable("id") String id) {
        return socialService.getFollowers(id);
    }

    @GetMapping("/api/followings/{id}")
    public List<User> getFollowings(@RequestHeader(value = USER_ID) String userId, @PathVariable("id") String id) {
        return socialService.getFollowings(id);
    }

    @GetMapping("/api/timeline")
    public List<Timeline> getTimeline(@RequestHeader(value = USER_ID) String userId) {
        List<User> followings = socialService.getFollowings(userId);
        List<Timeline> timelines = new ArrayList<>();
        for (User f : followings) {
            timelines.addAll(commentService.getCommentByUserId(f.getUserId()));
            timelines.addAll(upvoteService.getUpvoteByUserId(f.getUserId()));
            timelines.addAll(postService.getPostByUserId(f.getUserId()));
        }
        // timelines.sort((o1, o2) -> (o1.getTime().compareTo(o2.getTime())));
        return timelines;
    }

}
