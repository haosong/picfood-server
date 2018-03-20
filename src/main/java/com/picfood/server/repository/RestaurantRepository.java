package com.picfood.server.repository;

import com.picfood.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Shuqi on 18/3/19.
 */
public interface RestaurantRepository  extends JpaRepository<Restaurant, String> {
    public Restaurant findByRestaurantId(String id);
//    public Restaurant findByName(String name);
    @Query("select * from Restaurant where name like %?%")

    public List<Restaurant> findByName(String name);

    @Query("select * from Restaurant where category like %?%")
    public List<Restaurant> findByCategory(String category);

    @Query("select * from Restaurant where category like %?% or name like %?%")
    public List<Restaurant> searchByContent(String content);
    public List<Restaurant> findRestaurantByLocation(float longitude, float latitude);
}
