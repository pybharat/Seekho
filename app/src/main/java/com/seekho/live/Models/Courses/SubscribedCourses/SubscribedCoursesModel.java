package com.seekho.live.Models.Courses.SubscribedCourses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.seekho.live.Models.Courses.AllCoursesData;

import java.util.List;

public class SubscribedCoursesModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    Message message;

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
        return "SubscribedCoursesModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message {
        @SerializedName("subscribedcourses")
        @Expose
        List<AllCoursesData> subscribedcourses;

        public List<AllCoursesData> getSubscribedcourses() {
            return subscribedcourses;
        }

        public void setSubscribedcourses(List<AllCoursesData> subscribedcourses) {
            this.subscribedcourses = subscribedcourses;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "subscribedcourses=" + subscribedcourses +
                    '}';
        }
    }
}
