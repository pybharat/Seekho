package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Activities.QuizActivity;
import com.seekho.live.Adapters.QuizSummaryAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.Models.Quiz.QuizSummary;
import com.seekho.live.Models.Quiz.QuizSummaryModel;
import com.seekho.live.R;

import java.util.List;

public class QuizSummaryRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;
    QuizSummaryModel quizSummaryModel;
    QuizSummaryAdapter adapter;

    TextView quiz_no_tv;

    public QuizSummaryRV(Context context, RecyclerView recyclerView, QuizSummaryModel quizSummaryModel) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;
        this.quizSummaryModel = quizSummaryModel;

        setQuestionsRV();
    }

    @Override
    public void initializeComponents(View view) {
        if (view == null) return;
        quiz_no_tv = view.findViewById(R.id.quiz_no_tv);
    }

    private void setQuestionsRV() {
        if (quizSummaryModel != null) {
            List<QuizSummary> quizSummaryList = quizSummaryModel.getQuizSummaries();
            if (quizSummaryList != null && quizSummaryList.size() > 0) {
                recyclerView.setLayoutManager(new GridLayoutManager(context, 6));
                adapter = new QuizSummaryAdapter(context, quizSummaryList, this);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void setData(View view, int position) {
        if (quizSummaryModel != null) {
            List<QuizSummary> quizSummaryList = quizSummaryModel.getQuizSummaries();
            if (quizSummaryList != null && quizSummaryList.size() > 0) {

                QuizSummary quizSummary = quizSummaryList.get(position);
                if (quizSummary != null) {
                    quiz_no_tv.setText(quizSummary.getQuestion_no());
                    if (quizSummary.getShow_status() == 0) {
                        quiz_no_tv.setEnabled(true);
                        quiz_no_tv.setBackgroundResource(R.drawable.bg_oval_red_white_border);
                    } else if (quizSummary.getShow_status() == 1) {
                        quiz_no_tv.setEnabled(false);
                        quiz_no_tv.setBackgroundResource(R.drawable.bg_oval_green_white_border);
                    } else if (quizSummary.getShow_status() == 2 || quizSummary.getShow_status() == 3) {
                        quiz_no_tv.setEnabled(true);
                        quiz_no_tv.setBackgroundResource(R.drawable.bg_oval_naviblue_white_border);
                    }

                    quiz_no_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((QuizActivity)context).getQuizQuestionFromSummary(position,adapter);
                        }
                    });
                }
            } else {
                return;
            }
        }
    }
}
