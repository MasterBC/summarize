package com.app.gaolonglong.gankgirl.adapter.github;

import android.content.Context;
import android.view.ViewGroup;

import com.app.gaolonglong.gankgirl.model.github.Following;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/29 0029.
 */
public class FollowingAdapter extends RecyclerArrayAdapter<Following> {
    public FollowingAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new FollowingViewHolder(parent);
    }
}
