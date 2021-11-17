package com.seekho.live.Fragments;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.Adapters.MockCoursesAdapter;
import com.seekho.live.Adapters.PromotionsAdapter;
import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.Models.MockTests.MockTestCategoriesModel;
import com.seekho.live.Models.MockTests.MockTestCategoryDataModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class MockTestsFragment extends AppBaseFragment {

    RecyclerView promotion_rv,mock_courses_rv;
    PromotionsAdapter promotionsAdapter;
    MockCoursesAdapter mockCoursesAdapter;
    ProgressBar progress_bar;

    SwipeRefreshLayout swipe_refresh_layout;

    @Override
    public int layoutResourceID() {
        return R.layout.fragment_mock_tests;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null)return;
        promotion_rv = getView().findViewById(R.id.promotion_rv);
        mock_courses_rv = getView().findViewById(R.id.mock_courses_rv);
        swipe_refresh_layout = getView().findViewById(R.id.swipe_refresh_layout);
        progress_bar = getView().findViewById(R.id.progress_bar);

        swipe_refresh_layout.setOnRefreshListener(this);
        setPromotionalBannerRV(getActivity(),promotion_rv);
        callMockTestCategoriesApi();
    }

    private void callMockTestCategoriesApi() {
        String user_id = Pref.getValueFromPref(getActivity(),USER_INFO_PREF,KEY_USER_ID);
        String token = Pref.getValueFromPref(getActivity(),USER_INFO_PREF,KEY_TOKEN);

        JsonObject authParameters = new JsonObject();
        JsonObject subParameters = new JsonObject();
        if (user_id != null && !user_id.equals("") && token != null && !token.equals("")){
            authParameters.addProperty(KEY_USER_ID,user_id);
            subParameters.addProperty(KEY_ID,user_id);
            subParameters.addProperty(KEY_TOKEN,token);
            authParameters.add(KEY_AUTH,subParameters);
            if (authParameters == null)return;
            updateViewVisibility(progress_bar,View.VISIBLE);
            makeWebRequest(getActivity()).getMockTestCategories(authParameters,this);
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() == 400)return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_GET_MOCK_TEST_CATEGORIES_CODE){
                handleMockTestCategoriesResponse(response);
            }
    }

    private void handleMockTestCategoriesResponse(Response response) {
        Object object = response.body();
        MockTestCategoriesModel mockTestCategoriesModel = (MockTestCategoriesModel)object;
        if (mockTestCategoriesModel.getCode() == 200){
            updateViewVisibility(progress_bar,View.GONE);
            if (swipe_refresh_layout.isRefreshing()){
                swipe_refresh_layout.setRefreshing(false);
            }

            MockTestCategoriesModel.Message messageList = mockTestCategoriesModel.getMessage();
            if (messageList != null){
                List<MockTestCategoryDataModel> categoryList = messageList.getCategory();
                if (categoryList != null){
                    setMockCoursesRV(categoryList);
                }
            }
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null)return;
        updateViewVisibility(progress_bar,View.GONE);
        showSimpleToast(throwable.getMessage());
        Log.d(getClass().getSimpleName(),throwable.getCause().toString());
    }

    @Override
    public void onRefresh() {
        callMockTestCategoriesApi();
    }

    private void setMockCoursesRV(List<MockTestCategoryDataModel> categoryDataList) {
        if (categoryDataList != null && categoryDataList.size() > 0){
            ((SimpleItemAnimator)mock_courses_rv.getItemAnimator()).setSupportsChangeAnimations(false);
            mock_courses_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mockCoursesAdapter = new MockCoursesAdapter(getActivity(),categoryDataList,this);
            mock_courses_rv.setAdapter(mockCoursesAdapter);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        if (view == null)return;

    }
}
