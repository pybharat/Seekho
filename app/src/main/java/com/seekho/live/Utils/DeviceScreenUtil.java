package com.seekho.live.Utils;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

public class DeviceScreenUtil {
    private static DeviceScreenUtil deviceScreenUtil;
    Rect deviceRect;
    DisplayMetrics displayMetrics;

    public DeviceScreenUtil(DisplayMetrics displayMetrics) {
        this.displayMetrics = displayMetrics;
        this.deviceRect = new Rect(0,0,displayMetrics.widthPixels,displayMetrics.heightPixels);
    }

    public static DeviceScreenUtil getInstance(){
        if (deviceScreenUtil == null){
            deviceScreenUtil = new DeviceScreenUtil(Resources.getSystem().getDisplayMetrics());
        }
        return deviceScreenUtil;
    }

    public int getWidth(){
        return deviceRect.width();
    }

    public int getWidth(float percent){
        return Math.round(deviceRect.width() * percent);
    }
}
