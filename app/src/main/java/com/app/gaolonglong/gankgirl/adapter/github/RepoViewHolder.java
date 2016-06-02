package com.app.gaolonglong.gankgirl.adapter.github;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.model.github.Repo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class RepoViewHolder extends BaseViewHolder<Repo> {

    private TextView textView_repo_name;
    private TextView textView_repo_description;

    public RepoViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.repo_item);
        textView_repo_name = $(R.id.repo_name);
        textView_repo_description = $(R.id.repo_description);
    }

    @Override
    public void setData(Repo data) {
        super.setData(data);
        textView_repo_name.setText(data.getName());
        textView_repo_description.setText(data.getDescription());
    }
}
