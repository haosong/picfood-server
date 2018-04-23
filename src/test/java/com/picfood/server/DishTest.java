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
        Dish dish = (Dish)dishController.getDishInfo("2c9f945c62f029b80162f053c8210017");
        Assert.assertTrue(dish.getName().equals("Salad"));
    }
    @Test
    public void testGetDishImage(){
        List<String> result = dishController.getDishImages("2c9f945c62f029b80162f053c8210017");
        Assert.assertTrue(result.size()> 0 && result.get(0).equals("https://s3.us-east-1.amazonaws.com/picfoodphotos/1524450427095-photo.jpg"));
    }
    @Test
    public void testGetDishPosts(){
        List<PostDTO> result = dishController.getDishPosts("2c9f945c62f029b80162f053c8210017");
        Assert.assertTrue(result.size()>0 );
    }
    @Test
    public void testGetDish(){
        DishDTO result = (DishDTO) dishController.getDish("2c9f945c62f029b80162f053c8210017");
        Assert.assertTrue(result!=null && result.getName().equals("Salad"));
    }
    @Test
    public void testSearchDishes(){
        List<DishSearchDTO> result = dishController.searchDishes("Salad","distance", -72.92,41.30, 100.);
        Assert.assertTrue(result.size() > 0 && result.get(0).getName().equals("Salad"));
        List<DishSearchDTO> result1 = dishController.searchDishes("Salad", "rate", -72.92,41.30, 100.);
        Assert.assertTrue(result1.size() > 0 && result1.get(0).getName().equals("Salad"));

    }

}
