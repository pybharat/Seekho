package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Models.Quiz.Results.QuizOptions;
import com.seekho.live.Models.Quiz.Results.SubmitFinalQuizTestModel;
import com.seekho.live.R;

import java.util.List;

import io.github.kexanie.library.MathView;

public class ResultAdapter extends AppBaseAdapter {

    Context context;
    List<SubmitFinalQuizTestModel.Message> finalResultList;
    OnRecyclerListener recyclerListener;

    TextView question_no_tv;
    MathView question_tv;
    MathView option_one_tv, option_two_tv, option_three_tv, option_four_tv;
    MathView correct_option_tv;
    TextView quiz_obtain_mark, your_ans_tv;

    public ResultAdapter(Context context, List<SubmitFinalQuizTestModel.Message> finalResultList, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.finalResultList = finalResultList;
        this.recyclerListener = recyclerListener;
    }

    public ResultAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_results_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (view == null) return;
        if (recyclerListener == null) return;
        recyclerListener.onItemClick(view, position);

        question_no_tv = view.findViewById(R.id.question_no_tv);
        question_tv = view.findViewById(R.id.question_tv);
        option_one_tv = view.findViewById(R.id.option_one_tv);
        option_two_tv = view.findViewById(R.id.option_two_tv);
        option_three_tv = view.findViewById(R.id.option_three_tv);
        option_four_tv = view.findViewById(R.id.option_four_tv);
        quiz_obtain_mark = view.findViewById(R.id.quiz_obtain_mark);
        correct_option_tv = view.findViewById(R.id.correct_option_tv);
        your_ans_tv = view.findViewById(R.id.your_ans_tv);

        if (finalResultList != null && finalResultList.size() > 0) {
            SubmitFinalQuizTestModel.Message messageList = finalResultList.get(position);
            if (messageList != null) {

                question_no_tv.setText("Question " + (position + 1) + ":");
                String que_actual_string = messageList.getCq_question();
                if (que_actual_string.contains("$")){
                    //String que_replaced_string = que_actual_string.replaceAll("\\$","");
                    String que_final_string = que_actual_string.replace("$","$$");
                    question_tv.setText(que_final_string);
                } else {
                    question_tv.setText(que_actual_string);
                }

                setData(messageList, position);

                quiz_obtain_mark.setText(String.valueOf(messageList.getMark()));

                if (messageList.getCorrect_options() != null){
                    String actual_string = messageList.getCorrect_options().getOption();
                    if (actual_string.contains("$")){
                        String replaced_string = actual_string.replaceAll("\\$","");
                        String final_string = replaced_string.replace(replaced_string,"\\(" + replaced_string + "\\)");
                        correct_option_tv.setText(final_string);
                    } else {
                        correct_option_tv.setText(actual_string);
                    }
                }

                if (messageList.getMy_answer() != null && messageList.getMy_answer().size() > 0) {
                    String correct_answer = String.valueOf(messageList.getMy_answer().get(0));
                    if (correct_answer != null && !correct_answer.equals(""))
                    your_ans_tv.setText(correct_answer);
                } else {
                    your_ans_tv.setText("");
                }
            }
        } else {
            return;
        }
    }

    private void setData(SubmitFinalQuizTestModel.Message messageList, int position) {
        if (messageList != null){
            QuizOptions quizOptions = messageList.getCq_options();
            if (quizOptions != null){

                //---------------------------- Option One ------------------------------------
                String op_one_actual_string = quizOptions.getOne();
                if (op_one_actual_string.contains("$")){
                    String op_one_replaced_string = op_one_actual_string.replaceAll("\\$","");
                    String op_one_final_string = op_one_replaced_string.replace(op_one_replaced_string,"\\(" + op_one_replaced_string + "\\)");
                    option_one_tv.setText(op_one_final_string);
                } else {
                    option_one_tv.setText(op_one_actual_string);
                }

                //---------------------------- Option Two ------------------------------------
                String op_two_actual_string = quizOptions.getTwo();
                if (op_two_actual_string.contains("$")){
                    String op_two_replaced_string = op_two_actual_string.replaceAll("\\$","");
                    String op_two_final_string = op_two_replaced_string.replace(op_two_replaced_string,"\\(" + op_two_replaced_string + "\\)");
                    option_two_tv.setText(op_two_final_string);
                } else {
                    option_two_tv.setText(op_two_actual_string);
                }

                //---------------------------- Option Three ------------------------------------
                String op_three_actual_string = quizOptions.getThree();
                if (op_three_actual_string.contains("$")){
                    String op_three_replaced_string = op_three_actual_string.replaceAll("\\$","");
                    String op_three_final_string = op_three_replaced_string.replace(op_three_replaced_string,"\\(" + op_three_replaced_string + "\\)");
                    option_three_tv.setText(op_three_final_string);
                } else {
                    option_three_tv.setText(op_three_actual_string);
                }

                //---------------------------- Option Four ------------------------------------
                String op_forth_actual_string = quizOptions.getFour();
                if (op_forth_actual_string.contains("$")){
                    String op_forth_replaced_string = op_forth_actual_string.replaceAll("\\$","");
                    String op_forth_final_string = op_forth_replaced_string.replace(op_forth_replaced_string,"\\(" + op_forth_replaced_string + "\\)");
                    option_four_tv.setText(op_forth_final_string);
                } else {
                    option_four_tv.setText(op_forth_actual_string);
                }
            }
        }
    }


    @Override
    public int getDataCount() {
        return finalResultList.size();
    }
}
