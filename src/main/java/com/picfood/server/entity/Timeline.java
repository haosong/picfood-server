package com.picfood.server.entity;

import java.util.Date;

/**
 * Created by shawn on 2018/3/24.
 */
public abstract class Timeline {
    String userName;

    String userAvatar;

    String dishName;

    abstract public Date getTime();

    abstract public String getUserId();

    abstract public String getPostId();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
