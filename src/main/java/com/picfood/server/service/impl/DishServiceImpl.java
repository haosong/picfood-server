package com.picfood.server.service.impl;

import com.picfood.server.entity.Dish;
import com.picfood.server.repository.DishRepository;
import com.picfood.server.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Shuqi on 18/3/20.
 */
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository){this.dishRepository = dishRepository;}

    @Override
    public Object createDish(Dish dish) {
        return null;
    }

    @Override
    public void deleteDish(String dishId) {

    }
}
