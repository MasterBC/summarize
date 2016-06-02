package com.app.gaolonglong.gankgirl.ui.activity.github;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.adapter.github.FollowingAdapter;
import com.app.gaolonglong.gankgirl.model.github.Following;
import com.app.gaolonglong.gankgirl.retrofit.github.FollowingRetrofit;
import com.app.gaolonglong.gankgirl.retrofit.github.FollowingService;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FolllowingsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{

    private EasyRecyclerView recyclerView_following;
    FollowingAdapter followingAdapter;
    private List<Following> followings;
    private Handler handler = new Handler();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folllowings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FolllowingsActivity.this,RepoActivity.class));
            }
        });
    }

    private void initView(){

        followings = new ArrayList<>();
        recyclerView_following = (EasyRecyclerView)findViewById(R.id.recyclerview_following);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView_following.setLayoutManager(staggeredGridLayoutManager);

        followingAdapter = new FollowingAdapter(this);
        recyclerView_following.setAdapterWithProgress(followingAdapter);
        followingAdapter.setMore(R.layout.load_more_layout,this);
        followingAdapter.setNoMore(R.layout.no_more_layout);
        followingAdapter.setError(R.layout.error_layout);
        followingAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(FolllowingsActivity.this,Following_detailActivity.class);
                intent.putExtra("following_url",followings.get(position).getHtml_url());
                startActivity(intent);
            }
        });

        recyclerView_following.setRefreshListener(this);
        onRefresh();
    }

    private void getData(String name,int page,int per_page) {
        FollowingRetrofit.getRetrofit()
                .create(FollowingService.class)
                .getFollowings(name, page, per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Following>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerView_following,"没网",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Following> followingList) {
                        followings = followingList;
                        followingAdapter.addAll(followings);
                    }
                });
    }


    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                followingAdapter.clear();
                getData("yamaidie", 1, 15);
                page=2;
            }

        },1000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //followingAdapter.clear();//加上clear就不会造成per_page之后点击position混乱，但是由于不断清空，加载变慢而且之前的全部消失
                getData("yamaidie", page, 15);
                page++;//能得到全部的头像，但是只有前15张的点击是有效的，其它头像的position就是乱的，为什么？
            }
        }, 1000);
    }

}
