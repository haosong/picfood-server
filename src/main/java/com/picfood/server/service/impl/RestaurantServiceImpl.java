package com.picfood.server.service.impl;

import com.picfood.server.entity.Restaurant;
import com.picfood.server.entity.SearchCondition;
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

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){this.restaurantRepository = restaurantRepository;}


    @Override
    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findByRestaurantId(id);
    }

    @Override
    public List<Restaurant> getRestaurantByCondition(SearchCondition condition) {
        float longitude = condition.getLongitude();
        float latitude = condition.getLatitude();
        String content = condition.getContent();

        //TODO order by distance ( calc by longitude and latitude)
        List<Restaurant> results = restaurantRepository.searchByContent(content);

        return results;
    }

    public Object createRestaurant(Restaurant restaurant){
        return restaurant == null ? null:restaurantRepository.save(restaurant);
    }

    @Override
    public List<Object[]> getRestaurantByLocation(Long lon, Long lat) {
        return restaurantRepository.findRestaurantByLocation(lon,lat);
    }
}
