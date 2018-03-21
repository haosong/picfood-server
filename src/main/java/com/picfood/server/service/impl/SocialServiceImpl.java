package com.picfood.server.service.impl;

import com.picfood.server.entity.Follow;
import com.picfood.server.entity.User;
import com.picfood.server.repository.FollowRepository;
import com.picfood.server.repository.UserRepository;
import com.picfood.server.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shawn on 2018/3/21.
 */
@Service
public class SocialServiceImpl implements SocialService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Autowired
    public SocialServiceImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean follow(String user, String other) {
        User follower = userRepository.findByUserId(user);
        User followee = userRepository.findByUserId(other);
        if (null != followee && null != follower) {
            followRepository.save(new Follow(user, other));
            return true;
        }
        return false;
    }

    @Override
    public boolean unfollow(String user, String other) {
        User follower = userRepository.findByUserId(user);
        User followee = userRepository.findByUserId(other);
        if (null != followee && null != follower) {
            followRepository.delete(new Follow(user, other));
            return true;
        }
        return false;
    }

    @Override
    public List<User> getFollowers(String userId) {
        List<String> followers_id = followRepository.findAllByFollowee(userId)
                .stream().map(Follow::getFollower).collect(Collectors.toList());
        return userRepository.findAllById(followers_id);
    }

    @Override
    public List<User> getFollowings(String userId) {
        List<String> followees_id = followRepository.findAllByFollower(userId)
                .stream().map(Follow::getFollowee).collect(Collectors.toList());
        return userRepository.findAllById(followees_id);
    }
}
