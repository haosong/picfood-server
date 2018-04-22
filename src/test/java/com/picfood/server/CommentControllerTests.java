package com.picfood.server;

import com.picfood.server.controller.CommentController;
import com.picfood.server.entity.Comment;
import com.picfood.server.entity.DTO.CommentDTO;
import com.picfood.server.repository.CommentRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerTests {

    @Autowired
    private CommentController commentController;

    private String userId;
    private String postId;
    private String content;
    private String commentId;

    public CommentControllerTests() {
        commentId = "";
        userId = "2c9abebe625fd50801625fdf3d7a000a";
        postId = "2c9bdc1e625aa9fc01625aae0f070002";
        content = "this is a content";
    }

    @Before
    public void makeComment() {
        Map<String, String> map = new HashMap<>();
        map.put("postId", postId);
        map.put("content", content);
        commentId = ((CommentDTO)commentController.makeComment(userId, map)).getCommentId();
    }

    @Test
    public void testGetComment() {
        Object result = commentController.getComments(postId);
        Assert.assertTrue(result instanceof List);
        Assert.assertTrue(((List)result).size() > 0);
        int cnt = 0;
        for (int i = 0; i < ((List)result).size(); i++) {
            CommentDTO comment = (CommentDTO) ((List)result).get(i);
            if (comment.getCommentId().equals(commentId)) {
                Assert.assertTrue(comment.getCommenterId().equals(userId));
                Assert.assertTrue(comment.getPostId().equals(postId));
                cnt++;
            }
        }
        Assert.assertTrue(cnt == 1);
    }

    @After
    public void deleteComment() {
        Map<String, String> map = new HashMap<>();
        map.put("commentId", commentId);
        commentController.deleteComment(map);
    }
}
