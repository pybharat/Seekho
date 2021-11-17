package com.seekho.live.Models.Quiz;

import java.io.Serializable;
import java.util.List;

public class QuizSummaryModel implements Serializable {

    List<QuizSummary> quizSummaries;

    public List<QuizSummary> getQuizSummaries() {
        return quizSummaries;
    }

    public void setQuizSummaries(List<QuizSummary> quizSummaries) {
        this.quizSummaries = quizSummaries;
    }

    @Override
    public String toString() {
        return "QuizSummaryModel{" +
                "quizSummaries=" + quizSummaries +
                '}';
    }
}
