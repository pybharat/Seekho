package com.seekho.live.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.Adapters.QuizAdapter;
import com.seekho.live.Adapters.QuizSummaryAdapter;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Quiz.QuizModel;
import com.seekho.live.Models.Quiz.QuizSummary;
import com.seekho.live.Models.Quiz.QuizSummaryModel;
import com.seekho.live.Models.Quiz.SaveAndNextQuizResultModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebRequest;

import java.util.ArrayList;
import java.util.List;

import io.github.kexanie.library.MathView;
import retrofit2.Response;

public class QuizActivity extends AppBaseActivity {

    TextView finish_test_tv, clear_res_tv, save_and_next_btn_tv, mark_as_review_btn_tv;
    TextView remain_time_tv;
    SwipeRefreshLayout swipe_refresh_layout;
    TextView question_no_tv;
    MathView question_tv;
    CardView quiz_cv;
    TextView answered_tv, not_answered_tv, mark_as_tv;
    ProgressBar progress_bar;

    RecyclerView cb_recycler_view;
    QuizAdapter quizAdapter;
    public List<QuizModel.Message> message_list = new ArrayList<>();
    QuizModel quizDataModel;

    String topic_id = "";
    String title = "";

    QuizSummaryModel quizSummaryModel;
    List<QuizSummary> quizSummaryList = new ArrayList<>();
    long duration = 0;

    CountDownTimer countDownTimer;

    int quiz_type = 0;
    int summary_question_type = 0;
    String test_series_id = "";

    QuizSummaryAdapter quizSummaryAdapter;
    LinearLayout open_summary_ll, quiz_summary_ll;
    TextView open_and_close_summary_tv, quiz_summary_title_tv;
    RecyclerView summary_rv;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_quiz_layout;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        finish_test_tv = findViewById(R.id.finish_test_tv);
        clear_res_tv = findViewById(R.id.clear_res_tv);
        remain_time_tv = findViewById(R.id.remain_time_tv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        cb_recycler_view = findViewById(R.id.cb_recycler_view);
        question_tv = findViewById(R.id.question_tv);
        quiz_cv = findViewById(R.id.quiz_cv);
        question_no_tv = findViewById(R.id.question_no_tv);
        save_and_next_btn_tv = findViewById(R.id.save_and_next_btn_tv);
        mark_as_review_btn_tv = findViewById(R.id.mark_as_review_btn_tv);
        answered_tv = findViewById(R.id.answered_tv);
        not_answered_tv = findViewById(R.id.not_answered_tv);
        mark_as_tv = findViewById(R.id.mark_as_tv);
        progress_bar = findViewById(R.id.progress_bar);
        open_summary_ll = findViewById(R.id.open_summary_ll);
        open_and_close_summary_tv = findViewById(R.id.open_and_close_summary_tv);
        quiz_summary_ll = findViewById(R.id.quiz_summary_ll);
        summary_rv = findViewById(R.id.summary_rv);
        quiz_summary_title_tv = findViewById(R.id.quiz_summary_title_tv);

        quizSummaryModel = new QuizSummaryModel();
        if (getIntent().getExtras() != null) {
            quiz_type = getIntent().getExtras().getInt(KEY_OTP_TYPE);
            title = getIntent().getExtras().getString(KEY_TITLE);
            if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                topic_id = getIntent().getExtras().getString(KEY_TOPIC_ID);
            } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                test_series_id = getIntent().getExtras().getString(KEY_TEST_SERIES_ID);
            }

            getToolbarFragment().setTitle(title);
            quiz_summary_title_tv.setText(title);
        }

        callQuizApi(quiz_type);
        swipe_refresh_layout.setOnRefreshListener(this);
        open_summary_ll.setOnClickListener(this);
    }

    int open_summary_tab_position = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_summary_ll:
                if (open_summary_tab_position == 0) {
                    open_and_close_summary_tv.setText("Close Summary");
                    updateViewVisibility(quiz_summary_ll, View.VISIBLE);
                    open_summary_tab_position = 1;
                } else {
                    open_and_close_summary_tv.setText("Open Summary");
                    updateViewVisibility(quiz_summary_ll, View.GONE);
                    open_summary_tab_position = 0;
                }
                break;
        }
    }

    @Override
    public void onRefresh() {
        swipe_refresh_layout.setRefreshing(false);
    }


    private void setCountDown(long millis) {
        countDownTimer = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                duration = millisUntilFinished;
                getTimeDuration(remain_time_tv, millisUntilFinished, false, "");
            }

            @Override
            public void onFinish() {
                Bundle bundle = new Bundle();
                bundle.putString(KEY_TITLE, title);
                bundle.putString(KEY_TEST_HISTORY_ID, quizDataModel.getTesthistoryid());
                bundle.putLong(KEY_DURATION, duration);
                bundle.putSerializable(KEY_QUIZ_SUMMARY_MODEL, quizSummaryModel);
                getQuizSummaryDialog(QuizActivity.this, bundle);
            }
        };
        countDownTimer.start();
    }

    //------------------------------------- Quiz Setup -------------------------------------------
    public void setQuizRv(List<String> quizList) {
        if (quizList != null && quizList.size() > 0) {
            ((SimpleItemAnimator) cb_recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
            cb_recycler_view.setLayoutManager(new LinearLayoutManager(this));
            quizAdapter = new QuizAdapter(this, quizList, this);
            cb_recycler_view.setAdapter(quizAdapter);
        } else {
            return;
        }
    }

    public void getQuizQuestionFromSummary(int position, QuizSummaryAdapter quizSummaryAdapter) {
        if (message_list != null && message_list.size() > 0) {

            for (int i = 0; i < message_list.size(); i++) {
                List<String> options_list = message_list.get(i).getOptions();
                if (options_list != null && options_list.size() > 0) {
                    this.quizSummaryAdapter = quizSummaryAdapter;
                    summary_question_type = 1;
                    questions_count = position;
                    save_and_next_btn_tv.setEnabled(true);
                    mark_as_review_btn_tv.setEnabled(true);

                    setQuizRv(options_list);
                } else {
                    return;
                }
            }
        }
    }

    public void callQuizApi(int quiz_type) {
        String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

        JsonObject authParameter = new JsonObject();
        JsonObject subParameter = new JsonObject();
        if (user_id != null && user_id.length() > 0 && token != null && token.length() > 0) {
            authParameter.addProperty(KEY_USER_ID, user_id);
            if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                if (topic_id != null && !topic_id.equals("")) {
                    authParameter.addProperty(KEY_TOPIC_ID, topic_id);
                    subParameter.addProperty(KEY_ID, user_id);
                    subParameter.addProperty(KEY_TOKEN, token);
                    authParameter.add(KEY_AUTH, subParameter);
                    if (authParameter == null) return;
                    Fun.showLoader(this);
                    makeWebRequest(this).getTopicWiseQuiz(authParameter, this);
                }
            } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                if (test_series_id != null && !test_series_id.equals("")) {
                    authParameter.addProperty(KEY_TEST_SERIES_ID, test_series_id);
                    subParameter.addProperty(KEY_ID, user_id);
                    subParameter.addProperty(KEY_TOKEN, token);
                    authParameter.add(KEY_AUTH, subParameter);
                    if (authParameter == null) return;
                    Fun.showLoader(this);
                    makeWebRequest(this).getMockTestSeriesQuestions(authParameter, this);
                }
            }
        }
    }

    private void callSaveAndNextQuizResultApi(String test_history_id, String cq_id, int show_status, String answer) {
        String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

        JsonObject authParameter = new JsonObject();
        JsonObject subParameter = new JsonObject();
        if (user_id != null && user_id.length() > 0 && token != null && token.length() > 0) {
            if (test_history_id != null && !test_history_id.equals("") && cq_id != null && !cq_id.equals("")) {
                authParameter.addProperty(KEY_USER_ID, user_id);
                authParameter.addProperty(KEY_TEST_HISTORY_ID, test_history_id);
                authParameter.addProperty(KEY_QUIZ_ID, cq_id);
                authParameter.addProperty(KEY_SHOW_STATUS, show_status);
                authParameter.addProperty(KEY_NOT_ANSWERED_QUESTIONS, not_answered_tv.getText().toString());
                authParameter.addProperty(KEY_ANSWERED_QUESTIONS, answered_tv.getText().toString());
                authParameter.addProperty(KEY_MARKED_AS_REVIEW_QUESTIONS, mark_as_tv.getText().toString());
                authParameter.addProperty(KEY_ANSWER, answer);
                subParameter.addProperty(KEY_ID, user_id);
                subParameter.addProperty(KEY_TOKEN, token);
                authParameter.add(KEY_AUTH, subParameter);
                if (authParameter == null) return;
                updateViewVisibility(progress_bar, View.VISIBLE);
                mark_as_review_btn_tv.setEnabled(false);
                save_and_next_btn_tv.setEnabled(false);
                mark_as_review_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
                save_and_next_btn_tv.setBackgroundResource(R.drawable.bg_mid_grey_radius_8dp);
                if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                    makeWebRequest(this).getSaveAndNextQuizResult(authParameter, this);
                } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                    makeWebRequest(this).getSaveAndNextMockTestResult(authParameter, this);
                }
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200) return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_GET_TOPIC_WISE_QUIZ_CODE ||
                    webRequest.getWebRequestID() == WEB_GET_MOCK_TEST_SERIES_QUESTIONS_CODE) {
                handleQuizOptionsData(response);
            } else if (webRequest.getWebRequestID() == WEB_SAVE_AND_NEXT_QUIZ_RESULT_CODE ||
                    webRequest.getWebRequestID() == WebContants.WEB_SAVE_AND_NEXT_MOCK_TEST_RESULT_CODE) {
                handleSaveAndNextQuizResultResponse(response);
            }
    }

    private void handleSaveAndNextQuizResultResponse(Response response) {
        Object object = response.body();
        SaveAndNextQuizResultModel saveAndNextQuizResult = (SaveAndNextQuizResultModel) object;
        if (saveAndNextQuizResult.getCode() == 200) {
            not_answered_tv.setText(String.valueOf(saveAndNextQuizResult.getNotansweredquestions()));
            answered_tv.setText(String.valueOf(saveAndNextQuizResult.getAnsweredquestions()));
            mark_as_tv.setText(String.valueOf(saveAndNextQuizResult.getMarkedasreviewquestions()));
            updateViewVisibility(progress_bar, View.GONE);
            if (questions_count == message_list.size()) {
                return;
            } else {
                mark_as_review_btn_tv.setEnabled(true);
                save_and_next_btn_tv.setEnabled(true);
                mark_as_review_btn_tv.setTextColor(getResources().getColor(R.color.white));
                save_and_next_btn_tv.setBackgroundResource(R.drawable.custom_navi_blue_radius_eight_btn_bg);
            }

        } else {
            return;
        }
    }

    private void handleQuizOptionsData(Response response) {
        Object object = response.body();
        QuizModel quizModel = (QuizModel) object;
        updateViewVisibility(progress_bar, View.VISIBLE);
        if (quizModel.getCode() == 200) {
            Fun.finishLoader(this);
            quizDataModel = quizModel;
            setQuizResponse(quizDataModel);
            message_list.addAll(quizModel.getMessage());
            if (message_list != null && message_list.size() > 0) {
                updateViewVisibility(quiz_cv, View.VISIBLE);
                for (int i = 0; i < message_list.size(); i++) {
                    List<String> options_list = message_list.get(i).getOptions();
                    if (options_list != null && options_list.size() > 0) {
                        setQuizRv(options_list);
                    } else {
                        return;
                    }
                }

            } else {
                return;
            }
        } else {
            return;
        }
    }

    private void setQuizResponse(QuizModel quizResponse) {
        if (quizResponse != null) {
            answered_tv.setText("" + quizResponse.getAnsweredquestions());
            not_answered_tv.setText("" + quizResponse.getNotansweredquestions());
            mark_as_tv.setText("" + quizResponse.getMarkedasreviewquestions());
            updateViewVisibility(progress_bar, View.GONE);
            if (quizResponse.getQuiz_duration() > 0)
                setCountDown(quizResponse.getQuiz_duration());
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        Log.d(getClass().getSimpleName(), "Code " + throwable.getMessage() + "\n" + throwable.toString());
        showSimpleToast(throwable.getMessage());
    }

    //------------------------------- QuizOptions RecyclerView Setup ------------------------------

    public int questions_count = 0;
    int current_position = -1;
    int show_status = -1;
    String answer = "";

    @Override
    public void onItemClick(View view, int position) {
        if (view == null) return;
        CheckBox check_box = view.findViewById(R.id.check_box);
        MathView option_tv = view.findViewById(R.id.option_tv);

        if (message_list != null) {
            question_no_tv.setText("Q. " + (questions_count + 1));

            String actual_string = message_list.get(questions_count).getQuestion();
            if (actual_string.contains("$")) {
                //String que_replaced_string = actual_string.replaceAll("\\$", "$$");
                String que_final_string = actual_string.replace("$", "$$");
                try {
                    question_tv.setText(que_final_string);
                } catch (StringIndexOutOfBoundsException exception) {
                    Log.d(getClass().getSimpleName(), "Message " + exception.getMessage() + "\n" + "Cause " + exception.getCause());
                }
            } else {
                question_tv.setText(actual_string);
            }

            check_box.setTag(position);
            boolean isSelected = position == current_position;
            check_box.setChecked(isSelected);
            check_box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int previous_position = current_position;
                    int selected_position = (Integer) view.getTag();
                    if (previous_position == selected_position) {
                        current_position = -1;
                        quizAdapter.notifyItemChanged(current_position);
                    } else {
                        current_position = selected_position;
                        if (previous_position != -1) {
                            quizAdapter.notifyItemChanged(previous_position);
                        }
                        quizAdapter.notifyItemChanged(current_position);
                    }
                }
            });

            List<String> options_list = message_list.get(questions_count).getOptions();
            if (options_list != null && options_list.size() > 0) {

                String option_string = options_list.get(position);
                if (option_string.contains("$")) {
                    String option_replaced_string = option_string.replaceAll("\\$", "");
                    String option_final_string = option_replaced_string.replace(option_replaced_string, "\\(" + option_replaced_string + "\\)");
                    option_tv.setText(option_final_string);
                } else {
                    option_tv.setText(option_string);
                }


                mark_as_review_btn_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (check_box.isChecked()) {
                            show_status = 2;
                            answer = options_list.get(position);
                        } else {
                            show_status = 3;
                            answer = "";
                        }

                        if (quizDataModel != null) {
                            if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                                callSaveAndNextQuizResultApi(quizDataModel.getTesthistoryid(), message_list.get(questions_count).getCq_id(),
                                        show_status, answer);
                            } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                                callSaveAndNextQuizResultApi(quizDataModel.getTesthistoryid(), message_list.get(questions_count).getCq_id(),
                                        show_status, answer);
                            }
                        }

                        if (summary_question_type == 0) {
                            quizSummaryList.add(new QuizSummary(show_status, String.valueOf(questions_count + 1)));
                            quizSummaryModel.setQuizSummaries(quizSummaryList);
                        } else if (summary_question_type == 1) {
                            quizSummaryList.get(questions_count).setShow_status(show_status);
                            quizSummaryList.get(questions_count).setQuestion_no(String.valueOf(questions_count + 1));
                            quizSummaryModel.setQuizSummaries(quizSummaryList);
                            quizSummaryAdapter.notifyItemChanged(questions_count);
                            quizSummaryAdapter.notifyDataSetChanged();

                        }

                        current_position = -1;
                        if (questions_count == (message_list.size() - 1)) {
                            questions_count = message_list.size();
                            if (questions_count == message_list.size()) {
                                //getQuizSummaryDialog(QuizActivity.this, null);
                                mark_as_review_btn_tv.setEnabled(false);
                                save_and_next_btn_tv.setEnabled(false);
                                mark_as_review_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
                                save_and_next_btn_tv.setBackgroundResource(R.drawable.bg_mid_grey_radius_8dp);

                                return;
                            }
                        } else {
                            questions_count++;
                        }


                        setQuizRv(message_list.get(questions_count).getOptions());
                        quizAdapter.notifyDataSetChanged();

                    }
                });

                save_and_next_btn_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (check_box.isChecked()) {
                            show_status = 1;
                            answer = options_list.get(position);
                        } else {
                            show_status = 0;
                            answer = "";
                        }

                        if (quizDataModel != null) {
                            if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                                callSaveAndNextQuizResultApi(quizDataModel.getTesthistoryid(), message_list.get(questions_count).getCq_id(),
                                        show_status, answer);
                            } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                                callSaveAndNextQuizResultApi(quizDataModel.getTesthistoryid(), message_list.get(questions_count).getCq_id(),
                                        show_status, answer);
                            }
                        }

                        if (summary_question_type == 0) {
                            quizSummaryList.add(new QuizSummary(show_status, String.valueOf(questions_count + 1)));
                            quizSummaryModel.setQuizSummaries(quizSummaryList);
                        } else if (summary_question_type == 1) {
                            quizSummaryList.get(questions_count).setShow_status(show_status);
                            quizSummaryList.get(questions_count).setQuestion_no(String.valueOf(questions_count + 1));
                            quizSummaryModel.setQuizSummaries(quizSummaryList);
                            quizSummaryAdapter.notifyItemChanged(questions_count);
                            quizSummaryAdapter.notifyDataSetChanged();

                        }


                        current_position = -1;
                        if (questions_count == (message_list.size() - 1)) {
                            questions_count = message_list.size();
                            if (questions_count == message_list.size()) {
                                //getQuizSummaryDialog(QuizActivity.this, null);
                                mark_as_review_btn_tv.setEnabled(false);
                                save_and_next_btn_tv.setEnabled(false);
                                mark_as_review_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
                                save_and_next_btn_tv.setBackgroundResource(R.drawable.bg_mid_grey_radius_8dp);
                                return;
                            }
                        } else {
                            questions_count++;
                        }

                        setQuizRv(message_list.get(questions_count).getOptions());
                        quizAdapter.notifyDataSetChanged();
                    }
                });
            }

//            if (quizSummaryList.size() > 0){
//                updateViewVisibility(open_summary_ll,View.VISIBLE);
//                setQuizSummaryRV(this,summary_rv,quizSummaryModel);
//            }

            clear_res_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (check_box.isChecked()) {
                        check_box.setChecked(false);
                        current_position = -1;
                    }
                }
            });

            finish_test_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!mark_as_review_btn_tv.isEnabled() && !save_and_next_btn_tv.isEnabled()) {
                        Bundle bundle = new Bundle();
                        if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                            bundle.putInt(KEY_OTP_TYPE, KEY_COURSE_QUIZ_TYPE);
                        } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                            bundle.putInt(KEY_OTP_TYPE, KEY_MOCK_TEST_QUIZ_TYPE);
                        }
                        bundle.putString(KEY_TITLE, title);
                        bundle.putString(KEY_TEST_HISTORY_ID, quizDataModel.getTesthistoryid());
                        bundle.putLong(KEY_DURATION, duration);
                        bundle.putSerializable(KEY_QUIZ_SUMMARY_MODEL, quizSummaryModel);
                        getQuizSummaryDialog(QuizActivity.this, bundle);
                    } else {
                        Bundle bundle = new Bundle();
                        if (quiz_type == KEY_COURSE_QUIZ_TYPE) {
                            bundle.putInt(KEY_OTP_TYPE, KEY_COURSE_QUIZ_TYPE);
                        } else if (quiz_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                            bundle.putInt(KEY_OTP_TYPE, KEY_MOCK_TEST_QUIZ_TYPE);
                        }
                        bundle.putString(KEY_TITLE, title);
                        bundle.putString(KEY_TEST_HISTORY_ID, quizDataModel.getTesthistoryid());
                        bundle.putLong(KEY_DURATION, duration);
                        bundle.putSerializable(KEY_QUIZ_SUMMARY_MODEL, quizSummaryModel);
                        getConfirmationDialog(QuizActivity.this, bundle);
                    }
                }
            });
        } else {
            return;
        }
    }
}
