package com.app.gaolonglong.gankgirl.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.app.gaolonglong.gankgirl.model.home.Shots;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class ShotsAdapter extends RecyclerArrayAdapter<Shots> {
    public ShotsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShotsViewHolder(parent);
    }
}
