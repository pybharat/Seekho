package com.seekho.live.Models.Quiz.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizOptions {

    @SerializedName("1")
    @Expose
    String one;

    @SerializedName("2")
    @Expose
    String two;

    @SerializedName("3")
    @Expose
    String three;

    @SerializedName("4")
    @Expose
    String four;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    @Override
    public String toString() {
        return "QuizOptions{" +
                "one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                '}';
    }
}
