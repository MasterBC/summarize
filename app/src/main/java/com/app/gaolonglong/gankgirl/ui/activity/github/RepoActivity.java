package com.app.gaolonglong.gankgirl.ui.activity.github;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.adapter.github.RepoAdapter;
import com.app.gaolonglong.gankgirl.model.github.Repo;
import com.app.gaolonglong.gankgirl.retrofit.github.RepoRetrofit;
import com.app.gaolonglong.gankgirl.retrofit.github.RepoService;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {

    private EasyRecyclerView recyclerView_repos;
    private RepoAdapter repoAdapter;
    private List<Repo> repos;
    private Handler handler = new Handler();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RepoActivity.this,Repos_detailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        repos = new ArrayList<>();
        repoAdapter = new RepoAdapter(this);

        recyclerView_repos = (EasyRecyclerView)findViewById(R.id.recyclerview_repo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext()) ;
        recyclerView_repos.setLayoutManager(linearLayoutManager);

        recyclerView_repos.setAdapterWithProgress(repoAdapter);
        repoAdapter.setError(R.layout.error_layout);
        repoAdapter.setNoMore(R.layout.no_more_layout);
        repoAdapter.setMore(R.layout.load_more_layout,this);
        repoAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*Intent intent = new Intent(RepoActivity.this,Repos_detailsActivity.class);
                intent.putExtra("repoURL",repos.get(position).getHtml_url());
                startActivity(intent);*/
            }
        });

        recyclerView_repos.setRefreshListener(this);
        onRefresh();
    }

    private void getData(String name,int page,int per_page){
        RepoRetrofit.getRetrofit()
                .create(RepoService.class)
                .getRepos(name, page, per_page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerView_repos,"没网",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Repo> repoList) {
                        repos = repoList;
                        repoAdapter.addAll(repos);
                    }
                });
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView_repos.clear();
                getData("yamaidie",1,10);
                page = 2;
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData("yamaidie",page,10);
                page++;
            }
        }, 1000);
    }
}
