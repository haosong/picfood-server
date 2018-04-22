package com.picfood.server;

import java.util.*;
import com.picfood.server.controller.SocialController;
import com.picfood.server.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Created by Shuqi on 18/4/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SocialTest {
    @Autowired
    SocialController socialController;

    @Test
    public void testFollow(){
        boolean result = (boolean)socialController.follow("2c9abebe625fe79701625fe925200000", "2c9f945c6297322f016297a04092000f");
        Assert.assertTrue(result);
        result = (boolean)socialController.unfollow("2c9abebe625fe79701625fe925200000", "2c9f945c6297322f016297a04092000f");
        Assert.assertTrue(result);
    }
    @Test
    public void testGetFollow(){
        List<User> followers = socialController.getFollowers("2c9f945c6297322f0162a3422b2a0041");
        Assert.assertTrue(followers.size() > 0 && followers.get(0).getUserId().equals("2c9f945c62eea5b10162eea611fb0000"));
        followers = socialController.getFollowers("2c9f945c6297322f0162a3422b2a0041","2c9f945c6297322f0162a3422b2a0041");
        Assert.assertTrue(followers.size() > 0 && followers.get(0).getUserId().equals("2c9f945c62eea5b10162eea611fb0000"));

        List<User> followings = socialController.getFollowings("2c9abebe625fd50801625fd6c27b0001");
        Assert.assertTrue(followings.size()>0 && followings.get(0).getUserId().equals("2c9abebe625fd50801625fd5e0390000"));
        followings = socialController.getFollowings("2c9abebe625fd50801625fd6c27b0001","2c9abebe625fd50801625fd6c27b0001");
        Assert.assertTrue(followings.size()>0 && followings.get(0).getUserId().equals("2c9abebe625fd50801625fd5e0390000"));

    }
    @Test
    public void testGetTimeLine(){
        List<Timeline> result = socialController.getTimeline("2c9abebe625fd50801625fd6c27b0001");
        Assert.assertTrue(result.size()>0);
        result = socialController.getTimelineByUserId("2c9abebe625fd50801625fd6c27b0001","2c9abebe625fd50801625fd6c27b0001");
        Assert.assertTrue(result.size() > 0);
    }



}
