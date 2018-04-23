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
public class SocialTest {
    @Autowired
    SocialController socialController;

    @Test
    public void testFollow(){
        boolean result = (boolean)socialController.follow("2c9f945c62f029b80162f04560a20002", "2c9f945c62f029b80162f04560a20002");
        Assert.assertTrue(result);
        result = (boolean)socialController.unfollow("2c9f945c62f029b80162f04560a20002", "2c9f945c62f029b80162f04560a20002");
        Assert.assertTrue(result);
    }
    @Test
    @Transactional
    public void testGetFollow(){
        List<User> followers = socialController.getFollowers("2c9f945c62f029b80162f04560a20002");
        Assert.assertTrue(followers.size() > 0);
        followers = socialController.getFollowers("2c9f945c62f029b80162f04560a20002","2c9f945c62f029b80162f04560a20002");
        Assert.assertTrue(followers.size() > 0);

        List<User> followings = socialController.getFollowings("2c9f945c62f029b80162f04560a20002");
        Assert.assertTrue(followings.size()>0 );
        followings = socialController.getFollowings("2c9f945c62f029b80162f04560a20002","2c9f945c62f029b80162f04560a20002");
        Assert.assertTrue(followings.size()>0 );

    }
    @Test
    public void testGetTimeLine(){
        List<Timeline> result = socialController.getTimeline("2c9f945c62f029b80162f04560a20002", new Date());
        Assert.assertTrue(result!=null);
        result = socialController.getTimelineByUserId("2c9f945c62f029b80162f04560a20002","2c9f945c62f029b80162f04560a20002", new Date());
        Assert.assertTrue(result!=null);
    }



}
