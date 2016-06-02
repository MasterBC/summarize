package com.app.gaolonglong.gankgirl.retrofit.home;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class ShotsRetrofit {
    private static final String DRIBBBLE_API = "https://api.dribbble.com/v1/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            synchronized (ShotsService.class){
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
