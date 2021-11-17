package com.seekho.live.Models.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordModel {

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
        return "ResetPasswordModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }
}
