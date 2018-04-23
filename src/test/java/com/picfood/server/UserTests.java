package com.picfood.server;

import com.picfood.server.controller.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shuqi on 18/4/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserTests {
    @Autowired
    UserController userController;

    @Test
    public void testRegisterAndLogin(){
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("email","testa");
        loginMap.put("password","testa");
        HashMap<String,String> res = (HashMap<String,String>)userController.register(loginMap);
        Assert.assertTrue(res.containsKey("token"));
        res = (HashMap<String,String>)userController.login(loginMap);
        Assert.assertTrue(res.containsKey("token"));
    }

    @Test
    public void testGetUser(){
        Assert.assertTrue(userController.getUser("2c9f945c6297322f0162a33ceaeb0037")!=null);
        Assert.assertTrue(userController.getUsersByName("2c9f945c6297322f0162a33ceaeb0037","shuqi")!=null);
        Assert.assertTrue(userController.getUserByUserId("2c9f945c6297322f0162a33ceaeb0037","2c9f945c62efdf0c0162eff4703d0012")!=null);

    }
}
