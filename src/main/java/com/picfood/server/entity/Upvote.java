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
    private String upVoteId;

    private String postId;
    private String userId;

    @CreationTimestamp
    private Date timestamp;

    public Upvote() {
    }

    public String getUpVoteId() {
        return upVoteId;
    }

    public void setUpVoteId(String upVoteId) {
        this.upVoteId = upVoteId;
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
