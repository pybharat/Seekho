package com.seekho.live.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.google.gson.JsonObject;
import com.seekho.live.Adapters.MockTestsAdapter;
import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.Models.MockTests.MockQuiz.MockQuizListDataModel;
import com.seekho.live.Models.MockTests.MockQuiz.MockQuizesListModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class MockTestsDialog extends AppBaseDialogFragment {

    Context context;

    RecyclerView recycler_view;
    MockTestsAdapter mockTestsAdapter;
    ProgressBar progress_bar;

    ImageView dismiss_iv;

    String mock_test_category_id = "";
    public List<MockQuizListDataModel> quizesList;

    public static MockTestsDialog getInstance(Context context) {
        MockTestsDialog mockTestsDialog = new MockTestsDialog(context);
        return mockTestsDialog;
    }

    public MockTestsDialog(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_mock_tests;
    }


    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;
        recycler_view = getView().findViewById(R.id.recycler_view);
        dismiss_iv = getView().findViewById(R.id.dismiss_iv);
        progress_bar = getView().findViewById(R.id.progress_bar);

        if (getArguments() != null) {
            mock_test_category_id = getArguments().getString(KEY_MOCK_CATEGORY);
        }

        dismiss_iv.setOnClickListener(this);
        callMockTestCategoryWiseSeriesApi();
    }

    private void callMockTestCategoryWiseSeriesApi() {
        String user_id = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_TOKEN);

        JsonObject authParameter = new JsonObject();
        JsonObject subParameter = new JsonObject();
        if (user_id != null && !user_id.equals("") && token != null && !token.equals("")) {
            if (mock_test_category_id != null && !mock_test_category_id.equals("")) {
                authParameter.addProperty(KEY_USER_ID, user_id);
                authParameter.addProperty(KEY_MOCK_CATEGORY, mock_test_category_id);
                subParameter.addProperty(KEY_ID, user_id);
                subParameter.addProperty(KEY_TOKEN, token);
                authParameter.add(KEY_AUTH, subParameter);
                if (authParameter == null) return;
                updateViewVisibility(recycler_view, View.GONE);
                updateViewVisibility(progress_bar, View.VISIBLE);
                makeWebRequest(getActivity()).getMockTestCategoryWiseSeries(authParameter, this);
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_GET_MOCK_TEST_CATEGORY_WISE_SERIES_CODE) {
                handleMockTestCategoryWiseSeriesResponse(response);
            }

    }

    private void handleMockTestCategoryWiseSeriesResponse(Response response) {
        Object object = response.body();
        MockQuizesListModel mockQuizesListModel = (MockQuizesListModel) object;
        if (mockQuizesListModel.getCode() == 200) {
            //getDialog().show();
            updateViewVisibility(recycler_view, View.VISIBLE);
            updateViewVisibility(progress_bar, View.GONE);

            List<MockQuizListDataModel> dataList = mockQuizesListModel.getMessage().getTest_series();
            if (dataList != null && dataList.size() > 0) {
                quizesList = dataList;
                setMockQuizesListRV(dataList);
            }
        }
    }

    private void setMockQuizesListRV(List<MockQuizListDataModel> dataList) {
        if (dataList != null && dataList.size() > 0) {
            ((SimpleItemAnimator) recycler_view.getItemAnimator()).setSupportsChangeAnimations(false);
            recycler_view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            mockTestsAdapter = new MockTestsAdapter(getActivity(), dataList, this);
            recycler_view.setAdapter(mockTestsAdapter);
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        updateViewVisibility(progress_bar, View.GONE);
        Log.d(getClass().getSimpleName(), throwable.getCause().toString());
        showSimpleToast(throwable.getMessage());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dismiss_iv:
                closeDialog();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view == null) return;

        TextView mock_test_total_que_tv, mock_test_duration_tv, mock_test_name_tv;
        TextView start_test_btn_tv;

        mock_test_name_tv = view.findViewById(R.id.mock_test_name_tv);
        mock_test_total_que_tv = view.findViewById(R.id.mock_test_total_que_tv);
        mock_test_duration_tv = view.findViewById(R.id.mock_test_duration_tv);
        start_test_btn_tv = view.findViewById(R.id.start_test_btn_tv);

        if (quizesList != null && quizesList.size() > 0) {
            MockQuizListDataModel dataList = quizesList.get(position);

            if (dataList != null) {

                mock_test_name_tv.setText(dataList.getTs_name());
                mock_test_total_que_tv.setText(dataList.getTotal_questions());
                mock_test_duration_tv.setText(dataList.getTest_duration());

                start_test_btn_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_TITLE, dataList.getTs_name());
                        bundle.putInt(KEY_OTP_TYPE, KEY_MOCK_TEST_QUIZ_TYPE);
                        bundle.putString(KEY_TEST_SERIES_ID, dataList.getTs_id());
                        goToQuizActivity(bundle);
                        getDialog().dismiss();
                    }
                });
            }
        }
    }
}
