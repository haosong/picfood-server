package com.picfood.server.controller;

import com.picfood.server.entity.*;
import com.picfood.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final UserService userService;
    private final DishService dishService;

    @Autowired
    public SocialController(SocialService socialService, UpvoteService upvoteService, PostService postService, CommentService commentService, UserService userService, DishService dishService) {
        this.socialService = socialService;
        this.upvoteService = upvoteService;
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
        this.dishService = dishService;
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
        return timelines.stream().map(this::addTimelineDetail).collect(Collectors.toList());
    }

    @GetMapping("/api/timeline/{id}")
    public List<Timeline> getTimelineByUserId(@RequestHeader(value = USER_ID) String userId, @PathVariable("id") String id) {
        List<Timeline> timelines = new ArrayList<>();
        timelines.addAll(commentService.getCommentByUserId(id));
        timelines.addAll(upvoteService.getUpvoteByUserId(id));
        timelines.addAll(postService.getPostByUserId(id));
        // timelines.sort((o1, o2) -> (o1.getTime().compareTo(o2.getTime())));
        return timelines.stream().map(this::addTimelineDetail).collect(Collectors.toList());
    }

    public Timeline addTimelineDetail(Timeline t) {
        User u = userService.getUserById(t.getUserId());
        t.setUserAvatar(u.getAvatar());
        t.setUserName(u.getName());
        try {
            Dish d = dishService.findByPostId(t.getPostId());
            if (d != null) t.setDishName(d.getName());
        } catch (NullPointerException ignored) {

        }
        return t;
    }

}
