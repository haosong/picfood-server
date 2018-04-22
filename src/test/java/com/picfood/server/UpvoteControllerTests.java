package com.picfood.server;

import com.picfood.server.controller.UpvoteController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpvoteControllerTests {
    @Autowired
    UpvoteController upvoteController;

    private String userId;
    private String postId;
    private String upvoteId;

    public UpvoteControllerTests() {
        upvoteId = "";
        userId = "2c9abebe625fd50801625fdf3d7a000a";
        postId = "2c9bdc1e625aa9fc01625aae0f070002";
    }

    @Before
    public void upvote() {
        Map<String, String> map = new HashMap<>();
        map.put("postId", postId);
        upvoteId = upvoteController.upvote(userId, map).getUpvoteId();
    }

    @Test
    public void testHasUpvoted() {
        String res = upvoteController.hasUpvoted(userId, postId);
        Assert.assertTrue(res.equals(upvoteId));
    }

    @After
    public void downVote() {
        Map<String, String> map = new HashMap<>();
        map.put("upvoteId", upvoteId);
        upvoteController.deleteUpvote(map);
    }
}
