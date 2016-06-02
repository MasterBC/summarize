package com.app.gaolonglong.gankgirl.retrofit.home;

import com.app.gaolonglong.gankgirl.model.home.Shots;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public interface ShotsService {
    @GET("shots")
    Observable<List<Shots>> getShots(
            @Query("page") int page,
            @Query("per_page") int per_page,
            @Query("access_token") String access_token
    );
}
