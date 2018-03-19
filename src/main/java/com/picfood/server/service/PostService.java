package com.picfood.server.service;

import com.picfood.server.entity.PostMsg;

public interface PostService {
    public Object createPost(String uid, PostMsg postMsg);
    public void deletePost(String postId);
}
