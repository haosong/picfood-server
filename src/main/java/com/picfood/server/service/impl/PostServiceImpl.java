package com.picfood.server.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.picfood.server.entity.*;
import com.picfood.server.entity.DTO.CommentDTO;
import com.picfood.server.entity.DTO.PostDTO;
import com.picfood.server.repository.*;
import com.picfood.server.service.AmazonClient;
import com.picfood.server.service.CommentService;
import com.picfood.server.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.print.attribute.standard.Destination;
import javax.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UpvoteRepository upvoteRepository;
    @Autowired
    private AmazonClient amazonClient;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CommentService commentService;


    public PostDTO createPost(String uid, Map<String, String> postMsg) {
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
        //create post
        Post post = new Post();
        post.setContent(postMsg.get("content"));
        post.setDishId(dish.getDishId());
        post.setCreatorId(uid);
        post.setUpvoteCount(0);
        int rate = Integer.parseInt(postMsg.get("rate"));
        post.setRate(rate);
        //update dish rate
        dish.setAvgRate((dish.getAvgRate() * dish.getPostNum() + rate) / (dish.getPostNum() + 1));
        dish.setPostNum(dish.getPostNum() + 1);
        //save image
        post.setImageUrl(postMsg.get("imageUrl"));
        postRepository.save(post);
        return getPost(post.getPostId(), false);
    }

    public void deletePost(String postId) {
        Post post = postRepository.findByPostId(postId);
        if (post == null) {
            return;
        }
        //update dish rate
        Dish dish = dishRepository.findByDishId(post.getDishId());
        if (dish != null) {
            int postNum = dish.getPostNum();
            if (postNum <= 1) {
                dish.setAvgRate(0);
                dish.setPostNum(0);
            } else {
                dish.setAvgRate((dish.getAvgRate() * postNum - post.getRate()) / (postNum - 1));
                dish.setPostNum(postNum - 1);
            }
        }
        upvoteRepository.deleteAllByPostId(postId);
        amazonClient.deleteFileFromS3Bucket(post.getImageUrl());
        postRepository.deleteByPostId(postId);
    }

    public PostDTO getPost(String postId, boolean hasComment) {
        Post post = postRepository.findByPostId(postId);
        if (post == null) {
            return null;
        }
        return convertToDTO(post, hasComment);
    }

    public PostDTO convertToDTO(Post post, boolean hasComment) {
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        Dish dish = dishRepository.findByDishId(post.getDishId());
        if (dish == null) {
            throw new NoSuchElementException("Dish doesn't exist.");
        }
        postDTO.setDishName(dish.getName());
        postDTO.setRestaurantName(restaurantRepository.findByRestaurantId(dish.getRestaurantId()).getName());
        postDTO.setRestaurantId((dish.getRestaurantId()));
        User creator = userRepository.findByUserId(post.getCreatorId());
        if (creator == null) {
            throw new NoSuchElementException("User doesn't exist.");
        }
        postDTO.setCreatorAvater(creator.getAvatar());
        postDTO.setCreator(creator.getName());
        if (hasComment) {
            postDTO.setComments(commentService.getComments(post.getPostId()));
        }
        return postDTO;
    }


    public List<String> getImagesUrlsByDishId(String dishId) {
        return postRepository.findImagesByDishId(dishId);
    }

    public List<Post> getPostByDishId(String dishId) {
        return postRepository.findAllByDishId(dishId);
    }

    public List<Post> getPostByUserId(String userId) {
        return postRepository.findAllByCreatorId(userId);
    }
}
