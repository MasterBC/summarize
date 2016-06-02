package com.app.gaolonglong.gankgirl.model.home;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class User {//还可以当作followee使用
    private String username;
    private String html_url;
    private String avatar_url;
    private String location;
    private String bio;//个人简介
    private int followers_count;

    public User(String username, String html_url, String avatar_url, String location, String bio, int followers_count) {
        this.username = username;
        this.html_url = html_url;
        this.avatar_url = avatar_url;
        this.location = location;
        this.bio = bio;
        this.followers_count = followers_count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }
}
