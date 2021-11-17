package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.MockTests.MockQuiz.MockQuizListDataModel;
import com.seekho.live.R;

import java.util.List;

public class MockTestsAdapter extends AppBaseAdapter {

    Context context;
    List<MockQuizListDataModel> quizesList;
    OnRecyclerListener recyclerListener;

    public MockTestsAdapter(Context context, List<MockQuizListDataModel> quizesList, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.quizesList = quizesList;
        this.recyclerListener = recyclerListener;
    }

    public MockTestsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_mock_test_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null)return;
        if (recyclerListener == null)return;
        recyclerListener.onItemClick(view,position);
    }

    @Override
    public int getDataCount() {
        return quizesList.size();
    }
}
