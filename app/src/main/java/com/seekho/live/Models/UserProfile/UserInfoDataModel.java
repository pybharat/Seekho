package com.seekho.live.Models.UserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfoDataModel {

    @SerializedName("user_id")
    @Expose
    String user_id;

    @SerializedName("user_firstname")
    @Expose
    String user_firstname;

    @SerializedName("user_lastname")
    @Expose
    String user_lastname;

    @SerializedName("user_email")
    @Expose
    String user_email;

    @SerializedName("user_mobile")
    @Expose
    String user_mobile;

    @SerializedName("user_password")
    @Expose
    String user_password;

    @SerializedName("profile_image")
    @Expose
    String profile_image;

    @SerializedName("otp")
    @Expose
    String otp;

    @SerializedName("verify")
    @Expose
    String verify;

    @SerializedName("user_created_at")
    @Expose
    String user_created_at;

    @SerializedName("user_updated_at")
    @Expose
    String user_updated_at;

    @SerializedName("user_status")
    @Expose
    String user_status;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getUser_created_at() {
        return user_created_at;
    }

    public void setUser_created_at(String user_created_at) {
        this.user_created_at = user_created_at;
    }

    public String getUser_updated_at() {
        return user_updated_at;
    }

    public void setUser_updated_at(String user_updated_at) {
        this.user_updated_at = user_updated_at;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "UserInfoDataModel{" +
                "user_id='" + user_id + '\'' +
                ", user_firstname='" + user_firstname + '\'' +
                ", user_lastname='" + user_lastname + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_mobile='" + user_mobile + '\'' +
                ", user_password='" + user_password + '\'' +
                ", profile_image='" + profile_image + '\'' +
                ", otp='" + otp + '\'' +
                ", verify='" + verify + '\'' +
                ", user_created_at='" + user_created_at + '\'' +
                ", user_updated_at='" + user_updated_at + '\'' +
                ", user_status='" + user_status + '\'' +
                '}';
    }
}
