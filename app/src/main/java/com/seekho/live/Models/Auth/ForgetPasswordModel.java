package com.seekho.live.Models.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPasswordModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("otp")
    @Expose
    String otp;

    @SerializedName("email")
    @Expose
    String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ForgetPasswordModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", otp='" + otp + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
