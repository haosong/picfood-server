package com.picfood.server.entity;

/**
 * Created by Shuqi on 18/3/18.
 */
public class SearchCondition {
    String content;
    float longitude;
    float latitude;
    int searchType = 0;

    public SearchCondition(String content, float longitude, float latitude, int searchType) {
        this.content = content;
        this.longitude = longitude;
        this.latitude = latitude;
        this.searchType = searchType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
