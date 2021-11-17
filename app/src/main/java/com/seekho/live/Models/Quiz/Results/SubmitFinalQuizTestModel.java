package com.seekho.live.Models.Quiz.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubmitFinalQuizTestModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    List<Message> message;

    @SerializedName("marks_obtain")
    @Expose
    int marks_obtain;

    @SerializedName("totalquizquestions")
    @Expose
    int totalquizquestions;

    @SerializedName("test_date")
    @Expose
    String test_date;

    @SerializedName("test_duration")
    @Expose
    String test_duration;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public int getMarks_obtain() {
        return marks_obtain;
    }

    public void setMarks_obtain(int marks_obtain) {
        this.marks_obtain = marks_obtain;
    }

    public int getTotalquizquestions() {
        return totalquizquestions;
    }

    public void setTotalquizquestions(int totalquizquestions) {
        this.totalquizquestions = totalquizquestions;
    }

    public String getTest_date() {
        return test_date;
    }

    public void setTest_date(String test_date) {
        this.test_date = test_date;
    }

    public String getTest_duration() {
        return test_duration;
    }

    public void setTest_duration(String test_duration) {
        this.test_duration = test_duration;
    }

    @Override
    public String toString() {
        return "SubmitFinalQuizTestModel{" +
                "code=" + code +
                ", message=" + message +
                ", marks_obtain=" + marks_obtain +
                ", totalquizquestions=" + totalquizquestions +
                ", test_date='" + test_date + '\'' +
                ", test_duration='" + test_duration + '\'' +
                '}';
    }

    public class Message {

        @SerializedName("cq_id")
        @Expose
        String cq_id;

        @SerializedName("topic_id")
        @Expose
        String topic_id;

        @SerializedName("cq_question")
        @Expose
        String cq_question;

        @SerializedName("cq_options")
        @Expose
        QuizOptions cq_options;

        @SerializedName("correct_options")
        @Expose
        CorrectOptions correct_options;

        @SerializedName("cq_explaination")
        @Expose
        String cq_explaination;

        @SerializedName("cq_status")
        @Expose
        String cq_status;

        @SerializedName("cq_created_at")
        @Expose
        String cq_created_at;

        @SerializedName("cq_updated_at")
        @Expose
        String cq_updated_at;

        @SerializedName("my_answer")
        @Expose
        List<Integer> my_answer;

        @SerializedName("mark")
        @Expose
        int mark;

        public String getCq_id() {
            return cq_id;
        }

        public void setCq_id(String cq_id) {
            this.cq_id = cq_id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getCq_question() {
            return cq_question;
        }

        public void setCq_question(String cq_question) {
            this.cq_question = cq_question;
        }

        public QuizOptions getCq_options() {
            return cq_options;
        }

        public void setCq_options(QuizOptions cq_options) {
            this.cq_options = cq_options;
        }

        public CorrectOptions getCorrect_options() {
            return correct_options;
        }

        public void setCorrect_options(CorrectOptions correct_options) {
            this.correct_options = correct_options;
        }

        public String getCq_explaination() {
            return cq_explaination;
        }

        public void setCq_explaination(String cq_explaination) {
            this.cq_explaination = cq_explaination;
        }

        public String getCq_status() {
            return cq_status;
        }

        public void setCq_status(String cq_status) {
            this.cq_status = cq_status;
        }

        public String getCq_created_at() {
            return cq_created_at;
        }

        public void setCq_created_at(String cq_created_at) {
            this.cq_created_at = cq_created_at;
        }

        public String getCq_updated_at() {
            return cq_updated_at;
        }

        public void setCq_updated_at(String cq_updated_at) {
            this.cq_updated_at = cq_updated_at;
        }

        public List<Integer> getMy_answer() {
            return my_answer;
        }

        public void setMy_answer(List<Integer> my_answer) {
            this.my_answer = my_answer;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "cq_id='" + cq_id + '\'' +
                    ", topic_id='" + topic_id + '\'' +
                    ", cq_question='" + cq_question + '\'' +
                    ", cq_options=" + cq_options +
                    ", correct_options=" + correct_options +
                    ", cq_explaination='" + cq_explaination + '\'' +
                    ", cq_status='" + cq_status + '\'' +
                    ", cq_created_at='" + cq_created_at + '\'' +
                    ", cq_updated_at='" + cq_updated_at + '\'' +
                    ", my_answer=" + my_answer +
                    ", mark=" + mark +
                    '}';
        }
    }
}
