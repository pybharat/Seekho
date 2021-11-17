package com.seekho.live.Models.Quiz;

public class QuizSummary {

    int position;
    int show_status;
    String question_no;

    public QuizSummary(int position, int show_status, String question_no) {
        this.position = position;
        this.show_status = show_status;
        this.question_no = question_no;
    }

    public QuizSummary(int show_status, String question_no) {
        this.show_status = show_status;
        this.question_no = question_no;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getShow_status() {
        return show_status;
    }

    public void setShow_status(int show_status) {
        this.show_status = show_status;
    }

    public String getQuestion_no() {
        return question_no;
    }

    public void setQuestion_no(String question_no) {
        this.question_no = question_no;
    }

    @Override
    public String toString() {
        return "QuizSummary{" +
                "show_status=" + show_status +
                ", question_no='" + question_no + '\'' +
                '}';
    }
}
