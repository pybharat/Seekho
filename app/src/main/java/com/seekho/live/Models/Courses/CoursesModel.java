package com.seekho.live.Models.Courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoursesModel {

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

    public class Message {
        @SerializedName("allcourses")
        @Expose
        List<AllCoursesData> allcourses;

        public List<AllCoursesData> getAllcourses() {
            return allcourses;
        }

        public void setAllcourses(List<AllCoursesData> allcourses) {
            this.allcourses = allcourses;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "allcours=" + allcourses +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CoursesModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }
}
