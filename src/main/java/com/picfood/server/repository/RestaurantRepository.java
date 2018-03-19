package com.picfood.server.repository;

import com.picfood.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    public Restaurant findByName(String name);
}
