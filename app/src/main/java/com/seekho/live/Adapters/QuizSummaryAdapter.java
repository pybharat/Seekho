package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.Quiz.QuizSummary;
import com.seekho.live.R;

import java.util.List;

public class QuizSummaryAdapter extends AppBaseAdapter {

    Context context;
    List<QuizSummary> quizSummaryList;
    OnRecyclerListener recyclerListener;

    TextView quiz_no_tv;

    int NOT_ANSWERED = 0;
    int ANSWERED = 1;
    int MARK_AS_REVIEW = 2;

    public QuizSummaryAdapter(Context context, List<QuizSummary> quizSummaryList) {
        this.context = context;
        this.quizSummaryList = quizSummaryList;
    }

    public QuizSummaryAdapter(Context context, List<QuizSummary> quizSummaryList, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.quizSummaryList = quizSummaryList;
        this.recyclerListener = recyclerListener;
    }

    public QuizSummaryAdapter(Context context) {
        this.context = context;
    }

    public QuizSummaryAdapter() {
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_quiz_summary_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (view == null) return;
        if (recyclerListener == null) return;
        recyclerListener.onItemClick(view, position);
    }

    @Override
    public int getDataCount() {
        return quizSummaryList.size();
    }
}
