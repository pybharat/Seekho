package com.seekho.live.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.seekho.live.Interfaces.Constants;
import com.seekho.live.Interfaces.UserListener;
import com.seekho.live.Models.UserProfile.UserInfoDataModel;
import com.seekho.live.WebBase.WebContants;

public class Pref implements Constants, WebContants {

    Context context;

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    public Pref(Context context) {
        this.context = context;
    }

    public Pref() {
    }

    public static SharedPreferences.Editor getPrefEditor(Context context, String prefName, String key, String value) {
        if (context != null) {
            sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
        return editor;
    }

        public static SharedPreferences.Editor clearPref(Context context, String prefName) {
            if (context != null && prefName != null) {
                sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
            }
            return editor;
        }

    public static String getAllDataFromPref(Context context, String prefName) {
        if (context != null && prefName != null) {
            sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getAll().toString();
    }

    public static SharedPreferences getSpecificPref(Context context, String prefName) {
        if (context != null && prefName != null) {
            sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public static String getValueFromPref(Context context, String prefName, String key) {
        if (context != null && prefName != null && key != null) {
            sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, "");
    }

    public static void setUserDataPref(Context context, UserInfoDataModel userData) {
        if (userData == null) return;
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_USER_ID, userData.getUser_id());
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_FIRST_NAME, userData.getUser_firstname());
        if (userData.getUser_lastname() != null && !userData.getUser_lastname().equals("")) {
            Pref.getPrefEditor(context, USER_INFO_PREF, KEY_LAST_NAME, userData.getUser_lastname());
        }
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_EMAIL, userData.getUser_email());
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_NUMBER, userData.getUser_mobile());
        if (userData.getProfile_image() != null && !userData.getProfile_image().equals("")) {
            Pref.getPrefEditor(context, USER_INFO_PREF, KEY_PROFILE_IMAGE, userData.getProfile_image());
        }
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_OTP, userData.getOtp());
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_VERIFY, userData.getVerify());
        Pref.getPrefEditor(context, USER_INFO_PREF, KEY_USER_STATUS, userData.getUser_status());
    }

    public static void getUserData(Context context,UserListener userListener){
        UserInfoDataModel userData = new UserInfoDataModel();
        if (Pref.getSpecificPref(context,USER_INFO_PREF) != null){
            userData.setUser_id(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_USER_ID));
            userData.setUser_firstname(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_FIRST_NAME));
            if (Pref.getValueFromPref(context, USER_INFO_PREF, KEY_LAST_NAME) != null &&
                    !Pref.getValueFromPref(context, USER_INFO_PREF, KEY_LAST_NAME).equals("")){
                userData.setUser_lastname(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_LAST_NAME));
            }
            userData.setUser_email(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_EMAIL));
            userData.setUser_mobile(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_NUMBER));
            if (Pref.getValueFromPref(context, USER_INFO_PREF, KEY_PROFILE_IMAGE) != null &&
                    !Pref.getValueFromPref(context, USER_INFO_PREF, KEY_PROFILE_IMAGE).equals("")){
                userData.setProfile_image(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_PROFILE_IMAGE));
            }
            userData.setOtp(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_OTP));
            userData.setVerify(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_VERIFY));
            userData.setUser_status(Pref.getValueFromPref(context, USER_INFO_PREF, KEY_USER_STATUS));
        }

        if (userData != null){
            userListener.onUserDataChanged(userData);
        }
    }
}
