package com.seekho.live.AppBase;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.seekho.live.Base.BaseYouTubeActivity;
import com.seekho.live.BaseToolsFragment.ToolbarFragment;
import com.seekho.live.Interfaces.Constants;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebListener;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public abstract class YouTubeAppBaseActivity extends BaseYouTubeActivity implements View.OnClickListener,
        YouTubePlayer.OnInitializedListener, ToolbarFragment.OnToolbarClickListener, WebListener, Constants, WebContants,
        SwipeRefreshLayout.OnRefreshListener {

    @Override
    public void initializeComponents() {
        setStatusBarColor(R.color.navi_blue);
    }

    public void setStatusBarColor(int colorCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(colorCode));
        }
    }

    public void updateViewVisibility(View view, int visibility) {
        if (view == null) return;
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

    @Override
    public void onRefresh() {

    }



    @Override
    public void onClick(View view) {

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onToolbarClick(View view) {

    }

    //------------------------------------ Web Listeners ------------------------------------------

    public WebRequest makeWebRequest(Context context) {
        WebRequest webRequest = new WebRequest(context);
        if (webRequest != null) {
            return webRequest;
        } else {
            return null;
        }
    }

    //------------------------------------- Creating Toast -----------------------------------------
    public void showSimpleToast(String msg) {
        if (msg != null && !msg.isEmpty()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {

    }

    @Override
    public void onWebFailure(Throwable throwable) {

    }
}
