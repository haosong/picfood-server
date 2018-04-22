package com.picfood.server;
import java.util.*;
import com.picfood.server.controller.RestaurantController;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Restaurant;
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
public class RestaurantTests {
    @Autowired
    private RestaurantController restaurantController;

    @Test
    public void testGetRestaurantInfo(){
        Restaurant r1 = (Restaurant) restaurantController.getRestaurantInfo("basil-restaurant-new-haven-2");
        Assert.assertTrue(r1 != null || r1.getName().equals("Basil Restaurant"));

    }
    @Test
    public void testGetAllDishesCatagory(){
        List<String> result = restaurantController.getAllDishesCategory("basil-restaurant-new-haven-2");
        Assert.assertTrue(result.size() > 0 || result.get(0).equals("Asian Fusion"));
    }
    @Test
    public void testGetDishByRestaurant(){
        List<Dish> result = restaurantController.getDishesByRestaurant("basil-restaurant-new-haven-2");
        Assert.assertTrue(result.size() > 0 || result.get(0).getDishId().equals("2c9abebe625fd50801625fe33bb6000f"));

    }
    @Test
    public void testGetNearRestaurant(){
        List<Restaurant> result = restaurantController.getNearRestaurant(-72.9265664,41.3052498);
        Assert.assertTrue(result.size() > 0 || result.get(0).getRestaurantId().equals("cajun-boiled-seafood-ct-new-haven"));
    }
}