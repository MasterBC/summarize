package com.app.gaolonglong.gankgirl.retrofit.github;

import com.app.gaolonglong.gankgirl.model.github.Following;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/5/28 0028.
 */
public interface FollowingService {
    @GET("users/{user}/following")
    Observable<List<Following>> getFollowings(
            @Path("user") String user,
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}
