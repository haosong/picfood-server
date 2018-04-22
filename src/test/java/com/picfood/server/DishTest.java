package com.picfood.server;

import java.util.*;
import com.picfood.server.controller.DishController;
import com.picfood.server.entity.DTO.DishDTO;
import com.picfood.server.entity.DTO.DishSearchDTO;
import com.picfood.server.entity.DTO.PostDTO;
import com.picfood.server.entity.Dish;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Shuqi on 18/4/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DishTest {
    @Autowired
    DishController dishController;

    @Test
    public void testGetDishInfo(){
        Dish dish = (Dish)dishController.getDishInfo("2c9f945c6289fec401628c2038e2000d");
        Assert.assertTrue(dish.getName().equals("Chaofeng"));
    }
    @Test
    public void testGetDishImage(){
        List<String> result = dishController.getDishImages("2c9f945c6289fec401628c2038e2000d");
        Assert.assertTrue(result.size()> 0 && result.get(0).equals("https://s3.us-east-1.amazonaws.com/picfoodphotos/1522769326345-photo.jpg"));
    }
    @Test
    public void testGetDishPosts(){
        List<PostDTO> result = dishController.getDishPosts("2c9f945c6289fec401628c2038e2000d");
        Assert.assertTrue(result.size()>0 && result.get(0).getPostId().equals("2c9f945c6289fec401628c2038e2000e"));
    }
    @Test
    public void testGetDish(){
        DishDTO result = (DishDTO) dishController.getDish("2c9f945c6289fec401628c2038e2000d");
        Assert.assertTrue(result!=null && result.getName().equals("Chaofeng"));
    }
    @Test
    public void testSearchDishes(){
        List<DishSearchDTO> result = dishController.searchDishes("Chaofeng","distance", -72.92,41.30, 100.);
        Assert.assertTrue(result.size() > 0 && result.get(0).getName().equals("Chaofeng"));
        List<DishSearchDTO> result1 = dishController.searchDishes("Chaofeng", "rate", -72.92,41.30, 100.);
        Assert.assertTrue(result1.size() > 0 && result1.get(0).getName().equals("Chaofeng"));

    }

}
