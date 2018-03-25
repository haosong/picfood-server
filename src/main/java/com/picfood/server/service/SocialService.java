package com.picfood.server.service;

import com.picfood.server.entity.User;

import java.util.List;

/**
 * Created by shawn on 2018/3/21.
 */
public interface SocialService {
    boolean follow(String user, String other);

    boolean unfollow(String user, String other);

    List<User> getFollowers(String userId);

    List<User> getFollowings(String userId);

    boolean isFollow(String follower_id, String following_id);
}
