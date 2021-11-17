package com.seekho.live.Activities;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.JsonObject;
import com.seekho.live.AppBase.YouTubeAppBaseActivity;
import com.seekho.live.Models.Courses.ChaptersModel.TopicVideosModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebRequest;

import io.github.kexanie.library.MathView;
import retrofit2.Response;

public class SubjectsLectureActivity extends YouTubeAppBaseActivity {

    String topic_id = "";
    String title = "";

    YouTubePlayerView you_tube_pv;
    VimeoPlayerView vimeo_player;
    TextView title_tv;
    ImageView back_iv;
    MathView desc_wv;

    CardView youtube_cv, vimeo_cv;
    SwipeRefreshLayout swipe_refresh_layout;
    ProgressBar progress_bar;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_subjects_lecture;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        back_iv = findViewById(R.id.back_iv);
        desc_wv = findViewById(R.id.desc_wv);
        title_tv = findViewById(R.id.title_tv);
        you_tube_pv = findViewById(R.id.you_tube_pv);
        vimeo_player = findViewById(R.id.vimeo_player);
        youtube_cv = findViewById(R.id.youtube_cv);
        vimeo_cv = findViewById(R.id.vimeo_cv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        progress_bar = findViewById(R.id.progress_bar);
        //math_view = findViewById(R.id.math_view);

        if (getIntent().getExtras() != null) {
            topic_id = getIntent().getExtras().getString(KEY_TOPIC_ID);
            title = getIntent().getExtras().getString(KEY_TITLE);
        }
        title_tv.setText(title);


        callTopicVideosApi();
        back_iv.setOnClickListener(this);
        swipe_refresh_layout.setOnRefreshListener(this);
    }

    private void setDescription(String html_string) {
        if (html_string != null || html_string.length() > 0) {
            //desc_wv.loadData(html_string, "text/html", "utf-8");
            desc_wv.setText(html_string);
            desc_wv.getSettings().setJavaScriptEnabled(true);
            desc_wv.getSettings().setDomStorageEnabled(true);
            desc_wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
            desc_wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
    }


    @Override
    public void onRefresh() {
        callTopicVideosApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }

    private void initializeYouTube(String videoID) {
        you_tube_pv.initialize(getString(R.string.you_tube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (videoID != null && !videoID.equals("") && videoID.length() > 6) {
                    youTubePlayer.loadVideo(videoID);
//                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                    youTubePlayer.setShowFullscreenButton(true);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                if (youTubeInitializationResult.isUserRecoverableError()) {
                    Log.d(getClass().getSimpleName(), youTubeInitializationResult.toString());
                }
            }
        });
    }

    private void callTopicVideosApi() {
        if (topic_id != null && topic_id.length() > 0) {
            JsonObject authParameter = new JsonObject();
            JsonObject subParameters = new JsonObject();

            String userId = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
            String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

            if (userId != null && token != null) {
                authParameter.addProperty(KEY_USER_ID, userId);
                authParameter.addProperty(KEY_TOPIC_ID, topic_id);
                subParameters.addProperty(KEY_ID, userId);
                subParameters.addProperty(KEY_TOKEN, token);
                authParameter.add(KEY_AUTH, subParameters);
                if (authParameter == null) return;
                updateViewVisibility(progress_bar,View.VISIBLE);
                makeWebRequest(this).getTopicVideoList(authParameter, this);
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() >= 400) return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_TOPIC_VIDEOS_CODE) {
                handleVideosData(response);
            }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Log.d(getClass().getSimpleName(),throwable.getCause().toString());
        swipe_refresh_layout.setRefreshing(false);
        showSimpleToast(throwable.getMessage());
    }

    private void handleVideosData(Response response) {
        Object object = response.body();
        TopicVideosModel topicsData = (TopicVideosModel) object;
        if (topicsData != null && topicsData.getCode() == 200) {
            updateViewVisibility(progress_bar,View.GONE);
            if (swipe_refresh_layout.isRefreshing()) {
                swipe_refresh_layout.setRefreshing(false);
            }

            TopicVideosModel.VideoData videoData = topicsData.getMessage().getVideodet();
            if (videoData == null) return;
            Log.d(getClass().getSimpleName(), videoData.toString());
            if (videoData.getCv_vedio_provider().equals("vimeo")) {
                vimeo_cv.setVisibility(View.VISIBLE);
                youtube_cv.setVisibility(View.GONE);
                if (videoData.getCv_location() != null && !videoData.getCv_location().equals("")) {
                    String[] video_id = videoData.getCv_location().split("\\/", 5);
                    if (video_id[4] == null || video_id[4].equals("") || video_id[4].length() <= 0)
                        return;
                    setVimeoPlayer(getBaseContext(), vimeo_player, Integer.parseInt(video_id[4]));
                }
            } else if (videoData.getCv_vedio_provider().equals("youtube")) {
                vimeo_cv.setVisibility(View.GONE);
                youtube_cv.setVisibility(View.VISIBLE);
                if (videoData.getCv_location() != null && !videoData.getCv_location().equals("")) {
                    String[] video_id = videoData.getCv_location().split("=");
                    if (video_id[1] == null || video_id[1].equals("") || video_id[1].length() <= 0)
                        return;
                    initializeYouTube(video_id[1]);
                }
            }

            String actual_string = videoData.getCv_description();
            if (actual_string.contains("$")){
                String updated_string = actual_string.replace("$", "$$");
                //String final_string = updated_string.replace(updated_string,"\\(" + updated_string + "\\)");
                setDescription(updated_string);
            } else {
                setDescription(actual_string);
            }

        } else {
            return;
        }
    }
}