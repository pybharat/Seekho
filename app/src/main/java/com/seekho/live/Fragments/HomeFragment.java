package com.seekho.live.Fragments;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.Courses.CoursesModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class HomeFragment extends AppBaseFragment {

    RecyclerView promotion_rv, courses_rv, blogs_rv, community_rv;
    TextView view_all_tv;
    SwipeRefreshLayout swipe_refresh_layout;
    ProgressBar progress_bar;

    LinearLayout courses_ll,community_ll;

    @Override
    public int layoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;
        promotion_rv = getView().findViewById(R.id.promotion_rv);
        courses_rv = getView().findViewById(R.id.courses_rv);
        blogs_rv = getView().findViewById(R.id.blogs_rv);
        community_rv = getView().findViewById(R.id.community_rv);
        view_all_tv = getView().findViewById(R.id.view_all_tv);
        swipe_refresh_layout = getView().findViewById(R.id.swipe_refresh_layout);
        community_ll = getView().findViewById(R.id.community_ll);
        courses_ll = getView().findViewById(R.id.courses_ll);
        progress_bar = getView().findViewById(R.id.progress_bar);

        setPromotionalBannerRV(getActivity(), promotion_rv);
        setBlogsRV(getActivity(), blogs_rv);
        setCommunityRV(getActivity(), community_rv);

        swipe_refresh_layout.setOnRefreshListener(this);
        view_all_tv.setOnClickListener(this);

        callCoursesApi();
    }

    @Override
    public void onRefresh() {
        callCoursesApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_all_tv:
                goToAllMainCoursesActivity(null);
                break;
        }
    }

    private void callCoursesApi() {
        String userId = Pref.getValueFromPref(getActivity(),USER_INFO_PREF,KEY_USER_ID);
        String token = Pref.getValueFromPref(getActivity(),USER_INFO_PREF,KEY_TOKEN);
        if (userId != null && token != null){
            JsonObject authParameter = new JsonObject();
            JsonObject subParameters = new JsonObject();
            authParameter.addProperty(KEY_USER_ID,userId);
            subParameters.addProperty(KEY_ID,userId);
            subParameters.addProperty(KEY_TOKEN,token);
            authParameter.add(KEY_AUTH,subParameters);
            if (authParameter == null)return;
            //Fun.showLoader(getActivity());
            updateViewVisibility(progress_bar,View.VISIBLE);
            makeWebRequest(getActivity()).callCoursesApi(authParameter,this);
        } else {
            return;
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() == 400) return;
        if (response.code() == 200)
        //Fun.finishLoader(getActivity());
        if (webRequest.getWebRequestID() == WEB_GET_COURSES_CODE) {
                handleGetCoursesResponse(response);
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        swipe_refresh_layout.setRefreshing(false);
        updateViewVisibility(progress_bar,View.GONE);
        Log.d(getClass().getSimpleName(), throwable.getMessage());
    }

    private void handleGetCoursesResponse(Response response) {
        if (response == null) return;
            Object object = response.body();
            CoursesModel coursesList = (CoursesModel) object;
            if (coursesList.getCode() == 200 && coursesList != null) {
                updateViewVisibility(progress_bar,View.GONE);
                updateViewVisibility(courses_ll,View.VISIBLE);
                updateViewVisibility(community_ll,View.VISIBLE);
                swipe_refresh_layout.setRefreshing(false);
                List<AllCoursesData> dataList = coursesList.getMessage().getAllcourses();
                if (dataList != null && dataList.size() > 0) {
                    setCoursesRV(getActivity(), dataList, courses_rv);
                }
            } else if (coursesList.getCode() == 400 || coursesList.getCode() > 201){
                showSimpleToast(coursesList.getError());
            }
    }
}
