package com.seekho.live.Models.Courses.SubCourses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryData {

    @SerializedName("csc_id")
    @Expose
    String csc_id;

    @SerializedName("csc_slug")
    @Expose
    String csc_slug;

    @SerializedName("csc_page_title")
    @Expose
    String csc_page_title;

    @SerializedName("cc_id")
    @Expose
    String cc_id;

    @SerializedName("csc_meta_description")
    @Expose
    String csc_meta_description;

    @SerializedName("csc_keywords")
    @Expose
    String csc_keywords;

    @SerializedName("csc_name")
    @Expose
    String csc_name;

    @SerializedName("csc_short_description")
    @Expose
    String csc_short_description;

    @SerializedName("csc_key_states")
    @Expose
    String csc_key_states;

    @SerializedName("csc_description")
    @Expose
    String csc_description;

    @SerializedName("csc_introducting_vedio")
    @Expose
    String csc_introducting_vedio;

    @SerializedName("csc_curriculum_file")
    @Expose
    String csc_curriculum_file;

    @SerializedName("csc_thumbnail")
    @Expose
    String csc_thumbnail;

    @SerializedName("csc_price")
    @Expose
    String csc_price;

    @SerializedName("csc_language")
    @Expose
    String csc_language;

    @SerializedName("csc_have_subcategory")
    @Expose
    String csc_have_subcategory;

    @SerializedName("is_paid")
    @Expose
    String is_paid;

    @SerializedName("csc_status")
    @Expose
    String csc_status;

    @SerializedName("enrollstatus")
    @Expose
    int enrollstatus;

    public String getCsc_id() {
        return csc_id;
    }

    public void setCsc_id(String csc_id) {
        this.csc_id = csc_id;
    }

    public String getCsc_slug() {
        return csc_slug;
    }

    public void setCsc_slug(String csc_slug) {
        this.csc_slug = csc_slug;
    }

    public String getCsc_page_title() {
        return csc_page_title;
    }

    public void setCsc_page_title(String csc_page_title) {
        this.csc_page_title = csc_page_title;
    }

    public String getCc_id() {
        return cc_id;
    }

    public void setCc_id(String cc_id) {
        this.cc_id = cc_id;
    }

    public String getCsc_meta_description() {
        return csc_meta_description;
    }

    public void setCsc_meta_description(String csc_meta_description) {
        this.csc_meta_description = csc_meta_description;
    }

    public String getCsc_keywords() {
        return csc_keywords;
    }

    public void setCsc_keywords(String csc_keywords) {
        this.csc_keywords = csc_keywords;
    }

    public String getCsc_name() {
        return csc_name;
    }

    public void setCsc_name(String csc_name) {
        this.csc_name = csc_name;
    }

    public String getCsc_short_description() {
        return csc_short_description;
    }

    public void setCsc_short_description(String csc_short_description) {
        this.csc_short_description = csc_short_description;
    }

    public String getCsc_key_states() {
        return csc_key_states;
    }

    public void setCsc_key_states(String csc_key_states) {
        this.csc_key_states = csc_key_states;
    }

    public String getCsc_description() {
        return csc_description;
    }

    public void setCsc_description(String csc_description) {
        this.csc_description = csc_description;
    }

    public String getCsc_introducting_vedio() {
        return csc_introducting_vedio;
    }

    public void setCsc_introducting_vedio(String csc_introducting_vedio) {
        this.csc_introducting_vedio = csc_introducting_vedio;
    }

    public String getCsc_curriculum_file() {
        return csc_curriculum_file;
    }

    public void setCsc_curriculum_file(String csc_curriculum_file) {
        this.csc_curriculum_file = csc_curriculum_file;
    }

    public String getCsc_thumbnail() {
        return csc_thumbnail;
    }

    public void setCsc_thumbnail(String csc_thumbnail) {
        this.csc_thumbnail = csc_thumbnail;
    }

    public String getCsc_price() {
        return csc_price;
    }

    public void setCsc_price(String csc_price) {
        this.csc_price = csc_price;
    }

    public String getCsc_language() {
        return csc_language;
    }

    public void setCsc_language(String csc_language) {
        this.csc_language = csc_language;
    }

    public String getCsc_have_subcategory() {
        return csc_have_subcategory;
    }

    public void setCsc_have_subcategory(String csc_have_subcategory) {
        this.csc_have_subcategory = csc_have_subcategory;
    }

    public String getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(String is_paid) {
        this.is_paid = is_paid;
    }

    public String getCsc_status() {
        return csc_status;
    }

    public void setCsc_status(String csc_status) {
        this.csc_status = csc_status;
    }

    public int getEnrollstatus() {
        return enrollstatus;
    }

    public void setEnrollstatus(int enrollstatus) {
        this.enrollstatus = enrollstatus;
    }

    @Override
    public String toString() {
        return "SubCategoryData{" +
                "csc_id='" + csc_id + '\'' +
                ", csc_slug='" + csc_slug + '\'' +
                ", csc_page_title='" + csc_page_title + '\'' +
                ", cc_id='" + cc_id + '\'' +
                ", csc_meta_description='" + csc_meta_description + '\'' +
                ", csc_keywords='" + csc_keywords + '\'' +
                ", csc_name='" + csc_name + '\'' +
                ", csc_short_description='" + csc_short_description + '\'' +
                ", csc_key_states='" + csc_key_states + '\'' +
                ", csc_description='" + csc_description + '\'' +
                ", csc_introducting_vedio='" + csc_introducting_vedio + '\'' +
                ", csc_curriculum_file='" + csc_curriculum_file + '\'' +
                ", csc_thumbnail='" + csc_thumbnail + '\'' +
                ", csc_price='" + csc_price + '\'' +
                ", csc_language='" + csc_language + '\'' +
                ", csc_have_subcategory='" + csc_have_subcategory + '\'' +
                ", is_paid='" + is_paid + '\'' +
                ", csc_status='" + csc_status + '\'' +
                '}';
    }
}
