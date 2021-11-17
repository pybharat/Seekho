package com.seekho.live.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.Models.Enroll.EnrollDataModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public class EnrollNowDialog extends AppBaseDialogFragment {

    Context context;
    TextView enroll_now_btn_tv;
    ImageView dismiss_iv;

    TextView title_tv;

    String course_id = "";
    String sub_course_id = "";
    String course_name = "";
    String have_sub_category = "";

    LinearLayout before_enroll_ll,enrolled_ll;
    TextView successful_enroll_tv;

    //---------------- On Alert -------------
    LinearLayout enroll_alert_ll;
    TextView enroll_alert_title_tv;
    TextView enroll_alert_go_back_btn_tv;
    int type = 0;

    public EnrollNowDialog(Context context) {
        this.context = context;
    }

    public EnrollNowDialog() {
    }

    public static EnrollNowDialog getInstance(Context context) {
        EnrollNowDialog enrollNowDialog = new EnrollNowDialog(context);
        return enrollNowDialog;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_enroll_now;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;
        enroll_now_btn_tv = getView().findViewById(R.id.enroll_now_btn_tv);
        dismiss_iv = getView().findViewById(R.id.dismiss_iv);
        title_tv = getView().findViewById(R.id.title_tv);
        before_enroll_ll = getView().findViewById(R.id.before_enroll_ll);
        enrolled_ll = getView().findViewById(R.id.enrolled_ll);
        successful_enroll_tv = getView().findViewById(R.id.successful_enroll_tv);
        enroll_alert_ll = getView().findViewById(R.id.enroll_alert_ll);
        enroll_alert_title_tv = getView().findViewById(R.id.enroll_alert_title_tv);
        enroll_alert_go_back_btn_tv = getView().findViewById(R.id.enroll_alert_go_back_btn_tv);

        if (getArguments() != null) {
            type = getArguments().getInt(KEY_OTP_TYPE);

            if (type == 0){
                course_id = getArguments().getString(KEY_COURSE_ID);
                sub_course_id = getArguments().getString(KEY_SUB_COURSES_ID);
                course_name = getArguments().getString(KEY_COURSE_NAME);
                have_sub_category = getArguments().getString(KEY_HAVE_SUB_CATEGORY);
                title_tv.setText(course_name);
                updateViewVisibility(enroll_alert_ll,View.GONE);
            } else if (type == 1){
                updateViewVisibility(enroll_alert_ll,View.VISIBLE);
                updateViewVisibility(before_enroll_ll,View.GONE);
                updateViewVisibility(enroll_alert_title_tv,View.GONE);
                //enroll_alert_title_tv.setText(course_name);
            }
        }

        enroll_now_btn_tv.setOnClickListener(this);
        dismiss_iv.setOnClickListener(this);
        enroll_alert_go_back_btn_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enroll_now_btn_tv:
                callEnrollApis();
                break;
            case R.id.dismiss_iv:
                getDialog().dismiss();
                break;
            case R.id.enroll_alert_go_back_btn_tv:
                getActivity().onBackPressed();
                break;
        }
    }

    private void callEnrollApis() {
        if (Pref.getSpecificPref(getActivity(), USER_INFO_PREF) != null) {
            String user_id = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_USER_ID);
            String token = Pref.getValueFromPref(getActivity(), USER_INFO_PREF, KEY_TOKEN);

            JsonObject authParameters = new JsonObject();
            JsonObject subParameters = new JsonObject();

            if (user_id != null && !user_id.isEmpty() && token != null && !token.isEmpty()) {
                if (sub_course_id != null && !sub_course_id.isEmpty() && !sub_course_id.equals("")) {
                    authParameters.addProperty(KEY_USER_ID, user_id);
                    authParameters.addProperty(KEY_SUB_COURSES_ID, sub_course_id);
                    subParameters.addProperty(KEY_ID, user_id);
                    subParameters.addProperty(KEY_TOKEN, token);
                    authParameters.add(KEY_AUTH, subParameters);
                    if (authParameters == null) return;
                    makeWebRequest(getActivity()).getEnrollSubCourseByUser(authParameters, this);
                } else {
                    if (course_id != null && !course_id.isEmpty()) {
                        authParameters.addProperty(KEY_USER_ID, user_id);
                        authParameters.addProperty(KEY_COURSE_ID, course_id);
                        subParameters.addProperty(KEY_ID, user_id);
                        subParameters.addProperty(KEY_TOKEN, token);
                        authParameters.add(KEY_AUTH, subParameters);
                        if (authParameters == null) return;
                        makeWebRequest(getActivity()).getEnrollCourseByUser(authParameters, this);
                    }
                }
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
        if (webRequest.getWebRequestID() == WEB_ENROLL_COURSES_BY_USER_CODE) {
            handleEnrollMainCoursesResponse(response);
        } else if (webRequest.getWebRequestID() == WEB_ENROLL_SUB_COURSES_BY_USER_CODE){
            handleEnrollSubCoursesResponse(response);
        }
    }

    private void handleEnrollSubCoursesResponse(Response response) {
        Object object = response.body();
        EnrollDataModel enrollData = (EnrollDataModel)object;
        if (enrollData.getCode() == 200){
            updateViewVisibility(enrolled_ll,View.VISIBLE);
            updateViewVisibility(before_enroll_ll,View.GONE);
            successful_enroll_tv.setText("You Successfully Enrolled " + course_name + " Now");
            createDelay(1500, new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_COURSE_ID, course_id);
                    bundle.putString(KEY_SUB_COURSES_ID, sub_course_id);
                    if (have_sub_category.equals("1")) {
                        goToSubCoursesActivity(bundle);
                        getDialog().dismiss();
                    } else if (have_sub_category.equals("0") || have_sub_category.equals("2")) {
                        goToLecturesAndAccMCQActivity(bundle);
                        getDialog().dismiss();
                    }
                }
            });
        } else {
            return;
        }
    }

    private void handleEnrollMainCoursesResponse(Response response) {
        Object object = response.body();
        EnrollDataModel enrollData = (EnrollDataModel)object;
        if (enrollData.getCode() == 200){
            updateViewVisibility(enrolled_ll,View.VISIBLE);
            updateViewVisibility(before_enroll_ll,View.GONE);
            successful_enroll_tv.setText("You Successfully Enrolled " + course_name + " Now");
            createDelay(1500, new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    if (have_sub_category.equals("1")) {
                        bundle.putString(KEY_COURSE_ID, course_id);
                        goToSubCoursesActivity(bundle);
                        getDialog().dismiss();
                    } else if (have_sub_category.equals("0") || have_sub_category.equals("2")) {
                        bundle.putString(KEY_COURSE_ID, course_id);
                        goToLecturesAndAccMCQActivity(bundle);
                        getDialog().dismiss();
                    }
                }
            });
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Log.d(getClass().getSimpleName(),throwable.getMessage());
        showSimpleToast(throwable.getMessage());
    }
}
