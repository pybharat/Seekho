package com.seekho.live.Models.Auth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("userid")
    @Expose
    String userid;

    @SerializedName("verify")
    @Expose
    String verify;

    @SerializedName("Token")
    @Expose
    String Token;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    @Override
    public String toString() {
        return "LoginDataModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", userid='" + userid + '\'' +
                ", verify='" + verify + '\'' +
                ", Token='" + Token + '\'' +
                '}';
    }
}
