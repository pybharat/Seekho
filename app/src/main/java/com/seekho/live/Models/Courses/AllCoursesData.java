package com.seekho.live.Models.Courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCoursesData {
    @SerializedName("cc_id")
    @Expose
    String cc_id;

    @SerializedName("cc_slug")
    @Expose
    String cc_slug;

    @SerializedName("cc_page_title")
    @Expose
    String cc_page_title;

    @SerializedName("cc_meta_description")
    @Expose
    String cc_meta_description;

    @SerializedName("cc_keywords")
    @Expose
    String cc_keywords;

    @SerializedName("cc_name")
    @Expose
    String cc_name;

    @SerializedName("cc_short_description")
    @Expose
    String cc_short_description;

    @SerializedName("cc_key_states")
    @Expose
    String cc_key_states;

    @SerializedName("cc_description")
    @Expose
    String cc_description;

    @SerializedName("cc_introducting_vedio")
    @Expose
    String cc_introducting_vedio;

    @SerializedName("cc_curriculum_file")
    @Expose
    String cc_curriculum_file;

    @SerializedName("cc_thumbnail")
    @Expose
    String cc_thumbnail;

    @SerializedName("cc_price")
    @Expose
    String cc_price;

    @SerializedName("cc_language")
    @Expose
    String cc_language;

    @SerializedName("cc_have_subcategory")
    @Expose
    String cc_have_subcategory;

    @SerializedName("cc_status")
    @Expose
    String cc_status;

    @SerializedName("enrollstatus")
    @Expose
    int enrollstatus;

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

    public String getCc_page_title() {
        return cc_page_title;
    }

    public void setCc_page_title(String cc_page_title) {
        this.cc_page_title = cc_page_title;
    }

    public String getCc_meta_description() {
        return cc_meta_description;
    }

    public void setCc_meta_description(String cc_meta_description) {
        this.cc_meta_description = cc_meta_description;
    }

    public String getCc_keywords() {
        return cc_keywords;
    }

    public void setCc_keywords(String cc_keywords) {
        this.cc_keywords = cc_keywords;
    }

    public String getCc_name() {
        return cc_name;
    }

    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }

    public String getCc_short_description() {
        return cc_short_description;
    }

    public void setCc_short_description(String cc_short_description) {
        this.cc_short_description = cc_short_description;
    }

    public String getCc_key_states() {
        return cc_key_states;
    }

    public void setCc_key_states(String cc_key_states) {
        this.cc_key_states = cc_key_states;
    }

    public String getCc_description() {
        return cc_description;
    }

    public void setCc_description(String cc_description) {
        this.cc_description = cc_description;
    }

    public String getCc_introducting_vedio() {
        return cc_introducting_vedio;
    }

    public void setCc_introducting_vedio(String cc_introducting_vedio) {
        this.cc_introducting_vedio = cc_introducting_vedio;
    }

    public String getCc_curriculum_file() {
        return cc_curriculum_file;
    }

    public void setCc_curriculum_file(String cc_curriculum_file) {
        this.cc_curriculum_file = cc_curriculum_file;
    }

    public String getCc_thumbnail() {
        return cc_thumbnail;
    }

    public void setCc_thumbnail(String cc_thumbnail) {
        this.cc_thumbnail = cc_thumbnail;
    }

    public String getCc_price() {
        return cc_price;
    }

    public void setCc_price(String cc_price) {
        this.cc_price = cc_price;
    }

    public String getCc_language() {
        return cc_language;
    }

    public void setCc_language(String cc_language) {
        this.cc_language = cc_language;
    }

    public String getCc_have_subcategory() {
        return cc_have_subcategory;
    }

    public void setCc_have_subcategory(String cc_have_subcategory) {
        this.cc_have_subcategory = cc_have_subcategory;
    }

    public String getCc_status() {
        return cc_status;
    }

    public void setCc_status(String cc_status) {
        this.cc_status = cc_status;
    }

    public int getEnrollstatus() {
        return enrollstatus;
    }

    public void setEnrollstatus(int enrollstatus) {
        this.enrollstatus = enrollstatus;
    }

    @Override
    public String toString() {
        return "AllCoursesData{" +
                "cc_id='" + cc_id + '\'' +
                ", cc_slug='" + cc_slug + '\'' +
                ", cc_page_title='" + cc_page_title + '\'' +
                ", cc_meta_description='" + cc_meta_description + '\'' +
                ", cc_keywords='" + cc_keywords + '\'' +
                ", cc_name='" + cc_name + '\'' +
                ", cc_short_description='" + cc_short_description + '\'' +
                ", cc_key_states='" + cc_key_states + '\'' +
                ", cc_description='" + cc_description + '\'' +
                ", cc_introducting_vedio='" + cc_introducting_vedio + '\'' +
                ", cc_curriculum_file='" + cc_curriculum_file + '\'' +
                ", cc_thumbnail='" + cc_thumbnail + '\'' +
                ", cc_price='" + cc_price + '\'' +
                ", cc_language='" + cc_language + '\'' +
                ", cc_have_subcategory='" + cc_have_subcategory + '\'' +
                ", cc_status='" + cc_status + '\'' +
                '}';
    }
}
