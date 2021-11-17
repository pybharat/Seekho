package com.seekho.live.Activities;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.Adapters.CoursesAdapter;
import com.seekho.live.Adapters.PromotionsAdapter;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.Courses.SubCourses.SubCategoryData;
import com.seekho.live.Models.Courses.SubCourses.SubCourseDivisionModel;
import com.seekho.live.Models.Courses.SubCourses.SubCoursesModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class SubCoursesActivity extends AppBaseActivity {

    RecyclerView promotion_rv, sub_courses_rv;
    PromotionsAdapter promotionsAdapter;
    CoursesAdapter coursesAdapter;

    String course_id = "";
    String sub_course_id = "";

    SwipeRefreshLayout swipe_refresh_layout;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_sub_courses;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        promotion_rv = findViewById(R.id.promotion_rv);
        sub_courses_rv = findViewById(R.id.sub_courses_rv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);

        if (getIntent().getExtras() != null) {
            course_id = getIntent().getExtras().getString(KEY_COURSE_ID);
            sub_course_id = getIntent().getExtras().getString(KEY_SUB_COURSES_ID);
        }

        setPromotionalBannerRV(this, promotion_rv);
        callSubCoursesApi();

        swipe_refresh_layout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        callSubCoursesApi();
    }

    private void callSubCoursesApi() {
        if (course_id != null && !course_id.equals("")) {
            JsonObject authParameter = new JsonObject();
            JsonObject subParameters = new JsonObject();

            String userId = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
            String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

            if (userId != null && token != null) {

                if (sub_course_id == null || sub_course_id.equals("")) {
                    authParameter.addProperty(KEY_USER_ID, userId);
                    authParameter.addProperty(KEY_COURSE_ID, course_id);
                    subParameters.addProperty(KEY_ID, userId);
                    subParameters.addProperty(KEY_TOKEN, token);
                    authParameter.add(KEY_AUTH, subParameters);
                    if (authParameter == null) return;
                    Fun.showLoader(this);
                    makeWebRequest(this).callSubCoursesApi(authParameter, this);
                } else {
                    authParameter.addProperty(KEY_USER_ID, userId);
                    authParameter.addProperty(KEY_COURSE_ID, course_id);
                    authParameter.addProperty(KEY_SUB_COURSES_ID, sub_course_id);
                    subParameters.addProperty(KEY_ID, userId);
                    subParameters.addProperty(KEY_TOKEN, token);
                    authParameter.add(KEY_AUTH, subParameters);
                    if (authParameter == null) return;
                    Fun.showLoader(this);
                    makeWebRequest(this).getSubCoursesDivisionList(authParameter, this);
                }
            } else {
                return;
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
        Fun.finishLoader(this);
        if (webRequest.getWebRequestID() == WEB_SUB_COURSES_CODE) {
                handleSubCoursesResponses(response);
        } else if (webRequest.getWebRequestID() == WEB_SUB_COURSES_DIVISION_CODE) {
                handleSubCoursesDivisionResponse(response);
        }
    }

    private void handleSubCoursesDivisionResponse(Response response) {
        Object object = response.body();
        SubCourseDivisionModel subCourseDivisionModel = (SubCourseDivisionModel) object;
        Log.d(getClass().getSimpleName(), subCourseDivisionModel.toString());
        if (subCourseDivisionModel != null && subCourseDivisionModel.getCode() == 200) {
            swipe_refresh_layout.setRefreshing(false);
            SubCategoryData subCategoryData = subCourseDivisionModel.getMessage().getSubcategorydet();
            if (subCategoryData != null) {
                getToolbarFragment().setTitle(subCategoryData.getCsc_name());
            }

            List<SubCategoryData> dataList = subCourseDivisionModel.getMessage().getSubdivison();
            if (dataList == null) return;
            setSubCoursesRV(this, sub_courses_rv, dataList);
        } else {
            return;
        }
    }

    private void handleSubCoursesResponses(Response response) {
        Object object = response.body();
        SubCoursesModel subCourses = (SubCoursesModel) object;
        Log.d(getClass().getSimpleName(), subCourses.toString());
        if (subCourses != null && subCourses.getCode() == 200) {
            swipe_refresh_layout.setRefreshing(false);
            AllCoursesData courseData = subCourses.getMessage().getCategorydet();
            if (courseData != null) {
                getToolbarFragment().setTitle(courseData.getCc_name());
            }

            List<SubCategoryData> dataList = subCourses.getMessage().getSubcategory();
            if (dataList == null) return;
            setSubCoursesRV(this, sub_courses_rv, dataList);
        } else if (subCourses.getCode() > 200 && subCourses.getCode() >= 400) {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null)return;
        showSimpleToast(throwable.getMessage());
        Fun.finishLoader(this);
        swipe_refresh_layout.setRefreshing(false);
    }
}
