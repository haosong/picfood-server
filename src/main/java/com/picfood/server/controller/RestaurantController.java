package com.picfood.server.controller;
import com.picfood.server.config.GeoHash;
import com.picfood.server.entity.DTO.RestaurantDTO;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Restaurant;
import com.picfood.server.service.DishService;
import com.picfood.server.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Shuqi on 18/3/18.
 */
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final DishService dishService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, DishService dishService){
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping("/api/restaurants/{id}/info")
    public Object getRestaurantInfo(@PathVariable("id") String restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        RestaurantDTO dto = convertToDTO(restaurant);
        List<String> dishes = dishService.findNameByRestaurant(dto.getRestaurantId());
        dto.setDishes(dishes);
        return dto;
    }
    @GetMapping("/api/restaurants/{id}/dishes")
    public List<Dish> getDishesByRestaurant(@PathVariable("id") String rid){
        return dishService.findByRestaurant(rid);
    }
    private RestaurantDTO convertToDTO(Restaurant restaurant){
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @GetMapping("/api/restaurants/{location}")
    public List<Object[]> getNearRestaurant(@PathVariable("location") Object location){
       return restaurantService.getRestaurantByLocation(1L,1L);
    }


}
