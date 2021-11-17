package com.seekho.live.Activities;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.Adapters.ResultAdapter;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Quiz.Results.SubmitFinalQuizTestModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class ResultsActivity extends AppBaseActivity {

    RecyclerView recycler_view;
    ResultAdapter resultAdapter;
    SwipeRefreshLayout swipe_refresh_layout;
    ProgressBar progress_bar;

    String test_history_id = "";
    String duration = "";
    int quiz_result_type = 0;

    TextView your_total_marks_tv, total_no_of_que_tv, quiz_submited_date_tv, quiz_in_time_tv;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_result;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        recycler_view = findViewById(R.id.recycler_view);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        your_total_marks_tv = findViewById(R.id.your_total_marks_tv);
        total_no_of_que_tv = findViewById(R.id.total_no_of_que_tv);
        quiz_submited_date_tv = findViewById(R.id.quiz_submited_date_tv);
        quiz_in_time_tv = findViewById(R.id.quiz_in_time_tv);
        progress_bar = findViewById(R.id.progress_bar);

        if (getIntent().getExtras() != null) {
            quiz_result_type = getIntent().getExtras().getInt(KEY_OTP_TYPE);
            test_history_id = getIntent().getExtras().getString(KEY_TEST_HISTORY_ID);
            duration = getIntent().getExtras().getString(KEY_DURATION);
        }

        callSubmitFinalResultApi();
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    private void callSubmitFinalResultApi() {
        String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

        JsonObject authParameter = new JsonObject();
        JsonObject subParameter = new JsonObject();
        if (user_id != null && !user_id.equals("") && token != null && !token.equals("")) {
            if (test_history_id != null && !test_history_id.equals("") && duration != null && !duration.equals("")) {
                authParameter.addProperty(KEY_USER_ID, user_id);
                authParameter.addProperty(KEY_TEST_HISTORY_ID, test_history_id);
                authParameter.addProperty(KEY_DURATION, duration);
                subParameter.addProperty(KEY_ID, user_id);
                subParameter.addProperty(KEY_TOKEN, token);
                authParameter.add(KEY_AUTH, subParameter);
                if (authParameter == null) return;
                updateViewVisibility(progress_bar, View.VISIBLE);
                if (quiz_result_type == KEY_COURSE_QUIZ_TYPE) {
                    makeWebRequest(this).getSubmitFinalQuizTest(authParameter, this);
                } else if (quiz_result_type == KEY_MOCK_TEST_QUIZ_TYPE) {
                    makeWebRequest(this).getSubmitFinalMockTestSeries(authParameter, this);
                }
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_SUBMIT_FINAL_QUIZ_TEST_CODE ||
                    webRequest.getWebRequestID() == WEB_SUBMIT_FINAL_MOCK_TEST_SERIES_CODE) {
                handleSubmitFinalResultResponse(response);
            }
    }

    private void handleSubmitFinalResultResponse(Response response) {
        Object object = response.body();
        SubmitFinalQuizTestModel finalResult = (SubmitFinalQuizTestModel) object;
        if (finalResult != null && finalResult.getCode() == 200) {
            updateViewVisibility(progress_bar, View.GONE);
            if (swipe_refresh_layout.isRefreshing()) {
                swipe_refresh_layout.setRefreshing(false);
            }

            your_total_marks_tv.setText("Your Score: " + finalResult.getMarks_obtain());
            total_no_of_que_tv.setText("Total Questions: " + finalResult.getTotalquizquestions());
            if (finalResult.getTest_date() != null && !finalResult.getTest_date().equals("")) {
                quiz_submited_date_tv.setText("Submitted On: " + finalResult.getTest_date());
            } else {
                quiz_submited_date_tv.setText("Submitted On: ");
            }

            if (finalResult.getTest_duration() != null && !finalResult.getTest_duration().equals("")) {
                quiz_in_time_tv.setText("In Time: " + finalResult.getTest_duration());
            } else {
                quiz_in_time_tv.setText("In Time: ");
            }

            List<SubmitFinalQuizTestModel.Message> messageList = finalResult.getMessage();
            if (messageList != null) {
                setSubmitFinalResultRV(messageList);
            }
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        showSimpleToast(throwable.getMessage());
        updateViewVisibility(progress_bar, View.GONE);
        Log.d(getClass().getSimpleName(), throwable.getCause().toString());
    }

    @Override
    public void onRefresh() {
        callSubmitFinalResultApi();
    }

    private void setSubmitFinalResultRV(List<SubmitFinalQuizTestModel.Message> messageList) {
        if (messageList != null && messageList.size() > 0) {
            ((SimpleItemAnimator) recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
            recycler_view.setLayoutManager(new LinearLayoutManager(this));
            resultAdapter = new ResultAdapter(this, messageList, this);
            recycler_view.setAdapter(resultAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        goToDashboardActivity(null);
        //super.onBackPressed();
    }
}
