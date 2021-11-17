package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.seekho.live.Adapters.CoursesAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.R;
import com.seekho.live.Utils.Constant;

import java.util.List;

public class CoursesRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;
    List<AllCoursesData> allCoursesDataList;

    CoursesAdapter coursesAdapter;

    CardView card_view;
    TextView course_title_tv;
    ImageView thumbnail_iv;
    TextView enroll_btn_tv;

    int type = 0;

    public CoursesRV(Context context, RecyclerView recyclerView,
                     List<AllCoursesData> allCoursesDataList) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;
        this.allCoursesDataList = allCoursesDataList;

        setCoursesRV();
    }

    public CoursesRV(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private void setCoursesRV() {
        if (recyclerView == null) return;
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        coursesAdapter = new CoursesAdapter(context, allCoursesDataList, this);
        recyclerView.setAdapter(coursesAdapter);
    }

    @Override
    public void initializeComponents(View view) {
        if (view == null) return;
        card_view = view.findViewById(R.id.card_view);
        course_title_tv = view.findViewById(R.id.course_title_tv);
        thumbnail_iv = view.findViewById(R.id.thumbnail_iv);
        enroll_btn_tv = view.findViewById(R.id.enroll_btn_tv);

        card_view.setOnClickListener(this);
        enroll_btn_tv.setOnClickListener(this);
    }

    public CoursesAdapter getCoursesAdapter() {
        return coursesAdapter;
    }

    @Override
    public void setData(View view, int position) {
        if (allCoursesDataList != null && allCoursesDataList.size() > 0) {
            AllCoursesData allCoursesData = allCoursesDataList.get(position);

            course_title_tv.setText(allCoursesData.getCc_name());
            Glide.with(context).load(allCoursesData.getCc_thumbnail()).into(thumbnail_iv);

            card_view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    if (allCoursesData.getCc_have_subcategory().equals("1")) {
                        bundle.putString(KEY_COURSE_ID, allCoursesData.getCc_id());
                        goToSubCoursesActivity(bundle);
                    } else if (allCoursesData.getCc_have_subcategory().equals("0") || allCoursesData.getCc_have_subcategory().equals("2")) {
                        bundle.putString(KEY_COURSE_ID, allCoursesData.getCc_id());
                        goToLecturesAndAccMCQActivity(bundle);
                        return;
                    }
                }
            });

            if (Constant.isEnrollDialogEnable){
                if (getType() == 1){
                    updateViewVisibility(enroll_btn_tv,View.GONE);
                } else {
                    if (allCoursesData.getEnrollstatus() == 0){
                        enroll_btn_tv.setText("Enroll Now");
                        enroll_btn_tv.setEnabled(true);
                        enroll_btn_tv.setTextColor(getResources().getColor(R.color.light_blue));
                    } else if (allCoursesData.getEnrollstatus() == 1){
                        enroll_btn_tv.setText("Enrolled");
                        enroll_btn_tv.setEnabled(false);
                        enroll_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
                    } else if (allCoursesData.getEnrollstatus() == 2){
                        enroll_btn_tv.setText("Renew");
                        enroll_btn_tv.setEnabled(true);
                        enroll_btn_tv.setTextColor(getResources().getColor(R.color.light_blue));
                    }

                    enroll_btn_tv.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (context != null){
                                Bundle bundle = new Bundle();
                                bundle.putString(KEY_COURSE_ID,allCoursesData.getCc_id());
                                bundle.putString(KEY_COURSE_NAME,allCoursesData.getCc_name());
                                bundle.putString(KEY_HAVE_SUB_CATEGORY,allCoursesData.getCc_have_subcategory());
                                getEnrollDialog(context,bundle);
                            }
                        }
                    });
                }
            } else {
                enroll_btn_tv.setEnabled(false);
                enroll_btn_tv.setTextColor(getResources().getColor(R.color.mid_grey));
            }
        }
    }

    @Override
    public void onQueryChanged(String newText) {
        if (newText.length() > 0){
            showSimpleToast(newText);
        } else {
            return;
        }
    }
}
