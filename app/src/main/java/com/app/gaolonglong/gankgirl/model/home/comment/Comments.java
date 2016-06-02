package com.app.gaolonglong.gankgirl.model.home.comment;

import com.app.gaolonglong.gankgirl.model.home.User;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class Comments {
    private String body;
    private User user;//得到user中的usrname和头像

    public Comments(String body, User user) {
        this.body = body;
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
