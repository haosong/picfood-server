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

}