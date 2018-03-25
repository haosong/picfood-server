package com.picfood.server.service;

import com.picfood.server.entity.User;

import java.util.List;

/**
 * Created by shawn on 2018/3/16.
 */
public interface UserService {
    public boolean validatePassword(User user);

    public User getUserById(String id);

    public User updateUser(User user);

    public User createUser(User user);

    public List<User> getUsersByName(String name);
}
