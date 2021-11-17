package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.seekho.live.Adapters.SubCoursesAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.Models.Courses.SubCourses.SubCategoryData;
import com.seekho.live.R;
import com.seekho.live.Utils.Constant;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public class SubCoursesRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;
    List<SubCategoryData> subCategoryDataList;

    SubCoursesAdapter subCoursesAdapter;

    CardView card_view;
    ImageView thumbnail_iv;
    TextView course_title_tv;
    TextView open_test_btn_tv;
    LinearLayout desc_ll;
    TextView desc_tv;
    ImageView next_iv;

    TextView enroll_btn_tv;

    public SubCoursesRV(Context context, RecyclerView recyclerView, List<SubCategoryData> subCategoryDataList) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;
        this.subCategoryDataList = subCategoryDataList;

        setSubCoursesRV();
    }

    private void setSubCoursesRV() {
        if (recyclerView == null) return;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        subCoursesAdapter = new SubCoursesAdapter(context, subCategoryDataList, this);
        if (subCoursesAdapter == null) return;
        recyclerView.setAdapter(subCoursesAdapter);
    }

    @Override
    public void initializeComponents(View view) {
        if (view == null) return;
        card_view = view.findViewById(R.id.card_view);
        thumbnail_iv = view.findViewById(R.id.thumbnail_iv);
        course_title_tv = view.findViewById(R.id.course_title_tv);
        open_test_btn_tv = view.findViewById(R.id.open_test_btn_tv);
        desc_ll = view.findViewById(R.id.desc_ll);
        desc_tv = view.findViewById(R.id.desc_wv);
        next_iv = view.findViewById(R.id.next_iv);
        enroll_btn_tv = view.findViewById(R.id.enroll_btn_tv);

        updateViewVisibility(desc_ll, VISIBLE);
        updateViewVisibility(desc_tv, VISIBLE);
        updateViewVisibility(next_iv, VISIBLE);
    }

    @Override
    public void setData(View view, int position) {
        if (subCategoryDataList != null && subCategoryDataList.size() > 0) {
            SubCategoryData subCategoryData = subCategoryDataList.get(position);

            Glide.with(context).load(subCategoryData.getCsc_thumbnail()).into(thumbnail_iv);
            course_title_tv.setText(subCategoryData.getCsc_name());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                desc_tv.setText(Html.fromHtml(subCategoryData.getCsc_meta_description(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                desc_tv.setText(Html.fromHtml(subCategoryData.getCsc_meta_description()));
            }

            card_view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_COURSE_ID, subCategoryData.getCc_id());
                    bundle.putString(KEY_SUB_COURSES_ID, subCategoryData.getCsc_id());
                    if (subCategoryData.getCsc_have_subcategory().equals("0") ||
                            subCategoryData.getCsc_have_subcategory().equals("2")) {
                        goToLecturesAndAccMCQActivity(bundle);
                    } else if (subCategoryData.getCsc_have_subcategory().equals("1")) {
                        goToSubCoursesActivity(bundle);
                    }
                }
            });

            if (Constant.isEnrollDialogEnable){
                if (subCategoryData.getEnrollstatus() == 0){
                    enroll_btn_tv.setText("Enroll Now");
                    enroll_btn_tv.setEnabled(true);
                    enroll_btn_tv.setTextColor(getResources().getColor(R.color.light_blue));
                } else if (subCategoryData.getEnrollstatus() == 1){
                    enroll_btn_tv.setText("Enrolled");
                    enroll_btn_tv.setEnabled(false);
                    enroll_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
                } else if (subCategoryData.getEnrollstatus() == 2){
                    enroll_btn_tv.setText("Renew");
                    enroll_btn_tv.setEnabled(true);
                    enroll_btn_tv.setTextColor(getResources().getColor(R.color.light_blue));
                }

                enroll_btn_tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (context != null){
                            Bundle bundle = new Bundle();
                            bundle.putString(KEY_COURSE_ID,subCategoryData.getCc_id());
                            bundle.putString(KEY_SUB_COURSES_ID,subCategoryData.getCsc_id());
                            bundle.putString(KEY_COURSE_NAME,subCategoryData.getCsc_name());
                            bundle.putString(KEY_HAVE_SUB_CATEGORY,subCategoryData.getCsc_have_subcategory());
                            getEnrollDialog(context,bundle);
                        }
                    }
                });
            } else {
                enroll_btn_tv.setEnabled(false);
                enroll_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        super.onWebRequestSuccess(webRequest, response);
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        super.onWebFailure(throwable);
    }
}
