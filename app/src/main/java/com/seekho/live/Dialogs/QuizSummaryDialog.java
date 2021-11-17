package com.seekho.live.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Activities.QuizActivity;
import com.seekho.live.Adapters.QuizSummaryAdapter;
import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.Models.Quiz.QuizSummary;
import com.seekho.live.Models.Quiz.QuizSummaryModel;
import com.seekho.live.R;

import java.util.List;

public class QuizSummaryDialog extends AppBaseDialogFragment {

    Context context;
    RecyclerView recycler_view;
    QuizSummaryAdapter adapter;

    TextView done_btn_tv;
    TextView quiz_title_tv;
    TextView restart_btn_tv, exit_anyway_btn_tv;
    LinearLayout summary_rv_ll, null_ll;
    NestedScrollView nested_scroll_view;

    String title = "";
    String test_history_id = "";
    long duration = 0;
    int quiz_type = 0;
    QuizSummaryModel quizSummaryModel;

    public static QuizSummaryDialog getInstance(Context context) {
        QuizSummaryDialog dialog = new QuizSummaryDialog(context);
        return dialog;
    }

    public QuizSummaryDialog(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_quiz_summary;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;
        recycler_view = getView().findViewById(R.id.recycler_view);
        done_btn_tv = getView().findViewById(R.id.done_btn_tv);
        quiz_title_tv = getView().findViewById(R.id.quiz_title_tv);
        summary_rv_ll = getView().findViewById(R.id.summary_rv_ll);
        null_ll = getView().findViewById(R.id.null_ll);
        restart_btn_tv = getView().findViewById(R.id.restart_btn_tv);
        exit_anyway_btn_tv = getView().findViewById(R.id.exit_anyway_btn_tv);
        nested_scroll_view = getView().findViewById(R.id.nested_scroll_view);

        if (getArguments() != null) {
            quiz_type = getArguments().getInt(KEY_OTP_TYPE);
            title = getArguments().getString(KEY_TITLE);
            test_history_id = getArguments().getString(KEY_TEST_HISTORY_ID);
            duration = getArguments().getLong(KEY_DURATION);
            quizSummaryModel = (QuizSummaryModel) getArguments().getSerializable(KEY_QUIZ_SUMMARY_MODEL);

            quiz_title_tv.setText(title + " Summary");
        }

        restart_btn_tv.setOnClickListener(this);
        exit_anyway_btn_tv.setOnClickListener(this);
        done_btn_tv.setOnClickListener(this);
        setQuestionsRV();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.done_btn_tv:
                Bundle bundle = new Bundle();
                if (quiz_type == KEY_COURSE_QUIZ_TYPE){
                    bundle.putInt(KEY_OTP_TYPE,KEY_COURSE_QUIZ_TYPE);
                } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE){
                    bundle.putInt(KEY_OTP_TYPE,KEY_MOCK_TEST_QUIZ_TYPE);
                }
                bundle.putString(KEY_TEST_HISTORY_ID,test_history_id);
                bundle.putString(KEY_DURATION,String.valueOf(duration));
                goToResultsActivity(bundle);
                getDialog().dismiss();
                getActivity().finish();
                break;
            case R.id.restart_btn_tv:
                //((QuizActivity)getActivity()).callQuizApi();
                getDialog().dismiss();
                break;
            case R.id.exit_anyway_btn_tv:
                goToDashboardActivity(null);
                break;
        }
    }

    private void setQuestionsRV() {
        if (quizSummaryModel != null) {
            List<QuizSummary> quizSummaryList = quizSummaryModel.getQuizSummaries();
            if (quizSummaryList != null && quizSummaryList.size() > 0) {
                updateViewVisibility(nested_scroll_view,View.VISIBLE);
                updateViewVisibility(null_ll,View.GONE);

                recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                adapter = new QuizSummaryAdapter(getActivity(), quizSummaryList, this);
                recycler_view.setAdapter(adapter);
            } else {
                updateViewVisibility(nested_scroll_view,View.GONE);
                updateViewVisibility(null_ll,View.VISIBLE);
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view == null) return;
        TextView quiz_no_tv = view.findViewById(R.id.quiz_no_tv);

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
                            ((QuizActivity)getActivity()).getQuizQuestionFromSummary(position,adapter);
                            getDialog().dismiss();
                        }
                    });
                }
            } else {
                return;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(android.content.DialogInterface dialog, int keyCode,
                                 android.view.KeyEvent event) {

                if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                    if (event.getAction() != KeyEvent.ACTION_DOWN)
                        return true;
                    else {
                        return true;
                    }
                } else
                    return false;
            }
        });
    }
}
