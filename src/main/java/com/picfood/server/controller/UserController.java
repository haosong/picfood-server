package com.picfood.server.controller;

import com.picfood.server.config.JwtUtil;
import com.picfood.server.entity.User;
import com.picfood.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static com.picfood.server.config.JwtUtil.USER_ID;

/**
 * Created by shawn on 2018/3/15.
 */
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(HttpServletResponse response, @RequestBody final User user) throws IOException {
        if (userService.validatePassword(user)) {
            String jwtToken = JwtUtil.generateToken(user.getUserId().toString());
            // CookieUtil.create(response, "JWT-TOKEN", jwtToken, false, -1, "localhost");
            return new HashMap<String, String>() {{
                put("token", jwtToken);
            }};
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public Object register(@RequestBody final User user) {
        // Validate Email and Password. Can be done in front-end.
        return userService.createUser(user);
    }

    @GetMapping("/api/users/me")
    // @ResponseBody
    public Object getUser(@RequestHeader(value = USER_ID) String userId) {
        return userService.getUserById(Long.parseLong(userId));
    }

    @PostMapping("/api/users/me")
    public Object modifyUser(@RequestHeader(value = USER_ID) String userId, @RequestBody final User user) {
        return userService.updateUser(user);
    }

}
