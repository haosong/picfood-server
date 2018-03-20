package com.picfood.server.controller;

import com.picfood.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuqi on 18/3/18.
 */
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){this.restaurantService = restaurantService;}

    @PostMapping("/api/restaurants/{restaurant_id}/info")
    public Object getRestaurantInfo(@PathVariable("restaurant_id") String restaurantId){
        return restaurantService.getRestaurantById(restaurantId);
    }

}
