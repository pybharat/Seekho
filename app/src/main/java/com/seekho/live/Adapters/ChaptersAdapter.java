package com.seekho.live.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.Courses.ChaptersModel.ChaptersDataModel;
import com.seekho.live.Models.Courses.ChaptersModel.TopicsListModel;
import com.seekho.live.R;
import com.seekho.live.Utils.Constant;

import java.util.List;

public class ChaptersAdapter extends AppBaseAdapter {

    Context context;
    List<ChaptersDataModel> chaptersDataList;
    OnRecyclerListener recyclerListener;

    RecyclerView questions_rv;
    TopicsAdapter topicsAdapter;

    public ChaptersAdapter(Context context,
                           List<ChaptersDataModel> chaptersDataList,
                           OnRecyclerListener recyclerListener) {
        this.context = context;
        this.chaptersDataList = chaptersDataList;
        this.recyclerListener = recyclerListener;
    }

    public ChaptersAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_subjects_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null) return;
        if (chaptersDataList != null && chaptersDataList.size() > 0) {
            if (recyclerListener == null) return;
            recyclerListener.onItemClick(view, position);

            questions_rv = getView().findViewById(R.id.questions_rv);

            List<TopicsListModel> topics_list = chaptersDataList.get(position).getTopicslist();
            setQuestionsRV(topics_list, chaptersDataList.get(position).getEnrollstatus());
        } else {
            return;
        }
    }

    private void setQuestionsRV(List<TopicsListModel> chaptersData, int enrollStatus) {
        if (chaptersData != null && chaptersData.size() > 0) {
            questions_rv.setLayoutManager(new LinearLayoutManager(context));
            topicsAdapter = new TopicsAdapter(context, chaptersData, enrollStatus);
            questions_rv.setAdapter(topicsAdapter);
        }
    }

    @Override
    public int getDataCount() {
        return chaptersDataList.size();
    }

    public static class TopicsAdapter extends AppBaseAdapter {

        Context context;
        List<TopicsListModel> topics_list;
        OnRecyclerListener recyclerListener;
        int enroll_status = -1;

        LinearLayout lecture_ll;
        TextView topic_name_tv, total_duration_tv;
        ImageView topic_icon_iv;

        public TopicsAdapter(Context context, List<TopicsListModel> topics_list, int enroll_status) {
            this.context = context;
            this.topics_list = topics_list;
            this.enroll_status = enroll_status;
        }

        public TopicsAdapter(Context context, OnRecyclerListener recyclerListener) {
            this.context = context;
            this.recyclerListener = recyclerListener;
        }

        @Override
        public int layoutResourceID() {
            return R.layout.rv_questions_layout;
        }

        @Override
        public void initializeComponents(View view, int position) {
            if (getView() == null) return;
            lecture_ll = getView().findViewById(R.id.lecture_ll);
            topic_name_tv = getView().findViewById(R.id.topic_name_tv);
            total_duration_tv = getView().findViewById(R.id.total_duration_tv);
            topic_icon_iv = getView().findViewById(R.id.topic_icon_iv);

            if (topics_list != null && topics_list.size() > 0) {

                TopicsListModel topicData = topics_list.get(position);
                if (topicData.getCt_type().equals("video")) {
                    topic_name_tv.setText(topicData.getCt_name());
                    if (topicData.getVideoduration().equals("") || topicData.getVideoduration().equals("0")) {
                        total_duration_tv.setText("00:00");
                    } else {
                        total_duration_tv.setText(topicData.getVideoduration());
                    }
                    Glide.with(context).load(R.drawable.sand_watch_icon).into(topic_icon_iv);
                } else if (topicData.getCt_type().equals("quiz")) {
                    topic_name_tv.setText(topicData.getCt_name());
                    if (topicData.getQuizquestions().equals("") || topicData.getQuizquestions().equals("0")) {
                        total_duration_tv.setText("Total Questions: 0");
                    } else {
                        total_duration_tv.setText("Total Questions: " + topicData.getQuizquestions());
                    }

                    Glide.with(context).load(R.drawable.quiz_icon).into(topic_icon_iv);
                }
                lecture_ll.setTag(position);
                lecture_ll.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.lecture_ll:
                    int position = (Integer) view.getTag();
                    topicsItemClicks(position);
                    break;
            }
        }

        private void topicsItemClicks(int position) {

            if (topics_list != null && topics_list.size() > 0) {
                TopicsListModel topics = topics_list.get(position);
                String cc_type = topics.getCt_type();
                if (Constant.isEnrollDialogEnable) {
                    Bundle bundle = new Bundle();
                    if (enroll_status == 0) {
                        bundle.putInt(KEY_OTP_TYPE, 1);
                        getEnrollDialog(context, bundle);
                    } else if (enroll_status == 2) {
                        bundle.putInt(KEY_OTP_TYPE, 1);
                        getEnrollDialog(context, bundle);
                    } else if (enroll_status == 1) {
                        if (cc_type.equals("video")) {
                            bundle.putString(KEY_TOPIC_ID, topics.getCt_id());
                            bundle.putString(KEY_TITLE, topics.getCt_name());
                            goToSubjectsLecturesActivity(bundle);
                        } else if (cc_type.equals("quiz")) {
                            bundle.putString(KEY_TOPIC_ID, topics.getCt_id());
                            bundle.putString(KEY_TITLE, topics.getCt_name());
                            getQuizAleartDialog(context, bundle);
                        }
                    }
                }
            } else {
                return;
            }
        }

        @Override
        public int getDataCount() {
            return topics_list.size();
        }
    }
}
