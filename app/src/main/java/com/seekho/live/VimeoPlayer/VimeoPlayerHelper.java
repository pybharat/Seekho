package com.seekho.live.VimeoPlayer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;

import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;

public class VimeoPlayerHelper implements LifecycleObserver{

    Context context;
    VimeoPlayerView vimeoPlayerView;

    public VimeoPlayerHelper(Context context, VimeoPlayerView vimeoPlayerView,int videoID) {
        this.context = context;
        this.vimeoPlayerView = vimeoPlayerView;

        initializeVimeoPlayer(videoID);
    }

    public VimeoPlayerHelper(Context context) {
        this.context = context;
    }

    private void initializeVimeoPlayer(int videoID) {
        if (videoID > 0 && context != null){
            lifecycle.addObserver(this);
            vimeoPlayerView.initialize(videoID);
            vimeoPlayerView.setFullscreenVisibility(true);
        }
    }

    Lifecycle lifecycle = new Lifecycle() {
        @Override
        public void addObserver(@NonNull LifecycleObserver observer) {

        }

        @Override
        public void removeObserver(@NonNull LifecycleObserver observer) {

        }

        @NonNull
        @Override
        public State getCurrentState() {
            return null;
        }
    };

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public VimeoPlayerView getVimeoPlayerView() {
        return vimeoPlayerView;
    }

    public void setVimeoPlayerView(VimeoPlayerView vimeoPlayerView) {
        this.vimeoPlayerView = vimeoPlayerView;
    }
}
