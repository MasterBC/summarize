package com.app.gaolonglong.gankgirl.model.home;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class Dribbble_Following {
    private User followee;

    public Dribbble_Following(User followee) {
        this.followee = followee;
    }

    public User getFollowee() {
        return followee;
    }

    public void setFollowee(User followee) {
        this.followee = followee;
    }
}
