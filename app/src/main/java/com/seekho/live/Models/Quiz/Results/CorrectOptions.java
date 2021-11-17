package com.seekho.live.Models.Quiz.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CorrectOptions {

    @SerializedName("option")
    @Expose
    String option;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "CorrectOptions{" +
                "option='" + option + '\'' +
                '}';
    }
}
