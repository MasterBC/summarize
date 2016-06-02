package com.app.gaolonglong.gankgirl.adapter.home;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.model.home.Shots;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/5/31 0031.
 */
public class ShotsViewHolder extends BaseViewHolder<Shots> {

    private ImageView imageView_Shots_item;

    public ShotsViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.shots_item);
        imageView_Shots_item = $(R.id.shots_item_image);//必须用$，否则view为空
    }

    @Override
    public void setData(Shots data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getImages().getNormal())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView_Shots_item);
    }
}
