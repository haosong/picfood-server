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
    private ImageRepository imageRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
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

        Post post = new Post();
        post.setContent(postMsg.get("content"));
        post.setDishId(dish.getDishId());
        post.setCreatorId(uid);
        post.setUpvoteCount(0);
        String imageUrl = postMsg.get("imageUrl");
        if (imageUrl != null) {
            Image image = new Image();
            image.setUrl(imageUrl);
            post.setImageId(image.getImageId());
            imageRepository.save(image);
        }
        postRepository.save(post);
        return getPost(post.getPostId(), false);
    }

    public void deletePost(String postId) {
        Post post = postRepository.findByPostId(postId);
        Image image = imageRepository.findByImageId(post.getImageId());
        amazonClient.deleteFileFromS3Bucket(image.getUrl());
        imageRepository.deleteByImageId(image.getImageId());
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
        postDTO.setDishName(dish.getName());
        postDTO.setRestaurantName(restaurantRepository.findByRestaurantId(dish.getRestaurantId()).getName());
        User creator = userRepository.findByUserId(post.getCreatorId());
        postDTO.setCreatorAvater(creator.getAvatar());
        postDTO.setCreator(creator.getName());
        postDTO.setImageUrl(imageRepository.findByImageId(post.getImageId()).getUrl());
        if (hasComment) {
            postDTO.setComments(commentService.getComments(post.getPostId()));
        }
        return postDTO;
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
