package com.picfood.server.controller;
import com.picfood.server.entity.DTO.RestaurantDTO;
import com.picfood.server.entity.DTO.RestaurantSearchDTO;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Restaurant;
import com.picfood.server.repository.RestaurantRepository;
import com.picfood.server.service.DishService;
import com.picfood.server.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

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

    @GetMapping("/api/restaurants/{id}/category")
    public List<String> getAllDishesCategory(@PathVariable("id") String rid) {
        return restaurantService.getAllDishesCategory(rid);
    }

    @GetMapping("/api/restaurants/{id}/dishes")
    public List<Dish> getDishesByRestaurant(@PathVariable("id") String rid){
        return dishService.findByRestaurant(rid);
    }
    private RestaurantDTO convertToDTO(Restaurant restaurant){
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    @GetMapping("/api/restaurants")

    public List<Restaurant> getNearRestaurant(@RequestParam("lon") Double lon, @RequestParam("lat") Double lat){
       return restaurantService.getRestaurantByLocation(lon,lat);
    }

    @GetMapping("/api/search/restaurants")
    public List<RestaurantSearchDTO> searchRestaurants( @RequestParam(value = "keyword") String keyword, @RequestParam(value = "sorting") String sorting,
                                               @RequestParam(value = "lon") Double lon, @RequestParam(value = "lat") Double lat) {
        List<Restaurant> res = restaurantService.searchRestaurants(lon, lat, keyword);
        if (sorting.equals("distance")) {
            res.sort(Comparator.comparingDouble(a -> restaurantService.calcDistanceById(a.getRestaurantId(),lon,lat)));
        } else if (sorting.equals("rate")) {
            res.sort(Comparator.comparingDouble(a -> -a.getAvgRate()));
        }
        return res.stream().map(r->convertToSearchDTO(r, lon, lat)).collect(Collectors.toList());
    }
    private RestaurantSearchDTO convertToSearchDTO(Restaurant restaurant, double lon, double lat){
        RestaurantSearchDTO restaurantSearchDTO = modelMapper.map(restaurant, RestaurantSearchDTO.class);
        restaurantSearchDTO.setDistance(restaurantService.calcDistanceById(restaurant.getRestaurantId(), lon, lat));
        return restaurantSearchDTO;
    }



}
