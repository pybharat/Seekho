package com.seekho.live.Models.Quiz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizModel {

    @SerializedName("code")
    @Expose
    int code;

    @SerializedName("message")
    @Expose
    List<Message> message;

    @SerializedName("testhistoryid")
    @Expose
    String testhistoryid;

    @SerializedName("notansweredquestions")
    @Expose
    int notansweredquestions;

    @SerializedName("answeredquestions")
    @Expose
    int answeredquestions;

    @SerializedName("markedasreviewquestions")
    @Expose
    int markedasreviewquestions;

    @SerializedName("each_question_marks")
    @Expose
    String each_question_marks;

    @SerializedName("negative_marking")
    @Expose
    String negative_marking;

    @SerializedName("quiz_duration")
    @Expose
    long quiz_duration;

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

    public class Message{

        @SerializedName("cq_id")
        @Expose
        String cq_id;

        @SerializedName("question")
        @Expose
        String question;

        @SerializedName("options")
        @Expose
        List<String> options;

        @SerializedName("showstatus")
        @Expose
        int showstatus;

        @SerializedName("ques_level_id")
        @Expose
        String ques_level_id;

        @SerializedName("ques_type_id")
        @Expose
        String ques_type_id;

        public String getCq_id() {
            return cq_id;
        }

        public void setCq_id(String cq_id) {
            this.cq_id = cq_id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<String> getOptions() {
            return options;
        }

        public String getOption(){
            String option = "";
            if (options != null && options.size() > 0){
                for (String options : options){
                    option = options;
                }
            }
            return option;
        }

        public void setOptions(List<String> options) {
            this.options = options;
        }

        public int getShowstatus() {
            return showstatus;
        }

        public void setShowstatus(int showstatus) {
            this.showstatus = showstatus;
        }

        public String getQues_level_id() {
            return ques_level_id;
        }

        public void setQues_level_id(String ques_level_id) {
            this.ques_level_id = ques_level_id;
        }

        public String getQues_type_id() {
            return ques_type_id;
        }

        public void setQues_type_id(String ques_type_id) {
            this.ques_type_id = ques_type_id;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "question='" + question + '\'' +
                    ", options=" + options +
                    ", showstatus=" + showstatus +
                    '}';
        }
    }

    public String getTesthistoryid() {
        return testhistoryid;
    }

    public void setTesthistoryid(String testhistoryid) {
        this.testhistoryid = testhistoryid;
    }

    public int getNotansweredquestions() {
        return notansweredquestions;
    }

    public void setNotansweredquestions(int notansweredquestions) {
        this.notansweredquestions = notansweredquestions;
    }

    public int getAnsweredquestions() {
        return answeredquestions;
    }

    public void setAnsweredquestions(int answeredquestions) {
        this.answeredquestions = answeredquestions;
    }

    public int getMarkedasreviewquestions() {
        return markedasreviewquestions;
    }

    public void setMarkedasreviewquestions(int markedasreviewquestions) {
        this.markedasreviewquestions = markedasreviewquestions;
    }

    public String getEach_question_marks() {
        return each_question_marks;
    }

    public void setEach_question_marks(String each_question_marks) {
        this.each_question_marks = each_question_marks;
    }

    public String getNegative_marking() {
        return negative_marking;
    }

    public void setNegative_marking(String negative_marking) {
        this.negative_marking = negative_marking;
    }

    public long getQuiz_duration() {
        return quiz_duration;
    }

    public void setQuiz_duration(long quiz_duration) {
        this.quiz_duration = quiz_duration;
    }

    @Override
    public String toString() {
        return "QuizModel{" +
                "code=" + code +
                ", message=" + message +
                ", notansweredquestions=" + notansweredquestions +
                ", answeredquestions=" + answeredquestions +
                ", markedasreviewquestions=" + markedasreviewquestions +
                ", each_question_marks='" + each_question_marks + '\'' +
                ", negative_marking='" + negative_marking + '\'' +
                ", quiz_duration='" + quiz_duration + '\'' +
                '}';
    }
}
