package com.seekho.live.WebBase;

import retrofit2.Response;

public interface WebListener {
    void onWebRequestSuccess(WebRequest webRequest,Response response);
    void onWebFailure(Throwable throwable);
}
