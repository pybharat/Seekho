package com.seekho.live.Activities;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.Courses.CoursesModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class AllMainCoursesActivity extends AppBaseActivity {

    RecyclerView promotion_rv, courses_rv;
    SwipeRefreshLayout swipe_refresh_layout;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_all_main_courses;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        promotion_rv = findViewById(R.id.promotion_rv);
        courses_rv = findViewById(R.id.courses_rv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);

        setPromotionalBannerRV(this, promotion_rv);

        callCoursesApi();
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        callCoursesApi();
    }

    private void callCoursesApi() {
        String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

        JsonObject authParameter = new JsonObject();
        JsonObject subParameter = new JsonObject();
        if (user_id != null && !user_id.isEmpty() && token != null && !token.isEmpty()) {
            authParameter.addProperty(KEY_USER_ID, user_id);
            subParameter.addProperty(KEY_ID, user_id);
            subParameter.addProperty(KEY_TOKEN, token);
            authParameter.add(KEY_AUTH, subParameter);
            if (authParameter == null) return;
            Fun.showLoader(this);
            makeWebRequest(this).callCoursesApi(authParameter, this);
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
            Fun.finishLoader(this);
            if (webRequest.getWebRequestID() == WebContants.WEB_GET_COURSES_CODE) {
                handleAllCoursesResponse(response);
            }
    }

    private void handleAllCoursesResponse(Response response) {
        Object object = response.body();
        CoursesModel courses = (CoursesModel) object;
        if (courses.getCode() == 200) {
            swipe_refresh_layout.setRefreshing(false);
            List<AllCoursesData> coursesData = courses.getMessage().getAllcourses();
            if (coursesData != null && coursesData.size() > 0) {
                setCoursesRV(this, courses_rv, coursesData);
            }
        } else {
            return;
        }
    }

    @Override
    public void onQueryChanged(String newText) {
        if (getCoursesRV().getCoursesAdapter() != null) {
            getCoursesRV().getCoursesAdapter().coursesSearchFilter(newText);
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        showSimpleToast(throwable.getMessage());
    }
}
