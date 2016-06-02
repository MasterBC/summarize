package com.app.gaolonglong.gankgirl.retrofit.github;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/28 0028.
 */
public class FollowingRetrofit {
    private static final String GITHUB_API = "https://api.github.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            synchronized (FollowingService.class){
                if (retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(GITHUB_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
