package com.picfood.server.service.impl;

import com.picfood.server.entity.Restaurant;
import com.picfood.server.entity.SearchCondition;
import com.picfood.server.repository.DishRepository;
import com.picfood.server.repository.RestaurantRepository;
import com.picfood.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Shuqi on 18/3/18.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, DishRepository dishRepository){
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findByRestaurantId(id);
    }


    public Object createRestaurant(Restaurant restaurant){
        return restaurant == null ? null:restaurantRepository.save(restaurant);
    }

    @Override

    public List<Restaurant> getRestaurantByLocation(double lon, double lat) {
        return restaurantRepository.findRestaurantByLocation(lon,lat);
    }

    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findAllByNameContainingOrCategoryContaining(keyword, keyword);
    }

    public List<Restaurant> searchRestaurants(double lon, double lat, double range, String keyword) {
        return restaurantRepository.searchRestaurants(lon, lat, range, keyword);
    }

    @Override
    public double calcDistanceById(String id, double lon, double lat) {
        Restaurant restaurant = restaurantRepository.findByRestaurantId(id);
        return getDist(lon, lat, restaurant.getLongitude(), restaurant.getLatitude());
    }

    public List<String> getAllDishesCategory(String rid) {
        return dishRepository.findDistinctCategoryByRestaurantId(rid);
    }
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    private double EARTH_RADIUS = 6371;
    private double getDist(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        return s;
    }
}
