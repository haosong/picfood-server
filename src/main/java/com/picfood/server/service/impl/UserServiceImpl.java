package com.picfood.server.service.impl;

import com.picfood.server.entity.User;
import com.picfood.server.repository.UserRepository;
import com.picfood.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shawn on 2018/3/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean validatePassword(User input) {
        User user = userRepository.findByEmail(input.getEmail());
        if (user != null && input.getPassword().equals(user.getPassword())) {
            input.setUserId(user.getUserId());
            return true;
        }
        return false;
    }

    public User getUserById(Long id) {
        return userRepository.findByUserId(id);
    }

    public Object createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        } else {
            return "Email existed";
        }
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
