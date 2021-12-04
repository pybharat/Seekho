package com.seekho.live.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.seekho.live.R;
import com.seekho.live.VimeoPlayer.VimeoHelper;

import io.github.kexanie.library.MathView;

public class VimeoActivity extends AppCompatActivity {
TextView title_tv;
MathView desc_wv;
ImageView back_iv;
SwipeRefreshLayout swipe_refresh_layout;
ProgressBar progress_bar;
PlayerView playerView;
ImageView fullscreenButton;
SimpleExoPlayer player;
String videoid,topic_id,title,des;
    VimeoHelper vimeoHelper;
    private static final String VIMEO_ACCESS_TOKEN = "12db61deb1266bcf8dcb0c52631fd9d0";
    private boolean playWhenReady = false; //If true the player auto play the media
    private int currentWindow = 0;
    private long playbackPosition = 0;
    boolean fullscreen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vimeo);
       // back_iv = findViewById(R.id.back_iv);
        desc_wv = findViewById(R.id.desc_wv);
      //  title_tv = findViewById(R.id.title_tv);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);
        progress_bar = findViewById(R.id.progress_bar);
        playerView = findViewById(R.id.exo_pl);
        fullscreenButton = playerView.findViewById(R.id.exo_fullscreen_icon);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
           videoid=bundle.getString("videoid");
           topic_id=bundle.getString("topic_id");
           title=bundle.getString("title");
           des=bundle.getString("des");
        }
      //  title_tv.setText(title);
        vimeoHelper=new VimeoHelper(VimeoActivity.this,videoid);
        //Build vimeo configuration
        vimeoHelper.configVimeoClient(VIMEO_ACCESS_TOKEN);
        vimeoHelper.initializePlayer(player,playerView);
        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(VimeoActivity.this, R.drawable.ic_fullscreen_open));

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().show();
                    }

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();

                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);

                    fullscreen = false;

                } else {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(VimeoActivity.this, R.drawable.ic_fullscreen_close));

                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().hide();
                    }
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
    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }




    /**
     * Android API level 24 and higher supports multiple windows.
     * As your app can be visible, but not active in split window mode, you need to initialize the player in onStart.
     * Android API level 24 and lower requires you to wait as long as possible until you grab resources,
     * so you wait until onResume before initializing the player.
     */


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            //Frees the player's resources and destroys it.
            vimeoHelper.releasePlayer(player);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            //Frees the player's resources and destroys it.
            vimeoHelper.releasePlayer(player);
        }
    }




}