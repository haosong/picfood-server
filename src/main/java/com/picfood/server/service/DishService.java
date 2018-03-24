package com.picfood.server.service;
import java.util.*;
import com.picfood.server.entity.Dish;


/**
 * Created by Shuqi on 18/3/20.
 */
public interface DishService {
    public Object createDish(Dish dish);
    public void deleteDish(String dishId);


    public Dish findByDishId(String id);

    public Dish findByRestaurantAndName(String rid, String name);

    public List<Dish> findByRestaurant(String rid);

    public List<Dish> findByName(String name);

    public List<Dish> findByCategory(String category);

    public List<String> findNameByRestaurant(String rid);

    public List<Dish> searchDishes(String keyword);
}
