package com.picfood.server.controller;

import com.picfood.server.entity.DTO.PostDTO;
import com.picfood.server.entity.DTO.RestaurantDTO;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Post;
import com.picfood.server.entity.Restaurant;
import com.picfood.server.repository.RestaurantRepository;
import com.picfood.server.service.CommentService;
import com.picfood.server.service.DishService;
import java.util.*;
import java.util.stream.Collectors;

import com.picfood.server.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shuqi on 18/3/20.
 */
@RestController
public class DishController {
    private final DishService dishService;
    private final PostService postService;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    public DishController(DishService dishService, PostService postService, CommentService commentService){
        this.dishService = dishService;
        this.postService = postService;
    }

    @GetMapping("/api/dishes/{id}/info")
    public Object getDishInfo(@PathVariable("id")String id){
        return dishService.findByDishId(id);
    }

    @GetMapping("/api/dishes/{id}/images")
    public List<String> getDishImages(@PathVariable("id") String id){
        return postService.getImagesUrlsByDishId(id);
    }

    @GetMapping("/api/dishes/{id}/post")
    public List<PostDTO> getDishPosts(@PathVariable("id") String id){
        List<Post> posts = postService.getPostByDishId(id);
        return posts.stream().map(p -> postService.getPost(p.getPostId(), false)).collect(Collectors.toList());
    }

    @GetMapping("/search/dishes")
    public List<Dish> searchDishes( @RequestParam(value = "keyword") String keyword, @RequestParam(value = "sorting") String sorting,
                                               @RequestParam(value = "lon") Double lon, @RequestParam(value = "lat") Double lat) {
        List<Dish> res = dishService.searchDishes(lon, lat, keyword);
        System.out.println("1------>" + res.size());
        if (sorting.equals("distance")) {
            res.sort((a, b) -> {
                Restaurant ra = restaurantRepository.findByRestaurantId(a.getRestaurantId());
                Restaurant rb = restaurantRepository.findByRestaurantId(b.getRestaurantId());
                double dist1 = getDist(ra.getLongitude(), ra.getLatitude(), lon, lat);
                double dist2 = getDist(rb.getLongitude(), rb.getLatitude(), lon, lat);
                if (dist1 < dist2) {
                    return -1;
                } else {
                    return 1;
                }
            });
        } else if (sorting.equals("rate")) {
            res.sort((a, b) -> {
                if (a.getAvgRate() < b.getAvgRate()) {
                    return 1;
                } else {
                    return -1;
                }
            });
        }
        return res;
    }

    private double getDist(double lon1, double lat1, double lon2, double lat2) {
        return (lon1 - lon2) * (lon1 - lon2) + (lat1 - lat2) * (lat1 - lat2);
    }
}
