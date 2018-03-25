package com.picfood.server.repository;
import java.util.*;
import com.picfood.server.entity.Dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface DishRepository extends JpaRepository<Dish, String> {
    public Dish findByDishId(String id);

    public Dish findByRestaurantIdAndName(String rid, String name);

    public List<Dish> findByName(String Name);

    public List<Dish> findByRestaurantId(String rid);

    public List<Dish> findByCategory(String category);

    @Query("select d.name from Dish as d where d.restaurantId = :id")
    public List<String> findNameByRestaurantId(@Param("id") String id);

    public List<Dish> findByNameContainingOrCategoryContaining(String word1, String word2);

    @Query("select distinct d.category from Dish as d where d.restaurantId = :id")
    public List<String> findDistinctCategoryByRestaurantId(@Param("id") String id);

    @Query(value = "SELECT d.*\n" +
            "        FROM restaurant r, dish d\n" +
            "        WHERE r.restaurant_id = d.restaurant_id and (d.category like %:keyword% or d.name like %:keyword%) and MOD(ABS(r.longitude - :lng),360) < 10 and ABS(r.latitude - :lat) < 10"
            ,nativeQuery =  true)
    public List<Dish> searchDishes(@Param("lng") double log, @Param("lat") double lat, @Param("keyword") String keyword);

}