package com.seekho.live.WebBase;

import android.content.Context;
import android.util.Log;

import com.seekho.live.Utils.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class WebBase implements WebContants {
    OkHttpClient.Builder httpClient;
    Retrofit retrofit;

//    Context context;
//
//    public WebBase(Context context) {
//        this.context = context;
//    }

    public OkHttpClient.Builder getHttpClient() {
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader(HEADER_KEY_LANG, HEADER_LANG_VALUE)
                        .addHeader(HEADER_KEY_DEVICE_ID, "device-id")
                        .addHeader(HEADER_KEY_DEVICETYPE, HEADER_DEVICETYPE_VALUE)
                        .addHeader(HEADER_KEY_DEVICEINFO, "device-info")
                        .addHeader(HEADER_KEY_APPINFO, "app-info")
                        .addHeader(HEADER_KEY_AUTHORIZATION, HEADER_KEY_AUTHORIZATION)
                        .addHeader(HEADER_KEY_CONTENT_TYPE, "application/json")
                        //.addHeader(HEADER_KEY_TOKEN,HEADER_KEY_AUTHORIZATION)
                        .build();
                return chain.proceed(request);
            }
        });
        httpClient.connectTimeout(60000, TimeUnit.SECONDS);
        httpClient.readTimeout(60000, TimeUnit.SECONDS);
        httpClient.retryOnConnectionFailure(true);
        return httpClient;
    }

    public Retrofit getRetrofit() {
        if (getHttpClient() != null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient().build())
                    .build();
        }
        return retrofit;
    }

    public WebRequestInterface getWebRequestInterface() {
        return getRetrofit().create(WebRequestInterface.class);
    }

    public <T> void callApis(Context context, Call<T> responseCall, WebRequest webRequest, WebListener webListener) {
        if (responseCall == null) return;

        responseCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                if (response == null && response.body() == null && response.code() > 200 && response.code() >= 400) return;
                if (response.code() == 200)
                    Log.d("Response: ", response.body().toString());
                webListener.onWebRequestSuccess(webRequest, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.d("Error: ", t.toString());
                webListener.onWebFailure(t);
            }
        });
    }

    Object responsePojo;

    public Object getResponsePojo() {
        return responsePojo;
    }

    public void setResponsePojo(Object responsePojo) {
        this.responsePojo = responsePojo;
    }
}
