package com.picfood.server;

import com.picfood.server.controller.BucketController;
import com.picfood.server.controller.PostController;
import com.picfood.server.controller.RestaurantController;
import com.picfood.server.entity.DTO.PostDTO;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostControllerTests {
    @Autowired
    private PostController postController;

    private String userId;
    private String postId;
    private String restaurantId;
    private String dishName;
    private String category;
    private String content;
    private String rate;
    private String url;

    public PostControllerTests() {
        userId = "2c9abebe625fd50801625fdf3d7a000a";
        restaurantId = "cajun-boiled-seafood-ct-new-haven";
        dishName = "dishhhhh";
        category = "thai";
        content = "so good.";
        rate = "4";
        url = "fakeUrl";
    }

    @Before
    public void post() {
        Map<String, String>  map = new HashMap<>();
        map.put("restaurantId",restaurantId);
        map.put("dishName", dishName);
        map.put("category", category);
        map.put("content", content);
        map.put("rate", rate);
        map.put("imageUrl", url);
        Object postResult = postController.post(userId, map); //post a new dish
        postId = ((PostDTO)postResult).getPostId();
    }

    @Test
    public void testGetPost() {
        Object result = postController.getPost(postId); //get the posted dish
        Assert.assertTrue(result instanceof PostDTO);
        Assert.assertTrue(((PostDTO)result).getRestaurantId().equals(restaurantId));
        Assert.assertTrue(((PostDTO)result).getDishName().equals(dishName));
        Assert.assertTrue(((PostDTO)result).getContent().equals(content));
        Assert.assertTrue(((PostDTO)result).getRate()==Integer.parseInt(rate));
        Assert.assertTrue(((PostDTO)result).getImageUrl().equals(url));
    }

    @After
    public void deletePost() {
        Map<String, String> map = new HashMap<>();
        map.put("postId", postId);
        postController.deletePost(map);
    }
}
