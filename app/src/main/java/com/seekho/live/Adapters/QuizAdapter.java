package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;

import java.util.List;

public class QuizAdapter extends AppBaseAdapter {

    Context context;
    public List<String> options_list;
    OnRecyclerListener listener;

    public QuizAdapter(Context context, List<String> options_list, OnRecyclerListener listener) {
        this.context = context;
        this.options_list = options_list;
        this.listener = listener;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_quiz_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (view == null) return;
        if (listener == null) return;
        listener.onItemClick(view, position);
    }

    @Override
    public int getDataCount() {
        return options_list.size();
    }
}
