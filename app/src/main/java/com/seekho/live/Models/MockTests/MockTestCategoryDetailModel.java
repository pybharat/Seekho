package com.seekho.live.Models.MockTests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MockTestCategoryDetailModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    Message message;

    @SerializedName("error")
    @Expose
    String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "MockTestCategoryDetailModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message{
        @SerializedName("categorydet")
        @Expose
        MockTestCategoryDataModel categorydet;

        public MockTestCategoryDataModel getCategorydet() {
            return categorydet;
        }

        public void setCategorydet(MockTestCategoryDataModel categorydet) {
            this.categorydet = categorydet;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "categorydet=" + categorydet +
                    '}';
        }
    }
}
