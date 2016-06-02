package com.app.gaolonglong.gankgirl.adapter.github;

import android.content.Context;
import android.view.ViewGroup;

import com.app.gaolonglong.gankgirl.model.github.Repo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class RepoAdapter extends RecyclerArrayAdapter<Repo> {
    public RepoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoViewHolder(parent);
    }
}
