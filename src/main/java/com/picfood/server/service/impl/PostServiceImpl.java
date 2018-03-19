package com.picfood.server.service.impl;

import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Post;
import com.picfood.server.entity.PostMsg;
import com.picfood.server.repository.DishRepository;
import com.picfood.server.repository.PostRepository;
import com.picfood.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.postRepository = postRepository;
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }



    public Object createPost(String uid, PostMsg postMsg) {
        String restaurantName = postMsg.getRestaurantName();
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) {
            restaurant = new Restaurant();
            restaurant.setName(restaurantName);
            restaurantRepository.save(restaurant);
        }

        String dishName = postMsg.getDishName();
        Dish dish = dishRepository.findByRestaurantIdAndName(restaurant.getRestaurantId(), dishName);
        if (dish == null) {
            dish = new Dish();
            dish.setName(dishName);
            dish.setRestaurantId(restaurant.getRestaurantId());
            dishRepository.save(dish);
        }

        Post post = new Post();
        post.setContent(postMsg.getContent());
        post.setDishId(dish.getDishId());
        if (postMsg.getImageId() != null) {
            post.setImageId(postMsg.getImageId());
        }
        post.setUserId(uid);
        return postRepository.save(post);
    }

    public void deletePost(String postId) {
        postRepository.deleteByPostId(postId);
    }
}
