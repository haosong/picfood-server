package com.picfood.server.service.impl;

import com.picfood.server.entity.Restaurant;
import com.picfood.server.entity.SearchCondition;
import com.picfood.server.repository.RestaurantRepository;
import com.picfood.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Shuqi on 18/3/18.
 */
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){this.restaurantRepository = restaurantRepository;}


    @Override
    public Restaurant getRestaurantById(String id) {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurantByCondition(SearchCondition condition) {
        return null;
    }
}
