package com.app.gaolonglong.gankgirl.adapter.gank;

import android.content.Context;
import android.view.ViewGroup;

import com.app.gaolonglong.gankgirl.model.gank.GanHuo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

public class MeiZhiAdapter extends RecyclerArrayAdapter<GanHuo.Result> {

    public MeiZhiAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeiZhiViewHolder(parent);
    }
}
