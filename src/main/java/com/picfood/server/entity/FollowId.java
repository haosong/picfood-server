package com.picfood.server.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 2018/3/21.
 */
@IdClass(FollowId.class)
public class FollowId implements Serializable {

    private String follower;

    private String followee;


    public FollowId() {

    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public int hashCode() {
        String res = follower + ":" + followee;
        return res.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final FollowId objId = (FollowId) obj;
        return objId.hashCode() == this.hashCode();
    }
}
