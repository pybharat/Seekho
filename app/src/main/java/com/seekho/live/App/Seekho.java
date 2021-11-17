package com.seekho.live.App;

import android.app.Application;
import android.content.Context;

public class Seekho extends Application {

    static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getAppContext(){
        return mContext;
    }

    public static void setAppContext(Context context) {
        Seekho.mContext = context;
    }
}
