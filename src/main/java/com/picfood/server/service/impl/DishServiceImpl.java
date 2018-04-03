package com.picfood.server.service.impl;

import com.picfood.server.entity.Dish;
import com.picfood.server.repository.DishRepository;
import com.picfood.server.repository.PostRepository;
import com.picfood.server.service.DishService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Shuqi on 18/3/20.
 */
@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final PostRepository postRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, PostRepository postRepository) {
        this.dishRepository = dishRepository;
        this.postRepository = postRepository;
    }


    @Override
    public Object createDish(Dish dish) {
        return null;
    }

    @Override
    public void deleteDish(String dishId) {

    }

    public Dish findByPostId(String id) {
        return dishRepository.findByDishId(postRepository.findByPostId(id).getDishId());
    }

    @Override
    public Dish findByDishId(String id) {
        return dishRepository.findByDishId(id);
    }

    @Override
    public Dish findByRestaurantAndName(String rid, String name) {
        return dishRepository.findByRestaurantIdAndName(rid, name);
    }

    @Override
    public List<Dish> findByRestaurant(String rid) {
        return dishRepository.findByRestaurantId(rid);
    }

    @Override
    public List<Dish> findByName(String name) {
        return dishRepository.findByName(name);
    }

    @Override
    public List<Dish> findByCategory(String category) {
        return dishRepository.findByCategory(category);
    }

    @Override
    public List<String> findNameByRestaurant(String rid) {
        return dishRepository.findNameByRestaurantId(rid);
    }

    public List<Dish> searchDishes(String keyword) {
        return dishRepository.findByNameContainingOrCategoryContaining(keyword, keyword);
    }

    public List<Dish> searchDishes(double lon, double lat, String keyword) {
        return dishRepository.searchDishes(lon, lat, keyword);
    }
}
