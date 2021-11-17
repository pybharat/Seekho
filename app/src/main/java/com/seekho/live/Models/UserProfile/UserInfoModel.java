package com.seekho.live.Models.UserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("error")
    @Expose
    String error;

    @SerializedName("message")
    @Expose
    Message message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message{
        @SerializedName("userdet")
        @Expose
        UserInfoDataModel userdet;

        public UserInfoDataModel getUserdet() {
            return userdet;
        }

        public void setUserdet(UserInfoDataModel userdet) {
            this.userdet = userdet;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "userdet=" + userdet +
                    '}';
        }
    }
}
