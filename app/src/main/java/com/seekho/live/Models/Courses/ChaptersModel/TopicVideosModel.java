package com.seekho.live.Models.Courses.ChaptersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicVideosModel {

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
        return "TopicVideosModel{" +
                "code=" + code +
                ", message=" + message +
                '}';
    }

    public class Message{
        @SerializedName("videodet")
        @Expose
        VideoData videodet;

        public VideoData getVideodet() {
            return videodet;
        }

        public void setVideodet(VideoData videodet) {
            this.videodet = videodet;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "videodet=" + videodet +
                    '}';
        }
    }

    public class VideoData{
        @SerializedName("cv_id")
        @Expose
        String cv_id;

        @SerializedName("topic_id")
        @Expose
        String topic_id;

        @SerializedName("cv_vedio_provider")
        @Expose
        String cv_vedio_provider;

        @SerializedName("cv_description")
        @Expose
        String cv_description;

        @SerializedName("cv_location")
        @Expose
        String cv_location;

        @SerializedName("cv_durations")
        @Expose
        String cv_durations;

        @SerializedName("notes_file")
        @Expose
        String notes_file;

        @SerializedName("cv_status")
        @Expose
        String cv_status;

        public String getCv_id() {
            return cv_id;
        }

        public void setCv_id(String cv_id) {
            this.cv_id = cv_id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getCv_vedio_provider() {
            return cv_vedio_provider;
        }

        public void setCv_vedio_provider(String cv_vedio_provider) {
            this.cv_vedio_provider = cv_vedio_provider;
        }

        public String getCv_description() {
            return cv_description;
        }

        public void setCv_description(String cv_description) {
            this.cv_description = cv_description;
        }

        public String getCv_location() {
            return cv_location;
        }

        public void setCv_location(String cv_location) {
            this.cv_location = cv_location;
        }

        public String getCv_durations() {
            return cv_durations;
        }

        public void setCv_durations(String cv_durations) {
            this.cv_durations = cv_durations;
        }

        public String getNotes_file() {
            return notes_file;
        }

        public void setNotes_file(String notes_file) {
            this.notes_file = notes_file;
        }

        public String getCv_status() {
            return cv_status;
        }

        public void setCv_status(String cv_status) {
            this.cv_status = cv_status;
        }

        @Override
        public String toString() {
            return "VideoData{" +
                    "cv_id='" + cv_id + '\'' +
                    ", topic_id='" + topic_id + '\'' +
                    ", cv_vedio_provider='" + cv_vedio_provider + '\'' +
                    ", cv_description='" + cv_description + '\'' +
                    ", cv_location='" + cv_location + '\'' +
                    ", cv_durations='" + cv_durations + '\'' +
                    ", notes_file='" + notes_file + '\'' +
                    ", cv_status='" + cv_status + '\'' +
                    '}';
        }
    }
}
