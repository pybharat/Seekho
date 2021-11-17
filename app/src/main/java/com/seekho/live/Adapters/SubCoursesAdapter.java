package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.Courses.SubCourses.SubCategoryData;
import com.seekho.live.R;

import java.util.List;

public class SubCoursesAdapter extends AppBaseAdapter {

    Context context;
    List<SubCategoryData> subCategoryDataList;
    OnRecyclerListener recyclerListener;

    public SubCoursesAdapter(Context context, List<SubCategoryData> subCategoryDataList, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.subCategoryDataList = subCategoryDataList;
        this.recyclerListener = recyclerListener;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_courses;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (view == null) return;
        if (recyclerListener == null) return;
        recyclerListener.onItemClick(view, position);
    }

    @Override
    public int getDataCount() {
        return subCategoryDataList.size();
    }

}
