package com.picfood.server.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Shuqi on 18/3/19.
 */
@Entity
@Table(name = "Follow")
@IdClass(FollowId.class)
public class Follow {
    @Id
    private String follower;

    @Id
    private String followee;

    @CreationTimestamp
    private Date timestamp;

    public Follow() {
    }

    public Follow(String follower, String followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowee() {
        return followee;
    }

    public void setFollowee(String followee) {
        this.followee = followee;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // public Date getTimestamp() {
    //     return timestamp;
    // }
    //
    // public void setTimestamp(Date timestamp) {
    //     this.timestamp = timestamp;
    // }
}
