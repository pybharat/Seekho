package com.seekho.live.Models.Courses.SubCourses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.seekho.live.Models.Courses.AllCoursesData;

import java.util.List;

public class SubCourseDivisionModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    Message message;

    @SerializedName("status")
    @Expose
    boolean status;

    @SerializedName("error")
    @Expose
    boolean error;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "SubCourseDivisionModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message {
        @SerializedName("categorydet")
        @Expose
        AllCoursesData categorydet;

        @SerializedName("subcategorydet")
        @Expose
        SubCategoryData subcategorydet;

        @SerializedName("subdivison")
        @Expose
        List<SubCategoryData> subdivison;

        public AllCoursesData getCategorydet() {
            return categorydet;
        }

        public void setCategorydet(AllCoursesData categorydet) {
            this.categorydet = categorydet;
        }

        public SubCategoryData getSubcategorydet() {
            return subcategorydet;
        }

        public void setSubcategorydet(SubCategoryData subcategorydet) {
            this.subcategorydet = subcategorydet;
        }

        public List<SubCategoryData> getSubdivison() {
            return subdivison;
        }

        public void setSubdivison(List<SubCategoryData> subdivison) {
            this.subdivison = subdivison;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "categorydet=" + categorydet +
                    ", subcategorydet=" + subcategorydet +
                    ", subdivison=" + subdivison +
                    '}';
        }
    }
}
