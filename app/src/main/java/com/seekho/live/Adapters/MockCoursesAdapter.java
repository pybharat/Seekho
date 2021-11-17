package com.seekho.live.Adapters;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.MockTests.MockTestCategoryDataModel;
import com.seekho.live.R;

import java.util.List;

public class MockCoursesAdapter extends AppBaseAdapter {

    Context context;
    List<MockTestCategoryDataModel> categoryDataList;
    OnRecyclerListener recyclerListener;

    CardView card_view;
    ImageView test_thumb_iv;
    TextView test_title_tv,test_desc_tv;

    public MockCoursesAdapter(Context context) {
        this.context = context;
    }

    public MockCoursesAdapter(Context context, List<MockTestCategoryDataModel> categoryDataList, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.categoryDataList = categoryDataList;
        this.recyclerListener = recyclerListener;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_mock_tests_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null)return;
        if (recyclerListener == null)return;
        recyclerListener.onItemClick(view,position);

        card_view = view.findViewById(R.id.card_view);
        test_thumb_iv = view.findViewById(R.id.test_thumb_iv);
        test_title_tv = view.findViewById(R.id.test_title_tv);
        test_desc_tv = view.findViewById(R.id.test_desc_tv);

        if (categoryDataList != null && categoryDataList.size() > 0){
            MockTestCategoryDataModel categoryData = categoryDataList.get(position);
            if (categoryData != null){
                Glide.with(context).load(categoryData.getMc_image()).into(test_thumb_iv);
                test_title_tv.setText(categoryData.getMc_name());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    test_desc_tv.setText(Html.fromHtml(categoryData.getMc_meta_description(),Html.FROM_HTML_MODE_LEGACY));
                } else {
                    test_desc_tv.setText(Html.fromHtml(categoryData.getMc_meta_description()));
                }

                Bundle bundle = new Bundle();
                card_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bundle.putString(KEY_TITLE,categoryData.getMc_name());
                        bundle.putString(KEY_MOCK_CATEGORY,categoryData.getMc_id());
                        goToMockTestActivity(context,bundle);
                    }
                });
            }
        }
    }

    @Override
    public int getDataCount() {
        return categoryDataList.size();
    }
}
