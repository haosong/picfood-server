package com.picfood.server.repository;

import com.picfood.server.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Shuqi on 18/3/19.
 */
@Repository
public interface RestaurantRepository  extends JpaRepository<Restaurant, String> {
    public Restaurant findByRestaurantId(String id);
//    public Restaurant findByName(String name);
    @Query("select r from Restaurant as r where r.name like %:name%")
    public List<Restaurant> findByName(@Param("name") String name);

    @Query("select r from Restaurant as r where r.category like %:category%")
    public List<Restaurant> findByCategory(@Param("category") String category);

    @Query("select r from Restaurant as r where r.category like %:content% or r.name like %:content%")
    public List<Restaurant> searchByContent(@Param("content") String content);

    @Query(value = "SELECT   *,\n" +
            "        get_distance(latitude, longitude, :lat, :lng) AS distance  \n" +
            "        FROM restaurant as r  \n" +
            "        ORDER BY distance LIMIT 100 ",nativeQuery =  true)
    public List<Restaurant> findRestaurantByLocation(@Param("lng") double lon, @Param("lat")double lat);

    public List<Restaurant> findAllByNameContainingOrCategoryContaining(String word1, String word2);

    @Query(value = "SELECT   *\n" +
            "        FROM restaurant  \n" +
            "        WHERE (LOWER(category) like %:keyword% or LOWER(name) like %:keyword%) and get_distance(latitude, longitude, :lat, :lng) < :range"
            ,nativeQuery =  true)
    public List<Restaurant> searchRestaurants(@Param("lng") double lng, @Param("lat") double lat, @Param("range") double range, @Param("keyword") String keyword);

}
