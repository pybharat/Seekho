package com.seekho.live.Models.Courses.ChaptersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChaptersDataModel {

    @SerializedName("cc_id")
    @Expose
    String cc_id;

    @SerializedName("cc_slug")
    @Expose
    String cc_slug;

    @SerializedName("cc_name")
    @Expose
    String cc_name;

    @SerializedName("cc_descriptions")
    @Expose
    String cc_descriptions;

    @SerializedName("cm_id")
    @Expose
    String cm_id;

    @SerializedName("cs_id")
    @Expose
    String cs_id;

    @SerializedName("cc_status")
    @Expose
    String cc_status;

    @SerializedName("position")
    @Expose
    String position;

    @SerializedName("cc_created_at")
    @Expose
    String cc_created_at;

    @SerializedName("cc_updated_at")
    @Expose
    String cc_updated_at;

    @SerializedName("enrollstatus")
    @Expose
    int enrollstatus;

    @SerializedName("topicslist")
    @Expose
    List<TopicsListModel> topicslist;

    public String getCc_id() {
        return cc_id;
    }

    public void setCc_id(String cc_id) {
        this.cc_id = cc_id;
    }

    public String getCc_slug() {
        return cc_slug;
    }

    public void setCc_slug(String cc_slug) {
        this.cc_slug = cc_slug;
    }

    public String getCc_name() {
        return cc_name;
    }

    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }

    public String getCc_descriptions() {
        return cc_descriptions;
    }

    public void setCc_descriptions(String cc_descriptions) {
        this.cc_descriptions = cc_descriptions;
    }

    public String getCm_id() {
        return cm_id;
    }

    public void setCm_id(String cm_id) {
        this.cm_id = cm_id;
    }

    public String getCs_id() {
        return cs_id;
    }

    public void setCs_id(String cs_id) {
        this.cs_id = cs_id;
    }

    public String getCc_status() {
        return cc_status;
    }

    public void setCc_status(String cc_status) {
        this.cc_status = cc_status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCc_created_at() {
        return cc_created_at;
    }

    public void setCc_created_at(String cc_created_at) {
        this.cc_created_at = cc_created_at;
    }

    public String getCc_updated_at() {
        return cc_updated_at;
    }

    public void setCc_updated_at(String cc_updated_at) {
        this.cc_updated_at = cc_updated_at;
    }

    public int getEnrollstatus() {
        return enrollstatus;
    }

    public void setEnrollstatus(int enrollstatus) {
        this.enrollstatus = enrollstatus;
    }

    public List<TopicsListModel> getTopicslist() {
        return topicslist;
    }

    public void setTopicslist(List<TopicsListModel> topicslist) {
        this.topicslist = topicslist;
    }

    @Override
    public String toString() {
        return "ChaptersDataModel{" +
                "cc_id='" + cc_id + '\'' +
                ", cc_slug='" + cc_slug + '\'' +
                ", cc_name='" + cc_name + '\'' +
                ", cc_descriptions='" + cc_descriptions + '\'' +
                ", cm_id='" + cm_id + '\'' +
                ", cs_id='" + cs_id + '\'' +
                ", cc_status='" + cc_status + '\'' +
                ", position='" + position + '\'' +
                ", cc_created_at='" + cc_created_at + '\'' +
                ", cc_updated_at='" + cc_updated_at + '\'' +
                ", topicslist=" + topicslist +
                '}';
    }
}
