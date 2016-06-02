package com.app.gaolonglong.gankgirl.model.home;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class Images {
    private String normal;
    private String hidpi;

    public Images(String normal, String hidpi) {
        this.normal = normal;
        this.hidpi = hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }
}
