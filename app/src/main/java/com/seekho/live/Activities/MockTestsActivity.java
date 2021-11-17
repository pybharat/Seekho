package com.seekho.live.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.MockTests.MockTestCategoryDataModel;
import com.seekho.live.Models.MockTests.MockTestCategoryDetailModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import io.github.kexanie.library.MathView;
import retrofit2.Response;

public class MockTestsActivity extends AppBaseActivity {

    SwipeRefreshLayout swipe_refresh_layout;
    MathView desc_wv;
    String title = "";
    String mock_test_category_id = "";

    @Override
    public int layoutResourceID() {
        return R.layout.activity_mock_tests;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        desc_wv = findViewById(R.id.desc_wv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);

        if (getIntent().getExtras() != null) {
            title = getIntent().getExtras().getString(KEY_TITLE);
            mock_test_category_id = getIntent().getExtras().getString(KEY_MOCK_CATEGORY);

            getToolbarFragment().setTitle(title);
        }


        swipe_refresh_layout.setOnRefreshListener(this);
        callMockTestCategoryDetailApi();
    }

    @Override
    public void onRefresh() {
        callMockTestCategoryDetailApi();
    }

    private void callMockTestCategoryDetailApi() {
        String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

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
                Fun.showLoader(this);
                makeWebRequest(this).getMockTestCategoryDetail(authParameter, this);
            }
        }
    }


    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
            Fun.finishLoader(this);
        if (webRequest.getWebRequestID() == WEB_GET_MOCK_TEST_CATEGORY_DETAIL_CODE) {
            handleMockTestCategoryDetailResponse(response);
        }
    }

    private void handleMockTestCategoryDetailResponse(Response response) {
        Object object = response.body();
        MockTestCategoryDetailModel mockTestCategoryDetailModel = (MockTestCategoryDetailModel) object;
        if (mockTestCategoryDetailModel.getCode() == 200) {
            MockTestCategoryDataModel categoryDataModel = mockTestCategoryDetailModel.getMessage().getCategorydet();
            if (categoryDataModel != null) {
                String actual_desc_string = categoryDataModel.getMc_description();
                if (actual_desc_string.contains("$")) {
                    String final_desc_string = actual_desc_string.replace("$", "$$");
                    setDescription(final_desc_string);
                } else {
                    setDescription(actual_desc_string);
                }
            }
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        //swipe_refresh_layout.setRefreshing(false);
        Fun.finishLoader(this);
        showSimpleToast(throwable.getMessage());
        Log.d(getClass().getSimpleName(), throwable.getCause().toString());
    }

    private void setDescription(String html_string) {
        if (html_string != null || html_string.length() > 0) {
            //desc_wv.loadData(html_string, "text/html", "utf-8");
            desc_wv.setText(html_string);
//            desc_wv.getSettings().setJavaScriptEnabled(true);
//            desc_wv.getSettings().setDomStorageEnabled(true);
//            desc_wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//            desc_wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
    }

    @Override
    public void onToolbarClick(View view) {
        super.onToolbarClick(view);
        switch (view.getId()) {
            case R.id.open_test_btn_tv:
                Bundle bundle = new Bundle();
                if (mock_test_category_id != null && !mock_test_category_id.equals("")) {
                    bundle.putString(KEY_MOCK_CATEGORY, mock_test_category_id);
                    getMockTestsDialog(MockTestsActivity.this, bundle);
                }

                break;
        }
    }
}
