package com.picfood.server.controller;

import com.picfood.server.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.picfood.server.entity.User;

import java.util.List;

import static com.picfood.server.config.JwtUtil.USER_ID;

/**
 * Created by shawn on 2018/3/21.
 */
@RestController
public class SocialController {

    private final SocialService socialService;

    @Autowired
    public SocialController(SocialService socialService) {
        this.socialService = socialService ;
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
}
