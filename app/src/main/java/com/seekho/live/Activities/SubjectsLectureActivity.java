package com.seekho.live.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.JsonObject;
import com.seekho.live.AppBase.YouTubeAppBaseActivity;
import com.seekho.live.Models.Courses.ChaptersModel.TopicVideosModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.VimeoPlayer.VimeoHelper;
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


    private static final String VIMEO_ACCESS_TOKEN = "12db61deb1266bcf8dcb0c52631fd9d0";
    private PlayerView playerView;
SimpleExoPlayer player;
    private boolean playWhenReady = false; //If true the player auto play the media
    private int currentWindow = 0;
    private long playbackPosition = 0;
    boolean fullscreen = false;
    ImageView fullscreenButton;
    //Release references




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
        playerView = findViewById(R.id.exo_pl);
        fullscreenButton = playerView.findViewById(R.id.exo_fullscreen_icon);
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
        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(SubjectsLectureActivity.this, R.drawable.ic_fullscreen_open));

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);



                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();

                    params.width = params.MATCH_PARENT;
                    params.height = (int) (250 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);
                    fullscreen = false;

                } else {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(SubjectsLectureActivity.this, R.drawable.ic_fullscreen_close));

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();

                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);
                    fullscreen = true;

                }

            }
        });
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
                vimeo_cv.setVisibility(View.GONE);
                youtube_cv.setVisibility(View.GONE);
                playerView.setVisibility(View.VISIBLE);
                if (videoData.getCv_location() != null && !videoData.getCv_location().equals("")) {
                    String[] video_id = videoData.getCv_location().split("\\/", 5);
                    if (video_id[4] == null || video_id[4].equals("") || video_id[4].length() <= 0)
                        return;
                   else {
                        String actual_string = videoData.getCv_description();

                /*   Intent i=new Intent(SubjectsLectureActivity.this,VimeoActivity.class);
                   i.putExtra("videoid",video_id[4]);
                   i.putExtra("topic_id",topic_id);
                   i.putExtra("title",title);
                   i.putExtra("des",actual_string);
                   startActivity(i);
                       */
                    //Build vimeo configuration
                        VimeoHelper vimeoHelper=new VimeoHelper(SubjectsLectureActivity.this,video_id[4]);
                        //Build vimeo configuration
                        vimeoHelper.configVimeoClient(VIMEO_ACCESS_TOKEN);
                        vimeoHelper.initializePlayer(player,playerView);
                 // setVimeoPlayer(getBaseContext(), vimeo_player, Integer.parseInt(video_id[4]));


                    }
                }
            } else if (videoData.getCv_vedio_provider().equals("youtube")) {
                vimeo_cv.setVisibility(View.GONE);
                youtube_cv.setVisibility(View.VISIBLE);
                playerView.setVisibility(View.GONE);
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
    @SuppressLint("InlinedApi")
    private void hideSystemUi(){
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            //Frees the player's resources and destroys it.
            releasePlayer(player);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            //Frees the player's resources and destroys it.
            releasePlayer(player);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        releasePlayer(player);
    }

    private void releasePlayer(SimpleExoPlayer player) {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

}
