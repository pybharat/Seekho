package com.seekho.live.Models.MockTests.MockQuiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MockQuizListDataModel {

    @SerializedName("ts_id")
    @Expose
    String ts_id;

    @SerializedName("mc_id")
    @Expose
    String mc_id;

    @SerializedName("ts_name")
    @Expose
    String ts_name;

    @SerializedName("ts_slug")
    @Expose
    String ts_slug;

    @SerializedName("ts_discription")
    @Expose
    String ts_discription;

    @SerializedName("total_questions")
    @Expose
    String total_questions;

    @SerializedName("marking_schema_id")
    @Expose
    String marking_schema_id;

    @SerializedName("ts_created_at")
    @Expose
    String ts_created_at;

    @SerializedName("ts_updated_at")
    @Expose
    String ts_updated_at;

    @SerializedName("ts_status")
    @Expose
    String ts_status;

    @SerializedName("test_duration")
    @Expose
    String test_duration;

    public String getTs_id() {
        return ts_id;
    }

    public void setTs_id(String ts_id) {
        this.ts_id = ts_id;
    }

    public String getMc_id() {
        return mc_id;
    }

    public void setMc_id(String mc_id) {
        this.mc_id = mc_id;
    }

    public String getTs_name() {
        return ts_name;
    }

    public void setTs_name(String ts_name) {
        this.ts_name = ts_name;
    }

    public String getTs_slug() {
        return ts_slug;
    }

    public void setTs_slug(String ts_slug) {
        this.ts_slug = ts_slug;
    }

    public String getTs_discription() {
        return ts_discription;
    }

    public void setTs_discription(String ts_discription) {
        this.ts_discription = ts_discription;
    }

    public String getTotal_questions() {
        return total_questions;
    }

    public void setTotal_questions(String total_questions) {
        this.total_questions = total_questions;
    }

    public String getMarking_schema_id() {
        return marking_schema_id;
    }

    public void setMarking_schema_id(String marking_schema_id) {
        this.marking_schema_id = marking_schema_id;
    }

    public String getTs_created_at() {
        return ts_created_at;
    }

    public void setTs_created_at(String ts_created_at) {
        this.ts_created_at = ts_created_at;
    }

    public String getTs_updated_at() {
        return ts_updated_at;
    }

    public void setTs_updated_at(String ts_updated_at) {
        this.ts_updated_at = ts_updated_at;
    }

    public String getTs_status() {
        return ts_status;
    }

    public void setTs_status(String ts_status) {
        this.ts_status = ts_status;
    }

    public String getTest_duration() {
        return test_duration;
    }

    public void setTest_duration(String test_duration) {
        this.test_duration = test_duration;
    }

    @Override
    public String toString() {
        return "MockQuizListDataModel{" +
                "ts_id='" + ts_id + '\'' +
                ", mc_id='" + mc_id + '\'' +
                ", ts_name='" + ts_name + '\'' +
                ", ts_slug='" + ts_slug + '\'' +
                ", ts_discription='" + ts_discription + '\'' +
                ", total_questions='" + total_questions + '\'' +
                ", marking_schema_id='" + marking_schema_id + '\'' +
                ", ts_created_at='" + ts_created_at + '\'' +
                ", ts_updated_at='" + ts_updated_at + '\'' +
                ", ts_status='" + ts_status + '\'' +
                ", test_duration='" + test_duration + '\'' +
                '}';
    }
}
