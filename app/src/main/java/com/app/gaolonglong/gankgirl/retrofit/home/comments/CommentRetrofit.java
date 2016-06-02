package com.app.gaolonglong.gankgirl.retrofit.home.comments;

import com.app.gaolonglong.gankgirl.retrofit.home.ShotsService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class CommentRetrofit {
    private static final String DRIBBBLE_API = "https://api.dribbble.com/v1/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            synchronized (CommentService.class){
                if (retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(DRIBBBLE_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }
}
