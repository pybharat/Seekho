package com.seekho.live.Models.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterDataModel {
    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("otp")
    @Expose
    String otp;

    @SerializedName("userid")
    @Expose
    String userid;

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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "RegisterDataModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", otp='" + otp + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
