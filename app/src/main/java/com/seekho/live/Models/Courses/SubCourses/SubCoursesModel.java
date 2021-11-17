package com.seekho.live.Models.Courses.SubCourses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.seekho.live.Models.Courses.AllCoursesData;

import java.util.List;

public class SubCoursesModel {

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

    public class Message{

        @SerializedName("categorydet")
        @Expose
        AllCoursesData categorydet;

        @SerializedName("subcategory")
        @Expose
        List<SubCategoryData> subcategory;

        public AllCoursesData getCategorydet() {
            return categorydet;
        }

        public void setCategorydet(AllCoursesData categorydet) {
            this.categorydet = categorydet;
        }

        public List<SubCategoryData> getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(List<SubCategoryData> subcategory) {
            this.subcategory = subcategory;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "categorydet=" + categorydet +
                    ", subcategory=" + subcategory +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SubCoursesModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }
}
