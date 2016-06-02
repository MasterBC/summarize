package com.app.gaolonglong.gankgirl.ui.activity.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.adapter.home.comments.CommentsAdapter;
import com.app.gaolonglong.gankgirl.model.home.comment.Comments;
import com.app.gaolonglong.gankgirl.retrofit.home.comments.CommentRetrofit;
import com.app.gaolonglong.gankgirl.retrofit.home.comments.CommentService;
import com.app.gaolonglong.gankgirl.util.PatternUtil;
import com.app.gaolonglong.gankgirl.util.TimeUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Shots_detailsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {

    private String title;
    private String description;
    private String views_count;
    private String likes_count;
    private String updated_at;
    private String author;
    private String author_url;
    private String image;
    private String avatar;
    private String title_activity_shots_details;
    private String comments_url;
    private String id;

    private ImageView imageView;
    private TextView shots_detail_description;
    private TextView textView_shots_detail_viewcount;
    private TextView textView_shots_detail_likecount;
    private Button shots_description_loadmore;
    private TextView textView_shots_detail_title;
    private TextView textView_shots_detail_author;
    private ImageView imgView_shots_detail_avatar;
    private TextView textView_shots_detail_updated_at;

    private EasyRecyclerView recyclerView_shots_comment;
    private CommentsAdapter commentsAdapter;
    private List<Comments> commentsList;
    private Handler handler = new Handler();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String.format(title_activity_shots_details,title);
                if (null == description){
                    shots_detail_description.setText("TA没有添加描述");
                }else {
                    //正则表达式写得不好，但实现了想要的效果
                    //shots_detail_description.setText(description.replaceAll("\\<p>|</p>",""));
                    shots_detail_description.setText(description.replaceAll("\\<p>|</p>","").replaceAll("<a[^>]*>([^<]*)</a>","").replaceAll("<strong[^>]*>([^<]*)</strong>","").replaceAll("\\<br .*>",""));
                }
                shots_description_loadmore.setVisibility(view.getVisibility());
                shots_description_loadmore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shots_detail_description.setMaxLines(10);
                        shots_description_loadmore.setVisibility(view.INVISIBLE);
                    }
                });
            }
        });
    }

    private void init(){

        getData();

        initView();
    }

    private void getData(){
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        views_count = intent.getStringExtra("view_account");
        likes_count = intent.getStringExtra("like_account");
        updated_at = intent.getStringExtra("updated_at");
        image = intent.getStringExtra("image");
        author = intent.getStringExtra("author");
        author_url = intent.getStringExtra("author_url");
        avatar = intent.getStringExtra("avatar");
        comments_url = intent.getStringExtra("comments_url");

    }

    private void initView(){
        title_activity_shots_details = getResources().getString(R.string.title_activity_shots_details);
        //String.format(title_activity_shots_details,title);

        textView_shots_detail_title = (TextView)findViewById(R.id.textView_shots_detail_title);
        textView_shots_detail_title.setText(title);

        shots_detail_description = (TextView)findViewById(R.id.textView_shots_detail_description);
        shots_detail_description.setMaxLines(2);

        textView_shots_detail_author = (TextView)findViewById(R.id.textView_shots_detail_author);
        textView_shots_detail_author.setText(author);

        textView_shots_detail_updated_at = (TextView)findViewById(R.id.textView_shots_detail_updated_at);
        textView_shots_detail_updated_at.setText("更新时间： "+ TimeUtil.getdribbbleFormatTime(updated_at));

        imgView_shots_detail_avatar = (ImageView) findViewById(R.id.imgView_shots_detail_avatar);
        Glide.with(this)
                .load(avatar)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imgView_shots_detail_avatar.setImageBitmap(resource);
                    }
                });

        imgView_shots_detail_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shots_detailsActivity.this,AuthorActivity.class);
                intent.putExtra("author_url",author_url);
                startActivity(intent);
            }
        });

        textView_shots_detail_viewcount = (TextView)findViewById(R.id.textView_shots_detail_viewcount);
        textView_shots_detail_viewcount.setText(views_count + "人看过");
        textView_shots_detail_likecount = (TextView)findViewById(R.id.textView_shots_detail_likecount);
        textView_shots_detail_likecount.setText(likes_count + "人喜欢");

        shots_description_loadmore = (Button)findViewById(R.id.loadmore_shots_description);

        imageView = (ImageView)findViewById(R.id.shots_detail_image);
        Glide.with(this)
                .load(image)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);

                    }
                });


        //评论
        commentsList = new ArrayList<>();
        recyclerView_shots_comment = (EasyRecyclerView)findViewById(R.id.recyclerview_Shots_comments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) ;
        recyclerView_shots_comment.setLayoutManager(linearLayoutManager);

        commentsAdapter = new CommentsAdapter(this);
        recyclerView_shots_comment.setAdapterWithProgress(commentsAdapter);
        commentsAdapter.setMore(R.layout.load_more_layout,this);
        commentsAdapter.setNoMore(R.layout.no_more_layout);
        commentsAdapter.setError(R.layout.error_layout);
        commentsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        recyclerView_shots_comment.setRefreshListener(this);
        onRefresh();
    }

    private void getCommentData(String id,int page,int pre_page,String access_token){
        CommentRetrofit.getRetrofit()
                .create(CommentService.class)
                .getComments(id, page, pre_page, access_token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Comments>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerView_shots_comment,"没网",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Comments> commentses) {
                        commentsList = commentses;
                        for (Comments comments : commentsList){
                            //String.valueOf(comments).replaceAll("\\<p>|</p>","");
                            //commentsAdapter.add(PatternUtil.nohtml(comments));
                            commentsAdapter.addAll(commentsList);//只能String，对象做不了，貌似无解

                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                commentsAdapter.clear();
                getCommentData(id,1,25,"035eddf79698cc57ebe0fc511f484739ec3b50a1a2d75fd92bf6bc9b21d10a78");
                page = 2;
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCommentData(id,page,25,"035eddf79698cc57ebe0fc511f484739ec3b50a1a2d75fd92bf6bc9b21d10a78");
                page++;
            }
        }, 1000);
    }
}
