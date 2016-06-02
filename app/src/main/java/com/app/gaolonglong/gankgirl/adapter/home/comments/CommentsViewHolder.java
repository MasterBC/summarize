package com.app.gaolonglong.gankgirl.adapter.home.comments;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.model.home.comment.Comments;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class CommentsViewHolder extends BaseViewHolder<Comments> {

    private ImageView imgView_shots_comment_avatar;
    private TextView textView_shots_comment_contain;
    private TextView textView_shots_comment_author;

    public CommentsViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.shots_comment_item);
        imgView_shots_comment_avatar = $(R.id.imgView_shots_comment_avatar);
        textView_shots_comment_contain = $(R.id.textView_shots_comment_contain);
        textView_shots_comment_author = $(R.id.textView_shots_comment_author);
    }

    @Override
    public void setData(Comments data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUser().getAvatar_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView_shots_comment_avatar);

        textView_shots_comment_author.setText(data.getUser().getUsername());
        textView_shots_comment_contain.setText(data.getBody());
    }
}
