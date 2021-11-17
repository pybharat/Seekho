package com.seekho.live.Models.MockTests.MockQuiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MockQuizesListModel {

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
        return "MockQuizesListModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message{
        @SerializedName("test_series")
        @Expose
        List<MockQuizListDataModel> test_series;

        public List<MockQuizListDataModel> getTest_series() {
            return test_series;
        }

        public void setTest_series(List<MockQuizListDataModel> test_series) {
            this.test_series = test_series;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "test_series=" + test_series +
                    '}';
        }
    }
}
