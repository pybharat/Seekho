package com.seekho.live.Models.Courses.ChaptersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.Courses.SubCourses.SubCategoryData;

import java.util.List;

public class ChaptersModel {

    boolean isExpanded;

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    Message message;

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

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

        @SerializedName("subcategorydet")
        @Expose
        SubCategoryData subcategorydet;

        @SerializedName("chapterlist")
        @Expose
        List<ChaptersDataModel> chapterlist;

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

        public List<ChaptersDataModel> getChapterlist() {
            return chapterlist;
        }

        public void setChapterlist(List<ChaptersDataModel> chapterlist) {
            this.chapterlist = chapterlist;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "categorydet=" + categorydet +
                    ", subcategorydet=" + subcategorydet +
                    ", chapterlist=" + chapterlist +
                    '}';
        }
    }
}
