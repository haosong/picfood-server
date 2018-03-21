package com.picfood.server.service;
import java.util.*;

import com.picfood.server.entity.Image;
import com.picfood.server.entity.Post;

public interface PostService {
    public Post createPost(String uid, Map<String, String> postMsg);
    public void deletePost(String postId);
    public Post getPost(String postId);

    List<String> getImagesByDishId(String dishId);
}
