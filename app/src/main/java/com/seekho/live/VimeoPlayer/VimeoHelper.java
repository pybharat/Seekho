package com.seekho.live.VimeoPlayer;

import android.content.Context;
import android.util.Log;


import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.seekho.live.VimeoPlayer.network.VimeoClientAPI;
import com.seekho.live.VimeoPlayer.network.VimeoInterface;
import com.seekho.live.VimeoPlayer.vimeo.VimeoResponse;
import com.vimeo.networking.Configuration;
import com.vimeo.networking.VimeoClient;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

public class VimeoHelper {
    Context ct;
    String video_id;
    private boolean playWhenReady; //If true the player auto play the media
    private int currentWindow;
    private long playbackPosition;


    public VimeoHelper(Context ct, String video_id) {
     this.ct=ct;
     this.video_id=video_id;
        this.playWhenReady = false; //If true the player auto play the media
        this.currentWindow = 0;
        this.playbackPosition = 0;

    }
    public void configVimeoClient(String VIMEO_ACCESS_TOKEN) {
        Configuration.Builder configBuilder =
                new Configuration.Builder(VIMEO_ACCESS_TOKEN) //Pass app access token
                        .setCacheDirectory(ct.getCacheDir());
        VimeoClient.initialize(configBuilder.build());
    }
    private void createMediaItem(String url, SimpleExoPlayer player) {
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
    }
    public void initializePlayer(SimpleExoPlayer player, PlayerView playerView) {
        //To play streaming media, you need an ExoPlayer object.
        //SimpleExoPlayer is a convenient, all-purpose implementation of the ExoPlayer interface.
        player = new SimpleExoPlayer.Builder(ct).build();
        playerView.setPlayer(player);
        callVimeoAPIRequest(player);
        //Supply the state information you saved in releasePlayer to your player during initialization.
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();
    }
    public void releasePlayer(SimpleExoPlayer player) {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }
    public void callVimeoAPIRequest(SimpleExoPlayer player) {
        VimeoInterface vimeoInterface = VimeoClientAPI.getClient().create(VimeoInterface.class);
        vimeoInterface.getVimeoUrlResponse(video_id)
                .enqueue(new retrofit2.Callback<VimeoResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<VimeoResponse> call, @NotNull Response<VimeoResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            //Create media item
                            if (response.body().getRequest().getFiles().getProgressive().size() > 0)
                                createMediaItem(response.body().getRequest().getFiles().getProgressive().get(0).getUrl(),player);

                            Log.d("TAG", response.body().getRequest().getFiles().getProgressive().get(0).getUrl());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<VimeoResponse> call, @NotNull Throwable t) {
                        Log.e("TAG", Objects.requireNonNull(t.getMessage()));

                    }
                });
    }
}
