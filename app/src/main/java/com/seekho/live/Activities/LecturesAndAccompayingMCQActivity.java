package com.seekho.live.Activities;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonObject;
import com.seekho.live.Adapters.ChaptersAdapter;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.Courses.ChaptersModel.ChaptersDataModel;
import com.seekho.live.Models.Courses.ChaptersModel.ChaptersModel;
import com.seekho.live.Models.Courses.ChaptersModel.TopicsListModel;
import com.seekho.live.Models.Courses.SubCourses.SubCategoryData;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class LecturesAndAccompayingMCQActivity extends AppBaseActivity {

    RecyclerView subjects_rv;
    ChaptersAdapter chaptersAdapter;

    int expandable_position = -1;

    String course_id = "";
    String sub_course_id = "";

    SwipeRefreshLayout swipe_refresh_layout;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_lectures_and_accompaying_mcq;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        subjects_rv = findViewById(R.id.subjects_rv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);

        if (getIntent().getExtras() != null) {
            course_id = getIntent().getExtras().getString(KEY_COURSE_ID);
            sub_course_id = getIntent().getExtras().getString(KEY_SUB_COURSES_ID);
        }

        callChaptersApi();
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        callChaptersApi();
    }

    private void callChaptersApi() {
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
                    makeWebRequest(this).getMainCoursesChaptersList(authParameter, this);
                } else {
                    authParameter.addProperty(KEY_USER_ID, userId);
                    authParameter.addProperty(KEY_COURSE_ID, course_id);
                    authParameter.addProperty(KEY_SUB_COURSES_ID, sub_course_id);
                    subParameters.addProperty(KEY_ID, userId);
                    subParameters.addProperty(KEY_TOKEN, token);
                    authParameter.add(KEY_AUTH, subParameters);
                    if (authParameter == null) return;
                    Fun.showLoader(this);
                    makeWebRequest(this).getMainSubCoursesChaptersList(authParameter, this);
                }
            } else {
                return;
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() >= 400) return;
        if (response.code() == 200)
            Fun.finishLoader(this);
        if (webRequest.getWebRequestID() == WEB_SUB_COURSES_CHAPTERS_CODE ||
                webRequest.getWebRequestID() == WEB_MAIN_COURSES_CHAPTERS_CODE) {
            handleChaptersData(response);
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        showSimpleToast(throwable.getMessage());
        Fun.finishLoader(this);
        swipe_refresh_layout.setRefreshing(false);
    }

    private void handleChaptersData(Response response) {
        Object object = response.body();
        ChaptersModel chaptersModel = (ChaptersModel) object;
        if (chaptersModel != null && chaptersModel.getCode() == 200) {
            swipe_refresh_layout.setRefreshing(false);
            List<ChaptersDataModel> chaptersDataList = chaptersModel.getMessage().getChapterlist();
            if (chaptersDataList != null && chaptersDataList.size() > 0) {
                Log.d(getClass().getSimpleName(), chaptersDataList.toString());
                setSubjectsRV(chaptersDataList);

                SubCategoryData data = chaptersModel.getMessage().getSubcategorydet();
                if (data != null){
                    getToolbarFragment().setTitle(data.getCsc_name());
                }
            }
        }
    }

    private void setSubjectsRV(List<ChaptersDataModel> chaptersDataList) {
        if (chaptersDataList == null && chaptersDataList.size() <= 0) return;
        if (subjects_rv.getItemAnimator() != null) {
            ((SimpleItemAnimator) subjects_rv.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        subjects_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        subjects_rv.setNestedScrollingEnabled(false);
        chaptersAdapter = new ChaptersAdapter(this, chaptersDataList, new OnRecyclerListener() {
            @Override
            public void onItemClick(View view, int position) {
                CardView subject_title_cv = view.findViewById(R.id.subject_title_cv);
                LinearLayout lec_and_quiz_ll = view.findViewById(R.id.lec_and_quiz_ll);
                ImageView more_or_less_iv = view.findViewById(R.id.more_or_less_iv);
                TextView chapter_name_tv = view.findViewById(R.id.chapter_name_tv);

                if (chaptersDataList != null && chaptersDataList.size() > 0) {
                    ChaptersDataModel chaptersData = chaptersDataList.get(position);
                    subject_title_cv.setTag(position);

                    chapter_name_tv.setText(chaptersData.getCc_name());
                    updateViewVisibility(more_or_less_iv, View.GONE);

                    List<TopicsListModel> topicDataList = chaptersData.getTopicslist();
                    if (topicDataList.size() == 0) {
                        updateViewVisibility(lec_and_quiz_ll, View.GONE);
                    } else {
                        boolean isExpanded = position == expandable_position;
                        updateViewVisibility(lec_and_quiz_ll, isExpanded ? View.VISIBLE : View.GONE);
                        subject_title_cv.setActivated(isExpanded);

                        subject_title_cv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int previous_position = expandable_position;
                                int selected_position = (Integer) view.getTag();
                                if (previous_position == selected_position) {
                                    expandable_position = -1;

                                    chaptersAdapter.notifyItemChanged(previous_position);
                                } else {
                                    expandable_position = selected_position;

                                    if (previous_position != -1) {
                                        chaptersAdapter.notifyItemChanged(previous_position);
                                    }
                                    chaptersAdapter.notifyItemChanged(expandable_position);
                                }
                            }
                        });
                    }
                }
            }
        });
        subjects_rv.setAdapter(chaptersAdapter);
    }
}
