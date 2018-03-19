package com.picfood.server.controller;

import com.picfood.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuqi on 18/3/18.
 */
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){this.restaurantService = restaurantService;}

}
