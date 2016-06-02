package com.app.gaolonglong.gankgirl.adapter.home.comments;

import android.content.Context;
import android.view.ViewGroup;

import com.app.gaolonglong.gankgirl.model.home.comment.Comments;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class CommentsAdapter extends RecyclerArrayAdapter<Comments> {

    public CommentsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsViewHolder(parent);
    }
}
