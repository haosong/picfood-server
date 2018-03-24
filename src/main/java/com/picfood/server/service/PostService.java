package com.picfood.server.service;
import java.util.*;

import com.picfood.server.entity.DTO.PostDTO;
import com.picfood.server.entity.Post;

public interface PostService {
    public PostDTO createPost(String uid, Map<String, String> postMsg);
    public void deletePost(String postId);
    public PostDTO getPost(String postId, boolean hasComment);

    List<String> getImagesUrlsByDishId(String dishId);
    List<Post> getPostByDishId(String dishId);
    public PostDTO convertToDTO(Post post, boolean hasComment);
    public List<Post> getPostByUserId(String userId);
}
