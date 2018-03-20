package com.picfood.server.repository;
import java.util.*;
import com.picfood.server.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, String> {
    public Dish findByDishId(String id);

    public Dish findByRestaurantIdAndName(String rid, String name);

    public List<Dish> findByName(String Name);
}