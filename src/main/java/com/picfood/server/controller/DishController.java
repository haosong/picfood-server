package com.picfood.server.controller;

import com.picfood.server.entity.DTO.DishDTO;
import com.picfood.server.entity.DTO.DishSearchDTO;
import com.picfood.server.entity.DTO.PostDTO;
import com.picfood.server.entity.DTO.RestaurantDTO;
import com.picfood.server.entity.Dish;
import com.picfood.server.entity.Post;
import com.picfood.server.entity.Restaurant;
import com.picfood.server.entity.User;
import com.picfood.server.repository.RestaurantRepository;
import com.picfood.server.service.CommentService;
import com.picfood.server.service.DishService;
import java.util.*;
import java.util.stream.Collectors;

import com.picfood.server.service.PostService;
import com.picfood.server.service.RestaurantService;
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
    private final RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
     public DishController(DishService dishService, PostService postService, RestaurantService restaurantService){
        this.dishService = dishService;
        this.postService = postService;
        this.restaurantService = restaurantService;
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

    @GetMapping("/api/dishes/{id}")
    public Object getDish(@PathVariable("id")String id){
        Dish dish = dishService.findByDishId(id);
        if(dish == null){
            return null;
        }
        DishDTO dishDTO = convertToDTO(dish);
        List<PostDTO> postDTOList = getDishPosts(id);
        dishDTO.setPosts(postDTOList);
        dishDTO.setRestaurantName(restaurantService.getRestaurantById(dish.getRestaurantId()).getName());
        return dishDTO;
    }
    @GetMapping("/api/search/dishes")
    public List<DishSearchDTO> searchDishes( @RequestParam(value = "keyword") String keyword, @RequestParam(value = "sorting") String sorting,
                                               @RequestParam(value = "lon") Double lon, @RequestParam(value = "lat") Double lat, @RequestParam(value = "range") Double range) {
        List<Dish> res = dishService.searchDishes(lon, lat, range, keyword.toLowerCase());
        if (sorting.equals("distance")) {
            res.sort(Comparator.comparingDouble(a -> restaurantService.calcDistanceById(a.getRestaurantId(),lon,lat))); //sort restaurants according to restaurant distance
        } else if (sorting.equals("rate")) {
            res.sort(Comparator.comparingDouble(a -> -a.getAvgRate())); // sort dishes according to rate
        }
        return res.stream().map(d -> converToDTO(d,lon,lat)).collect(Collectors.toList());
    }


    public DishDTO convertToDTO(Dish dish) {
        DishDTO dishDTO = modelMapper.map(dish, DishDTO.class);
        dishDTO.setRestaurantName(dish.getRestaurantId());
        return dishDTO;
    }

    public DishSearchDTO converToDTO(Dish dish, Double lon, Double lat){
        DishSearchDTO dishSearchDTO = modelMapper.map(dish, DishSearchDTO.class);
        dishSearchDTO.setRestaurantName(restaurantService.getRestaurantById(dish.getRestaurantId()).getName());
        dishSearchDTO.setDistance(restaurantService.calcDistanceById(dish.getRestaurantId(),lon,lat));
        dishSearchDTO.setImageUrls(postService.getImagesUrlsByDishId(dish.getDishId()));
        return dishSearchDTO;
    }
}
