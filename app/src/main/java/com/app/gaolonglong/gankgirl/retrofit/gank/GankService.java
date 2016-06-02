package com.app.gaolonglong.gankgirl.retrofit.gank;

import com.app.gaolonglong.gankgirl.model.gank.GanHuo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GankService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<GanHuo> getGanHuo(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
}
