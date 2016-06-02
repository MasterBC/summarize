package com.app.gaolonglong.gankgirl.retrofit.home.comments;

import com.app.gaolonglong.gankgirl.model.home.comment.Comments;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public interface CommentService {
    @GET("shots/{id}/comments")
    Observable<List<Comments>> getComments(
            @Path("id") String id,
            @Query("page") int page,
            @Query("per_page") int per_page,
            @Query("access_token") String access_token
    );
}
