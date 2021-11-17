package com.seekho.live.Models.Enroll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnrollDataModel {

    @SerializedName("code")
    @Expose
    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
