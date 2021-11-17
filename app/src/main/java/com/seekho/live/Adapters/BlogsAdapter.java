package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;

public class BlogsAdapter extends AppBaseAdapter {

    Context context;
    OnRecyclerListener recyclerListener;

    public BlogsAdapter(Context context, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.recyclerListener = recyclerListener;
    }

    public BlogsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_blogs_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null)return;
        if (recyclerListener == null)return;
        recyclerListener.onItemClick(view,position);
    }

    @Override
    public int getDataCount() {
        return 5;
    }
}
