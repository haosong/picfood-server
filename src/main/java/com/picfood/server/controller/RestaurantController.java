package com.picfood.server.controller;

import com.picfood.server.entity.DTO.RestaurantDTO;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Restaurant;
import com.picfood.server.repository.DishRepository;
import com.picfood.server.service.DishService;
import com.picfood.server.service.RestaurantService;
import com.sun.tools.javac.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuqi on 18/3/18.
 */
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;
    //TODO add dishservice
    private final DishService dishService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){this.restaurantService = restaurantService;}

    @PostMapping("/api/restaurants/{restaurant_id}/info")
    public Object getRestaurantInfo(@PathVariable("restaurant_id") String restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        RestaurantDTO dto = convertToDTO(restaurant);
        List<Dish> dishes = dishService.findNameByRestaurant();
        dto.setDishes(dishes);
        return dto;
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant){
        return modelMapper.map(restaurant, RestaurantDTO.class);

    }

}
