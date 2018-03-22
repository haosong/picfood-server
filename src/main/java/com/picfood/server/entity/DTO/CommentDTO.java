package com.picfood.server.entity.DTO;


import java.util.Date;

public class CommentDTO {
    private String commentId;
    private String postId;
    private String commenterId;
    private String commenter;
    private String commenterAvatar;
    private String content;
    private Date time;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(String commenterId) {
        this.commenterId = commenterId;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getCommenterAvatar() {
        return commenterAvatar;
    }

    public void setCommenterAvatar(String commenterAvatar) {
        this.commenterAvatar = commenterAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
