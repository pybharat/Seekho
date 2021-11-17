package com.seekho.live.Models.MockTests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MockTestCategoryDataModel {

    @SerializedName("mc_id")
    @Expose
    String mc_id;

    @SerializedName("mc_name")
    @Expose
    String mc_name;

    @SerializedName("mc_slug")
    @Expose
    String mc_slug;

    @SerializedName("mc_page_title")
    @Expose
    String mc_page_title;

    @SerializedName("mc_meta_description")
    @Expose
    String mc_meta_description;

    @SerializedName("mc_keywords")
    @Expose
    String mc_keywords;

    @SerializedName("mc_description")
    @Expose
    String mc_description;

    @SerializedName("mc_image")
    @Expose
    String mc_image;

    @SerializedName("mc_icon")
    @Expose
    String mc_icon;

    @SerializedName("mc_keystates")
    @Expose
    String mc_keystates;

    @SerializedName("marking_schema_id")
    @Expose
    String marking_schema_id;

    @SerializedName("mc_created_at")
    @Expose
    String mc_created_at;

    @SerializedName("mc_updated_at")
    @Expose
    String mc_updated_at;

    @SerializedName("mc_status")
    @Expose
    String mc_status;

    public String getMc_id() {
        return mc_id;
    }

    public void setMc_id(String mc_id) {
        this.mc_id = mc_id;
    }

    public String getMc_name() {
        return mc_name;
    }

    public void setMc_name(String mc_name) {
        this.mc_name = mc_name;
    }

    public String getMc_slug() {
        return mc_slug;
    }

    public void setMc_slug(String mc_slug) {
        this.mc_slug = mc_slug;
    }

    public String getMc_page_title() {
        return mc_page_title;
    }

    public void setMc_page_title(String mc_page_title) {
        this.mc_page_title = mc_page_title;
    }

    public String getMc_meta_description() {
        return mc_meta_description;
    }

    public void setMc_meta_description(String mc_meta_description) {
        this.mc_meta_description = mc_meta_description;
    }

    public String getMc_keywords() {
        return mc_keywords;
    }

    public void setMc_keywords(String mc_keywords) {
        this.mc_keywords = mc_keywords;
    }

    public String getMc_description() {
        return mc_description;
    }

    public void setMc_description(String mc_description) {
        this.mc_description = mc_description;
    }

    public String getMc_image() {
        return mc_image;
    }

    public void setMc_image(String mc_image) {
        this.mc_image = mc_image;
    }

    public String getMc_icon() {
        return mc_icon;
    }

    public void setMc_icon(String mc_icon) {
        this.mc_icon = mc_icon;
    }

    public String getMc_keystates() {
        return mc_keystates;
    }

    public void setMc_keystates(String mc_keystates) {
        this.mc_keystates = mc_keystates;
    }

    public String getMarking_schema_id() {
        return marking_schema_id;
    }

    public void setMarking_schema_id(String marking_schema_id) {
        this.marking_schema_id = marking_schema_id;
    }

    public String getMc_created_at() {
        return mc_created_at;
    }

    public void setMc_created_at(String mc_created_at) {
        this.mc_created_at = mc_created_at;
    }

    public String getMc_updated_at() {
        return mc_updated_at;
    }

    public void setMc_updated_at(String mc_updated_at) {
        this.mc_updated_at = mc_updated_at;
    }

    public String getMc_status() {
        return mc_status;
    }

    public void setMc_status(String mc_status) {
        this.mc_status = mc_status;
    }

    @Override
    public String toString() {
        return "MockTestCategoryDataModel{" +
                "mc_id='" + mc_id + '\'' +
                ", mc_name='" + mc_name + '\'' +
                ", mc_slug='" + mc_slug + '\'' +
                ", mc_page_title='" + mc_page_title + '\'' +
                ", mc_meta_description='" + mc_meta_description + '\'' +
                ", mc_keywords='" + mc_keywords + '\'' +
                ", mc_description='" + mc_description + '\'' +
                ", mc_image='" + mc_image + '\'' +
                ", mc_icon='" + mc_icon + '\'' +
                ", mc_keystates='" + mc_keystates + '\'' +
                ", marking_schema_id='" + marking_schema_id + '\'' +
                ", mc_created_at='" + mc_created_at + '\'' +
                ", mc_updated_at='" + mc_updated_at + '\'' +
                ", mc_status='" + mc_status + '\'' +
                '}';
    }
}
