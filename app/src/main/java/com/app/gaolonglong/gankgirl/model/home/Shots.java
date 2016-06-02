package com.app.gaolonglong.gankgirl.model.home;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class Shots {
    private int id;
    private String title;
    private String description;
    private int views_count;
    private int likes_count;
    private String updated_at;
    private String html_url;
    private Images images;
    private User user;
    private String comments_url;

    public Shots(int id, String title, String description, int views_count, int likes_count, String updated_at, String html_url, Images images, User user, String comments_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views_count = views_count;
        this.likes_count = likes_count;
        this.updated_at = updated_at;
        this.html_url = html_url;
        this.images = images;
        this.user = user;
        this.comments_url = comments_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }
}
