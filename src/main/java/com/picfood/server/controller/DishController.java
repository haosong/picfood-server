package com.picfood.server.controller;

import com.picfood.server.entity.DTO.PostDTO;
import com.picfood.server.entity.DTO.RestaurantDTO;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Post;
import com.picfood.server.entity.Restaurant;
import com.picfood.server.service.CommentService;
import com.picfood.server.service.DishService;
import java.util.*;
import java.util.stream.Collectors;

import com.picfood.server.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shuqi on 18/3/20.
 */
@RestController
public class DishController {
    private final DishService dishService;
    private final PostService postService;
    private final CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public DishController(DishService dishService, PostService postService, CommentService commentService){
        this.dishService = dishService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/api/dishes/{id}/info")
    public Object getDishInfo(@PathVariable("id")String id){
        return dishService.findByDishId(id);
    }

    @GetMapping("/api/dishes/{id}/images")
    public Object getDishImages(@PathVariable("id") String id){
        return postService.getImagesByDishId(id);
    }
    @GetMapping("/api/dishes/{id}/post")
    public List<PostDTO> getDishPosts(@PathVariable("id") String id){

        List<Post> posts = postService.getPostByDishId(id);
        List<PostDTO> result = posts.stream().map(this::convertToDTO).collect(Collectors.toList());
        result.forEach(p -> p.setCommentCount(commentService.getCommentCountByPostId(p.getPostId())));
        return result;
    }
    private PostDTO convertToDTO(Post post){
        return modelMapper.map(post, PostDTO.class);
    }



}
