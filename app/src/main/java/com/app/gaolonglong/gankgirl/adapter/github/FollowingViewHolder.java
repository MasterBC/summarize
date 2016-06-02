package com.app.gaolonglong.gankgirl.adapter.github;


import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.model.github.Following;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/5/29 0029.
 */
public class FollowingViewHolder extends BaseViewHolder<Following> {

    private ImageView imageView_following;
    private TextView textView_following;


      public FollowingViewHolder(ViewGroup itemView) {
        super(itemView,R.layout.following_item);
        imageView_following = $(R.id.following_img);
        textView_following = $(R.id.following_name);
    }

    @Override
    public void setData(Following data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView_following);

        textView_following.setText(data.getLogin());
    }
}
