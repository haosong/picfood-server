package com.picfood.server.service;

import com.picfood.server.entity.Dish;

/**
 * Created by Shuqi on 18/3/20.
 */
public interface DishService {
    public Object createDish(Dish dish);
    public void deleteDish(String dishId);
}
