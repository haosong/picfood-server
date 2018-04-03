package com.picfood.server.controller;

import com.picfood.server.config.JwtUtil;
import com.picfood.server.entity.User;
import com.picfood.server.service.SocialService;
import com.picfood.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.picfood.server.config.JwtUtil.USER_ID;

/**
 * Created by shawn on 2018/3/15.
 */
@RestController
public class UserController {

    private final UserService userService;
    private final SocialService socialService;

    @Autowired
    public UserController(UserService userService, SocialService socialService) {
        this.userService = userService;
        this.socialService = socialService;
    }


    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> loginMap) {
        User user = new User();
        user.setEmail(loginMap.get("email"));
        user.setPassword(loginMap.get("password"));
        if (userService.validatePassword(user)) {
            System.out.println(user.getUserId());
            String jwtToken = JwtUtil.generateToken(user.getUserId());
            // CookieUtil.create(response, "JWT-TOKEN", jwtToken, false, -1, "localhost");
            System.out.println("Success");
            return new HashMap<String, String>() {{
                put("token", jwtToken);
            }};
        } else {
            System.out.println("Fail");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public Object register(@RequestBody Map<String, String> loginMap) {
        User user = new User();
        user.setEmail(loginMap.get("email"));
        user.setPassword(loginMap.get("password"));
        user.setName(loginMap.getOrDefault("name", ""));
        user.setAvatar(loginMap.getOrDefault("avatar", ""));
        User createdUser = userService.createUser(user);
        if (null == createdUser) {
            return new HashMap<String, String>() {{
                put("error", "Email existed");
            }};
        } else {
            String jwtToken = JwtUtil.generateToken(createdUser.getUserId());
            return new HashMap<String, String>() {{
                put("token", jwtToken);
            }};
        }
    }

    @GetMapping("/api/users/me")
    // @ResponseBody
    public Object getUser(@RequestHeader(value = USER_ID) String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/api/users/search")
    public List<User> getUsersByName(@RequestHeader(value = USER_ID) String userId, @RequestParam(value = "name") String name) {
        List<User> userList = userService.getUsersByName(name);
        for (User u : userList)
            u.setFollowing(socialService.isFollow(userId, u.getUserId()));
        return userList;
    }

    @GetMapping("/api/users/{id}")
    public Object getUserByUserId(@RequestHeader(value = USER_ID) String userId, @PathVariable("id") String id) {
        User u = userService.getUserById(id);
        u.setFollowing(socialService.isFollow(userId, id));
        return u;
    }

    @PostMapping("/api/users/me")
    public Object modifyUser(@RequestHeader(value = USER_ID) String userId, @RequestBody final User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/api/users/changepassword")
    public boolean changePassword(@RequestHeader(value = USER_ID) String userId, @RequestBody Map<String, String> password) {
        return false;
    }

}
