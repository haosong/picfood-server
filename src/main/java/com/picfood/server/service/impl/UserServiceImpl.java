package com.picfood.server.service.impl;

import com.picfood.server.entity.User;
import com.picfood.server.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by shawn on 2018/3/16.
 */
@Service
public class UserServiceImpl implements UserService {
    public boolean validatePassword(User user) {
        if ("admin".equals(user.getEmail()) && "admin".equals(user.getPassword())) {
            user.setUserId(1);
            return true;
        }
        return false;
    }

}
