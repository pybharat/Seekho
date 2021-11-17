package com.seekho.live.Models.MockTests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockTestCategoriesModel {

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

    @Override
    public String toString() {
        return "MockTestCategoriesModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message{
        @SerializedName("category")
        @Expose
        List<MockTestCategoryDataModel> category;

        public List<MockTestCategoryDataModel> getCategory() {
            return category;
        }

        public void setCategory(List<MockTestCategoryDataModel> category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "category=" + category +
                    '}';
        }
    }
}
