package com.seekho.live.Models.Quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveAndNextQuizResultModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("notansweredquestions")
    @Expose
    int notansweredquestions;

    @SerializedName("answeredquestions")
    @Expose
    int answeredquestions;

    @SerializedName("markedasreviewquestions")
    @Expose
    int markedasreviewquestions;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNotansweredquestions() {
        return notansweredquestions;
    }

    public void setNotansweredquestions(int notansweredquestions) {
        this.notansweredquestions = notansweredquestions;
    }

    public int getAnsweredquestions() {
        return answeredquestions;
    }

    public void setAnsweredquestions(int answeredquestions) {
        this.answeredquestions = answeredquestions;
    }

    public int getMarkedasreviewquestions() {
        return markedasreviewquestions;
    }

    public void setMarkedasreviewquestions(int markedasreviewquestions) {
        this.markedasreviewquestions = markedasreviewquestions;
    }

    @Override
    public String toString() {
        return "SaveAndNextQuizResultModel{" +
                "code=" + code +
                ", notansweredquestions=" + notansweredquestions +
                ", answeredquestions=" + answeredquestions +
                ", markedasreviewquestions=" + markedasreviewquestions +
                '}';
    }
}
