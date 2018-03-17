package com.picfood.server.service;

import com.picfood.server.entity.User;

/**
 * Created by shawn on 2018/3/16.
 */
public interface UserService {
    public boolean validatePassword(User user);
}
