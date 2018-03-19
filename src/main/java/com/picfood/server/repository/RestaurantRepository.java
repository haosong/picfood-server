package com.picfood.server.repository;

import com.picfood.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Shuqi on 18/3/19.
 */
public interface RestaurantRepository  extends JpaRepository<Restaurant, String> {
    public Restaurant findByRestaurantId(String id);
    public Restaurant findByName(String name);
//    public List<Restaurant> findRestaurantByLocation(float longitude, float latitude);
}
