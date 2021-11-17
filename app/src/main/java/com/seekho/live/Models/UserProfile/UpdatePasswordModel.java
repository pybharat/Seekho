package com.seekho.live.Models.UserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatePasswordModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UpdatePasswordModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
