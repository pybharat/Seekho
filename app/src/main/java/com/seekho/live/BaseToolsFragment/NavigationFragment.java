package com.seekho.live.BaseToolsFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.seekho.live.Activities.DashboardActivity;
import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.Models.UserProfile.UserInfoDataModel;
import com.seekho.live.Models.UserProfile.UserInfoModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class NavigationFragment extends AppBaseFragment {

    DrawerLayout drawerLayout;
    LinearLayout profile_ll, settings_ll, share_app_ll, contact_us_ll, rate_us_ll, privacy_policy_ll, log_out_ll;
    ImageView settings_iv, share_iv, contact_us_iv, rate_us_iv, privacy_policy_iv, log_out_iv;
    TextView username_tv, user_email_tv, settings_tv, share_this_app_tv, contact_us_tv, rate_us_tv, privacy_policy_tv, log_out_tv;
    CircleImageView profile_iv;

    @Override
    public int layoutResourceID() {
        return R.layout.layout_navigation;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        if (getActivity() == null && getView() == null) return;
        drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        if (drawerLayout == null) return;
        drawerLayout.addDrawerListener(drawerListener);

        profile_ll = getView().findViewById(R.id.profile_ll);
        settings_ll = getView().findViewById(R.id.settings_ll);
        share_app_ll = getView().findViewById(R.id.share_app_ll);
        contact_us_ll = getView().findViewById(R.id.contact_us_ll);
        rate_us_ll = getView().findViewById(R.id.rate_us_ll);
        privacy_policy_ll = getView().findViewById(R.id.privacy_policy_ll);
        log_out_ll = getView().findViewById(R.id.log_out_ll);

        settings_iv = getView().findViewById(R.id.settings_iv);
        share_iv = getView().findViewById(R.id.share_iv);
        contact_us_iv = getView().findViewById(R.id.contact_us_iv);
        rate_us_iv = getView().findViewById(R.id.rate_us_iv);
        privacy_policy_iv = getView().findViewById(R.id.privacy_policy_iv);
        log_out_iv = getView().findViewById(R.id.log_out_iv);
        profile_iv = getView().findViewById(R.id.profile_iv);

        username_tv = getView().findViewById(R.id.username_tv);
        user_email_tv = getView().findViewById(R.id.user_email_tv);
        settings_tv = getView().findViewById(R.id.settings_tv);
        share_this_app_tv = getView().findViewById(R.id.share_this_app_tv);
        contact_us_tv = getView().findViewById(R.id.contact_us_tv);
        rate_us_tv = getView().findViewById(R.id.rate_us_tv);
        privacy_policy_tv = getView().findViewById(R.id.privacy_policy_tv);
        log_out_tv = getView().findViewById(R.id.log_out_tv);

        profile_ll.setOnClickListener(this);
        settings_ll.setOnClickListener(this);
        share_app_ll.setOnClickListener(this);
        contact_us_ll.setOnClickListener(this);
        rate_us_ll.setOnClickListener(this);
        privacy_policy_ll.setOnClickListener(this);
        log_out_ll.setOnClickListener(this);

        callUserLoginInfoApi();
    }

    @Override
    public void onResume() {
        super.onResume();
        Pref.getUserData(getActivity(),this);
    }

    private void setUserData(UserInfoDataModel userData){
        if (userData != null){
            if (userData.getUser_lastname() != null) {
                username_tv.setText(userData.getUser_firstname() + " " + userData.getUser_lastname());
            } else {
                username_tv.setText(userData.getUser_firstname());
            }
            user_email_tv.setText(userData.getUser_email());
            if (userData.getProfile_image() != null &&
                    !userData.getProfile_image().equals("") &&
                    userData.getProfile_image().length() > 0) {
                Glide.with(getActivity())
                        .load(userData.getProfile_image())
                        .into(profile_iv);
            } else {
                Glide.with(getActivity())
                        .load(R.drawable.bg_no_profile)
                        .into(profile_iv);
            }
        } else {
            return;
        }
    }

    @Override
    public void onUserDataChanged(UserInfoDataModel userData) {
        if (userData != null) {
            setUserData(userData);
        } else {
            return;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_ll:
                ((DashboardActivity)getActivity()).view_pager.setCurrentItem(2);
                setNavigationView(0);
                handleDrawer();
                break;
            case R.id.share_app_ll:
                setNavigationView(1);
                handleDrawer();
                break;
            case R.id.contact_us_ll:
                setNavigationView(2);
                handleDrawer();
                break;
            case R.id.rate_us_ll:
                setNavigationView(3);
                handleDrawer();
                break;
            case R.id.privacy_policy_ll:
                setNavigationView(4);
                handleDrawer();
                break;
            case R.id.log_out_ll:
                setNavigationView(5);
                handleDrawer();
                break;
        }
    }

    public void handleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public boolean closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return true;
        }
        return false;
    }

    private void setNavigationView(int position) {

        int navi_blue_clr = getResources().getColor(R.color.navi_blue);
        int custom_black_clr = getResources().getColor(R.color.custom_black);

        switch (position) {
            case 0:
                settings_ll.setBackgroundResource(R.drawable.bg_light_blue_radius_10sp);
                share_app_ll.setBackgroundResource(android.R.color.transparent);
                contact_us_ll.setBackgroundResource(android.R.color.transparent);
                rate_us_ll.setBackgroundResource(android.R.color.transparent);
                privacy_policy_ll.setBackgroundResource(android.R.color.transparent);
                log_out_ll.setBackgroundResource(android.R.color.transparent);

                settings_iv.setColorFilter(navi_blue_clr);
                share_iv.setColorFilter(custom_black_clr);
                contact_us_iv.setColorFilter(custom_black_clr);
                rate_us_iv.setColorFilter(custom_black_clr);
                privacy_policy_iv.setColorFilter(custom_black_clr);
                log_out_iv.setColorFilter(custom_black_clr);

                settings_tv.setTextColor(navi_blue_clr);
                share_this_app_tv.setTextColor(custom_black_clr);
                contact_us_tv.setTextColor(custom_black_clr);
                rate_us_tv.setTextColor(custom_black_clr);
                privacy_policy_tv.setTextColor(custom_black_clr);
                log_out_tv.setTextColor(custom_black_clr);
                break;
            case 1:
                settings_ll.setBackgroundResource(android.R.color.transparent);
                share_app_ll.setBackgroundResource(R.drawable.bg_light_blue_radius_10sp);
                contact_us_ll.setBackgroundResource(android.R.color.transparent);
                rate_us_ll.setBackgroundResource(android.R.color.transparent);
                privacy_policy_ll.setBackgroundResource(android.R.color.transparent);
                log_out_ll.setBackgroundResource(android.R.color.transparent);

                settings_iv.setColorFilter(custom_black_clr);
                share_iv.setColorFilter(navi_blue_clr);
                contact_us_iv.setColorFilter(custom_black_clr);
                rate_us_iv.setColorFilter(custom_black_clr);
                privacy_policy_iv.setColorFilter(custom_black_clr);
                log_out_iv.setColorFilter(custom_black_clr);

                settings_tv.setTextColor(custom_black_clr);
                share_this_app_tv.setTextColor(navi_blue_clr);
                contact_us_tv.setTextColor(custom_black_clr);
                rate_us_tv.setTextColor(custom_black_clr);
                privacy_policy_tv.setTextColor(custom_black_clr);
                log_out_tv.setTextColor(custom_black_clr);
                break;
            case 2:
                settings_ll.setBackgroundResource(android.R.color.transparent);
                share_app_ll.setBackgroundResource(android.R.color.transparent);
                contact_us_ll.setBackgroundResource(R.drawable.bg_light_blue_radius_10sp);
                rate_us_ll.setBackgroundResource(android.R.color.transparent);
                privacy_policy_ll.setBackgroundResource(android.R.color.transparent);
                log_out_ll.setBackgroundResource(android.R.color.transparent);

                settings_iv.setColorFilter(custom_black_clr);
                share_iv.setColorFilter(custom_black_clr);
                contact_us_iv.setColorFilter(navi_blue_clr);
                rate_us_iv.setColorFilter(custom_black_clr);
                privacy_policy_iv.setColorFilter(custom_black_clr);
                log_out_iv.setColorFilter(custom_black_clr);

                settings_tv.setTextColor(custom_black_clr);
                share_this_app_tv.setTextColor(custom_black_clr);
                contact_us_tv.setTextColor(navi_blue_clr);
                rate_us_tv.setTextColor(custom_black_clr);
                privacy_policy_tv.setTextColor(custom_black_clr);
                log_out_tv.setTextColor(custom_black_clr);
                break;
            case 3:
                settings_ll.setBackgroundResource(android.R.color.transparent);
                share_app_ll.setBackgroundResource(android.R.color.transparent);
                contact_us_ll.setBackgroundResource(android.R.color.transparent);
                rate_us_ll.setBackgroundResource(R.drawable.bg_light_blue_radius_10sp);
                privacy_policy_ll.setBackgroundResource(android.R.color.transparent);
                log_out_ll.setBackgroundResource(android.R.color.transparent);

                settings_iv.setColorFilter(custom_black_clr);
                share_iv.setColorFilter(custom_black_clr);
                contact_us_iv.setColorFilter(custom_black_clr);
                rate_us_iv.setColorFilter(navi_blue_clr);
                privacy_policy_iv.setColorFilter(custom_black_clr);
                log_out_iv.setColorFilter(custom_black_clr);

                settings_tv.setTextColor(custom_black_clr);
                share_this_app_tv.setTextColor(custom_black_clr);
                contact_us_tv.setTextColor(custom_black_clr);
                rate_us_tv.setTextColor(navi_blue_clr);
                privacy_policy_tv.setTextColor(custom_black_clr);
                log_out_tv.setTextColor(custom_black_clr);
                break;
            case 4:
                settings_ll.setBackgroundResource(android.R.color.transparent);
                share_app_ll.setBackgroundResource(android.R.color.transparent);
                contact_us_ll.setBackgroundResource(android.R.color.transparent);
                rate_us_ll.setBackgroundResource(android.R.color.transparent);
                privacy_policy_ll.setBackgroundResource(R.drawable.bg_light_blue_radius_10sp);
                log_out_ll.setBackgroundResource(android.R.color.transparent);

                settings_iv.setColorFilter(custom_black_clr);
                share_iv.setColorFilter(custom_black_clr);
                contact_us_iv.setColorFilter(custom_black_clr);
                rate_us_iv.setColorFilter(custom_black_clr);
                privacy_policy_iv.setColorFilter(navi_blue_clr);
                log_out_iv.setColorFilter(custom_black_clr);

                settings_tv.setTextColor(custom_black_clr);
                share_this_app_tv.setTextColor(custom_black_clr);
                contact_us_tv.setTextColor(custom_black_clr);
                rate_us_tv.setTextColor(custom_black_clr);
                privacy_policy_tv.setTextColor(navi_blue_clr);
                log_out_tv.setTextColor(custom_black_clr);
                break;
            case 5:
                settings_ll.setBackgroundResource(android.R.color.transparent);
                share_app_ll.setBackgroundResource(android.R.color.transparent);
                contact_us_ll.setBackgroundResource(android.R.color.transparent);
                rate_us_ll.setBackgroundResource(android.R.color.transparent);
                privacy_policy_ll.setBackgroundResource(android.R.color.transparent);
                log_out_ll.setBackgroundResource(R.drawable.bg_light_blue_radius_10sp);

                settings_iv.setColorFilter(custom_black_clr);
                share_iv.setColorFilter(custom_black_clr);
                contact_us_iv.setColorFilter(custom_black_clr);
                rate_us_iv.setColorFilter(custom_black_clr);
                privacy_policy_iv.setColorFilter(custom_black_clr);
                log_out_iv.setColorFilter(navi_blue_clr);

                settings_tv.setTextColor(custom_black_clr);
                share_this_app_tv.setTextColor(custom_black_clr);
                contact_us_tv.setTextColor(custom_black_clr);
                rate_us_tv.setTextColor(custom_black_clr);
                privacy_policy_tv.setTextColor(custom_black_clr);
                log_out_tv.setTextColor(navi_blue_clr);

                callLogout();
                break;
        }
    }

    private void callLogout() {
        if (Pref.getSpecificPref(getActivity(), USER_INFO_PREF) != null) {
            Pref.clearPref(getActivity(), USER_INFO_PREF);
            goToLoginActivity(null);
        }
    }

    //---------------------------------- Calling UserLoginInfo Api --------------------------------

    private void callUserLoginInfoApi() {
        if (getActivity() != null) {
            String user_id = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_USER_ID);
            String token = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_TOKEN);

            JsonObject auth_parameters = new JsonObject();
            JsonObject sub_parameters = new JsonObject();
            if (user_id != null && !user_id.isEmpty() && !user_id.equals("") &&
                    token != null && !token.isEmpty() && !user_id.equals("")) {
                auth_parameters.addProperty(KEY_USER_ID, user_id);
                sub_parameters.addProperty(KEY_ID, user_id);
                sub_parameters.addProperty(KEY_TOKEN, token);
                auth_parameters.add(KEY_AUTH, sub_parameters);
                if (auth_parameters == null) return;
                makeWebRequest(getActivity()).getUserLoginInfo(auth_parameters, this);
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null || response.code() > 200 || response.code() >= 400) return;
        if (webRequest.getWebRequestID() == WEB_USER_LOGIN_INFO_CODE) {
            if (response.code() == 200)
                handleUserInfoData(response);
        }
    }

    private void handleUserInfoData(Response response) {
        Object object = response.body();
        UserInfoModel userInfo = (UserInfoModel) object;
        if (userInfo.getCode() == 200) {
            UserInfoDataModel userData = userInfo.getMessage().getUserdet();
            if (userData != null) {
                Pref.setUserDataPref(getActivity(), userData);
                setUserData(userData);
            }
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        showSimpleToast(throwable.getMessage());
    }

    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

}
