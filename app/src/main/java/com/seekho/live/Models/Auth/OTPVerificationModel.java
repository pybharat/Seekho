package com.seekho.live.Models.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPVerificationModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("otp")
    @Expose
    double otp;

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

    public double getOtp() {
        return otp;
    }

    public void setOtp(double otp) {
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
        return "OTPVerificationModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
