package com.app.gaolonglong.gankgirl.ui.activity.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.adapter.home.ShotsAdapter;
import com.app.gaolonglong.gankgirl.model.github.Following;
import com.app.gaolonglong.gankgirl.model.home.Shots;
import com.app.gaolonglong.gankgirl.retrofit.gank.GankRetrofit;
import com.app.gaolonglong.gankgirl.retrofit.gank.GankService;
import com.app.gaolonglong.gankgirl.retrofit.home.ShotsRetrofit;
import com.app.gaolonglong.gankgirl.retrofit.home.ShotsService;
import com.app.gaolonglong.gankgirl.ui.activity.AboutMe;
import com.app.gaolonglong.gankgirl.ui.activity.LoginActivity;
import com.app.gaolonglong.gankgirl.ui.activity.androidbase.AndroidbaseActivity;
import com.app.gaolonglong.gankgirl.ui.activity.github.FolllowingsActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {

    private EasyRecyclerView recyclerView_Shots;
    private ShotsAdapter shotsAdapter;
    private List<Shots> shotsList;
    private Handler handler = new Handler();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);//需要转化成一个view
        TextView username = (TextView)headerview.findViewById(R.id.textView_username);
        TextView email = (TextView)headerview.findViewById(R.id.textView_email);
        CircleImageView headerimg = (CircleImageView) headerview.findViewById(R.id.navigation_headerimg);
        headerimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AboutMe.class));
            }
        });
    }


    private void initView(){
        shotsList = new ArrayList<>();
        recyclerView_Shots = (EasyRecyclerView)findViewById(R.id.recyclerview_Shots);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView_Shots.setLayoutManager(staggeredGridLayoutManager);
        shotsAdapter = new ShotsAdapter(this);
        recyclerView_Shots.setAdapterWithProgress(shotsAdapter);
        shotsAdapter.setMore(R.layout.load_more_layout,this);
        shotsAdapter.setNoMore(R.layout.no_more_layout);
        shotsAdapter.setError(R.layout.error_layout);
        shotsAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(HomeActivity.this,Shots_detailsActivity.class);
                intent.putExtra("title",shotsList.get(position).getTitle());
                intent.putExtra("id",String.valueOf(shotsList.get(position).getId()));
                intent.putExtra("description",shotsList.get(position).getDescription());
                intent.putExtra("view_account",String.valueOf(shotsList.get(position).getViews_count()));
                intent.putExtra("like_account",String.valueOf(shotsList.get(position).getLikes_count()));
                intent.putExtra("updated_at",shotsList.get(position).getUpdated_at());
                intent.putExtra("image",shotsList.get(position).getImages().getHidpi());
                intent.putExtra("author",shotsList.get(position).getUser().getUsername());
                intent.putExtra("avatar",shotsList.get(position).getUser().getAvatar_url());
                intent.putExtra("comments_url",shotsList.get(position).getComments_url());
                intent.putExtra("author_url",shotsList.get(position).getUser().getHtml_url());
                startActivity(intent);
            }
        });

        recyclerView_Shots.setRefreshListener(this);
        //recyclerView_Shots.setRefreshingColor(R.color.colorPrimary,R.color.colorAccent,R.color.colorPrimaryDark,R.color.colorAccent);
        onRefresh();
    }

    private void getData(int page,int pre_page,String access_token){
        ShotsRetrofit.getRetrofit()
                .create(ShotsService.class)
                .getShots(page,pre_page,access_token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Shots>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(recyclerView_Shots,"没网",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Shots> shotses) {
                        shotsList = shotses;
                        shotsAdapter.addAll(shotsList);
                    }
                });

    }


    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shotsAdapter.clear();
                getData(1, 20, "035eddf79698cc57ebe0fc511f484739ec3b50a1a2d75fd92bf6bc9b21d10a78");
                page = 2;
            }
        },1000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //shotsAdapter.clear();
                getData(page,20,"035eddf79698cc57ebe0fc511f484739ec3b50a1a2d75fd92bf6bc9b21d10a78");
                page++;
            }
        },1000);
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            startActivity(new Intent(HomeActivity.this,AndroidbaseActivity.class));

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(HomeActivity.this,FolllowingsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
