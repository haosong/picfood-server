package com.picfood.server.service.impl;

import java.util.*;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Image;
import com.picfood.server.entity.Post;
import com.picfood.server.repository.DishRepository;
import com.picfood.server.repository.ImageRepository;
import com.picfood.server.repository.PostRepository;
import com.picfood.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final DishRepository dishRepository;
    private final ImageRepository imageRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository, DishRepository dishRepository, ImageRepository imageRepository) {
        this.postRepository = postRepository;
        this.dishRepository = dishRepository;
        this.imageRepository = imageRepository;
    }

    public Post createPost(String uid, Map<String, String> postMsg) {
        String restaurantId = postMsg.get("restaurantId");
        String dishName = postMsg.get("dishName");
        Dish dish = dishRepository.findByRestaurantIdAndName(restaurantId, dishName);
        if (dish == null) {
            dish = new Dish();
            dish.setName(dishName);
            dish.setRestaurantId(restaurantId);
            dish.setCategory(postMsg.get("category"));
            dishRepository.save(dish);
        }

        Post post = new Post();
        post.setContent(postMsg.get("content"));
        post.setDishId(dish.getDishId());
        if (postMsg.get("image") != null) {
            //TO-DO: get image url
            post.setImageId(postMsg.get("image"));
        }
        post.setUserId(uid);
        post.setUpvoteCount(0);
        return postRepository.save(post);
    }

    public void deletePost(String postId) {
        postRepository.deleteByPostId(postId);
    }

    public Post getPost(String postId) {
        return postRepository.findByPostId(postId);
    }

    public List<String> getImagesUrlsByDishId(String dishId) {
        return postRepository.findImagesByDishId(dishId);
    }

    public List<Image> getImagesByDishId(String dishId){
        List<String> imageUrls = getImagesUrlsByDishId(dishId);
        return imageRepository.findAllById(imageUrls);
    }

    public List<Post> getPostByDishId(String dishId) {
        return postRepository.findAllByDishId(dishId);
    }
}
