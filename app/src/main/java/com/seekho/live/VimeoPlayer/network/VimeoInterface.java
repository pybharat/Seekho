package com.seekho.live.VimeoPlayer.network;



import com.seekho.live.VimeoPlayer.vimeo.VimeoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VimeoInterface {
    @GET("{video_id}/config")
    Call<VimeoResponse> getVimeoUrlResponse(@Path("video_id") String id);

}
