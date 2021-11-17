package com.seekho.live.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.Courses.SubscribedCourses.SubscribedCoursesModel;
import com.seekho.live.Models.UserProfile.UserInfoDataModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class ProfileFragment extends AppBaseFragment {

    RecyclerView courses_rv;

    TextView edit_btn_tv, change_pass_btn_tv;
    TextView username_tv, user_email_tv;
    CircleImageView profile_iv;
    ProgressBar progress_bar;

    UserInfoDataModel userData;

    SwipeRefreshLayout swipe_refresh_layout;

    @Override
    public int layoutResourceID() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;
        courses_rv = getView().findViewById(R.id.courses_rv);
        edit_btn_tv = getView().findViewById(R.id.update_profile_btn_tv);
        change_pass_btn_tv = getView().findViewById(R.id.change_pass_btn_tv);
        username_tv = getView().findViewById(R.id.username_tv);
        user_email_tv = getView().findViewById(R.id.user_email_tv);
        profile_iv = getView().findViewById(R.id.profile_iv);
        swipe_refresh_layout = getView().findViewById(R.id.swipe_refresh_layout);
        progress_bar = getView().findViewById(R.id.progress_bar);

        //setUserData();
        callSubscribedCoursesListApi();

        edit_btn_tv.setOnClickListener(this);
        change_pass_btn_tv.setOnClickListener(this);
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Pref.getUserData(getActivity(),this);
    }

    @Override
    public void onUserDataChanged(UserInfoDataModel userData) {
        if (userData != null) {
            this.userData = userData;
            if (userData.getUser_lastname() != null && !userData.getUser_lastname().equals("")) {
                username_tv.setText(userData.getUser_firstname() + " " + userData.getUser_lastname());
            } else {
                username_tv.setText(userData.getUser_firstname());
            }
            user_email_tv.setText(userData.getUser_email());

            if (userData.getProfile_image() != null && !userData.getProfile_image().equals("")) {
                Glide.with(getActivity())
                        .load(userData.getProfile_image())
                        .into(profile_iv);
            } else {
                Glide.with(getActivity())
                        .load(R.drawable.bg_no_profile)
                        .into(profile_iv);
            }
        }
    }

    @Override
    public void onRefresh() {
        callSubscribedCoursesListApi();
        Pref.getUserData(getActivity(),this);
    }

    private void callSubscribedCoursesListApi() {
        String user_id = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_TOKEN);

        if (user_id != null && !user_id.isEmpty() && token != null && !token.isEmpty()) {
            JsonObject authParameter = new JsonObject();
            JsonObject subParameter = new JsonObject();
            authParameter.addProperty(KEY_USER_ID, user_id);
            subParameter.addProperty(KEY_ID, user_id);
            subParameter.addProperty(KEY_TOKEN, token);
            authParameter.add(KEY_AUTH, subParameter);
            if (authParameter == null) return;
            //Fun.showLoader(getActivity());
            updateViewVisibility(progress_bar,View.VISIBLE);
            makeWebRequest(getActivity()).getUserSubscribedCourses(authParameter, this);
        }
    }

//    private void setUserData() {
//        if (userData != null) {
//            if (userData.getUser_lastname() != null && !userData.getUser_lastname().equals("")) {
//                username_tv.setText(userData.getUser_firstname() + " " + userData.getUser_lastname());
//            } else {
//                username_tv.setText(userData.getUser_firstname());
//            }
//            user_email_tv.setText(userData.getUser_email());
//
//            if (userData.getProfile_image() != null && !userData.getProfile_image().equals("")) {
//                Glide.with(getActivity())
//                        .load(userData.getProfile_image())
//                        .into(profile_iv);
//            } else {
//                Glide.with(getActivity())
//                        .load(R.drawable.bg_no_profile)
//                        .into(profile_iv);
//            }
//        }
//    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null || response.code() > 200 || response.code() >= 400) return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_USER_SUBSCRIBED_COURSES_CODE) {
                //Fun.finishLoader(getActivity());
                handleSubscribedCoursesData(response);
            }
    }

    private void handleSubscribedCoursesData(Response response) {
        Object object = response.body();
        SubscribedCoursesModel subscribedData = (SubscribedCoursesModel) object;
        if (subscribedData.getCode() == 200) {
            updateViewVisibility(progress_bar,View.GONE);
            if (swipe_refresh_layout.isRefreshing()){
                swipe_refresh_layout.setRefreshing(false);
            }

            List<AllCoursesData> coursesData = subscribedData.getMessage().getSubscribedcourses();
            if (coursesData != null && coursesData.size() > 0) {
                setCoursesRV(getActivity(), coursesData, courses_rv).setType(1);
            }
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        updateViewVisibility(progress_bar,View.GONE);
        swipe_refresh_layout.setRefreshing(false);
        showSimpleToast(throwable.getMessage());
        Log.d(getClass().getSimpleName(),throwable.getCause().toString());
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.update_profile_btn_tv:
                if (userData.getUser_firstname() != null) {
                    bundle.putString(KEY_FIRST_NAME, userData.getUser_firstname());
                    if (userData.getUser_lastname() != null &&
                            !userData.getUser_lastname().equals("")) {
                        bundle.putString(KEY_LAST_NAME, userData.getUser_lastname());
                    }

                    if (userData.getProfile_image() != null &&
                            !userData.getProfile_image().equals("")) {
                        bundle.putString(KEY_IMG, userData.getProfile_image());
                    }

                    goToUpdateActivity(bundle);
                }
                break;
            case R.id.change_pass_btn_tv:
                bundle.putInt(KEY_OTP_TYPE, 0);
                goToChangePassActivity(bundle);
                break;

        }
    }
}
