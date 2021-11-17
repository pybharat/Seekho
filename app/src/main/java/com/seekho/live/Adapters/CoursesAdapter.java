package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CoursesAdapter extends AppBaseAdapter {

    Context context;
    OnRecyclerListener recyclerListener;
    List<AllCoursesData> allCoursesDataList;

    List<AllCoursesData> searchDataList;

    public CoursesAdapter(Context context) {
        this.context = context;
    }

    public CoursesAdapter(Context context, List<AllCoursesData> allCoursesDataList, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.allCoursesDataList = allCoursesDataList;
        this.recyclerListener = recyclerListener;

        searchDataList = new ArrayList<>();
        searchDataList.addAll(allCoursesDataList);
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_courses;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null) return;
        if (recyclerListener == null) return;
        recyclerListener.onItemClick(view, position);
    }

    @Override
    public int getDataCount() {
        return allCoursesDataList.size();
    }

    public List<AllCoursesData> getAllCoursesDataList() {
        return allCoursesDataList;
    }

    public List<AllCoursesData> getSearchDataList() {
        return searchDataList;
    }

    public void coursesSearchFilter(String charText) {
        if (charText == null && charText.length() <= 0) return;
        charText = charText.toLowerCase(Locale.getDefault());
        allCoursesDataList.clear();
        if (charText.length() == 0) {
            allCoursesDataList.addAll(searchDataList);
        } else {
            for (AllCoursesData allCoursesData : searchDataList) {
                if (allCoursesData.getCc_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    allCoursesDataList.add(allCoursesData);
                }
            }
        }
        notifyDataSetChanged();
    }
}
