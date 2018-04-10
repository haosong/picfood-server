package com.picfood.server.service;

import com.picfood.server.entity.Restaurant;
import com.picfood.server.entity.SearchCondition;

import java.util.List;

/**
 * Created by Shuqi on 18/3/18.
 */
public interface RestaurantService {
    public Restaurant getRestaurantById(String id);

    public List<Restaurant> getRestaurantByCondition(SearchCondition condition);

    public Object createRestaurant(Restaurant restaurant);

    public List<Restaurant> getRestaurantByLocation(double lon, double lat);

    public List<Restaurant> searchRestaurants(String keyword);

    public List<String> getAllDishesCategory(String rid);

    public List<Restaurant> searchRestaurants(double lon, double lat, String keyword);

    public double calcDistanceById(String id, double lon, double lat);
}
