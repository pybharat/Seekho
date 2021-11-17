package com.seekho.live.Models.Courses.ChaptersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicsListModel {

    @SerializedName("ct_id")
    @Expose
    String ct_id;

    @SerializedName("ct_slug")
    @Expose
    String ct_slug;

    @SerializedName("ct_name")
    @Expose
    String ct_name;

    @SerializedName("cc_ch_id")
    @Expose
    String cc_ch_id;

    @SerializedName("ct_type")
    @Expose
    String ct_type;

    @SerializedName("content_id")
    @Expose
    String content_id;

    @SerializedName("ct_status")
    @Expose
    String ct_status;

    @SerializedName("position")
    @Expose
    String position;

    @SerializedName("ct_created_at")
    @Expose
    String ct_created_at;

    @SerializedName("ct_updated_at")
    @Expose
    String ct_updated_at;

    @SerializedName("videoduration")
    @Expose
    String videoduration;

    @SerializedName("quizquestions")
    @Expose
    String quizquestions;

    public String getCt_id() {
        return ct_id;
    }

    public void setCt_id(String ct_id) {
        this.ct_id = ct_id;
    }

    public String getCt_slug() {
        return ct_slug;
    }

    public void setCt_slug(String ct_slug) {
        this.ct_slug = ct_slug;
    }

    public String getCt_name() {
        return ct_name;
    }

    public void setCt_name(String ct_name) {
        this.ct_name = ct_name;
    }

    public String getCc_ch_id() {
        return cc_ch_id;
    }

    public void setCc_ch_id(String cc_ch_id) {
        this.cc_ch_id = cc_ch_id;
    }

    public String getCt_type() {
        return ct_type;
    }

    public void setCt_type(String ct_type) {
        this.ct_type = ct_type;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getCt_status() {
        return ct_status;
    }

    public void setCt_status(String ct_status) {
        this.ct_status = ct_status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCt_created_at() {
        return ct_created_at;
    }

    public void setCt_created_at(String ct_created_at) {
        this.ct_created_at = ct_created_at;
    }

    public String getCt_updated_at() {
        return ct_updated_at;
    }

    public void setCt_updated_at(String ct_updated_at) {
        this.ct_updated_at = ct_updated_at;
    }

    public String getVideoduration() {
        return videoduration;
    }

    public void setVideoduration(String videoduration) {
        this.videoduration = videoduration;
    }

    public String getQuizquestions() {
        return quizquestions;
    }

    public void setQuizquestions(String quizquestions) {
        this.quizquestions = quizquestions;
    }

    @Override
    public String toString() {
        return "TopicsListModel{" +
                "ct_id='" + ct_id + '\'' +
                ", ct_slug='" + ct_slug + '\'' +
                ", ct_name='" + ct_name + '\'' +
                ", cc_ch_id='" + cc_ch_id + '\'' +
                ", ct_type='" + ct_type + '\'' +
                ", content_id='" + content_id + '\'' +
                ", ct_status='" + ct_status + '\'' +
                ", position='" + position + '\'' +
                ", ct_created_at='" + ct_created_at + '\'' +
                ", ct_updated_at='" + ct_updated_at + '\'' +
                ", videoduration='" + videoduration + '\'' +
                ", quizquestions='" + quizquestions + '\'' +
                '}';
    }
}
