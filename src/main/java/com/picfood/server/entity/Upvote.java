package com.picfood.server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Shuqi on 18/3/19.
 */
@Entity
@Table(name = "Upvote")
public class Upvote {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")

    private String upvoteId;

    private String postId;
    private String userId;

    @CreationTimestamp
    private Date timestamp;

    public Upvote() {
    }

    public String getUpvoteId() {
        return upvoteId;
    }

    public void setUpvoteId(String UpvoteId) {
        this.upvoteId = upvoteId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
