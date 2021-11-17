package com.seekho.live.AppBase;

//Created by Mohammed Faisal 15 March 2021

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.seekho.live.Activities.MockTestsActivity;
import com.seekho.live.Activities.QuizActivity;
import com.seekho.live.Activities.SubjectsLectureActivity;
import com.seekho.live.Base.BaseAdapter;
import com.seekho.live.Dialogs.EnrollNowDialog;
import com.seekho.live.Dialogs.QuizAleartDialog;
import com.seekho.live.Interfaces.Constants;
import com.seekho.live.WebBase.WebContants;

public abstract class AppBaseAdapter extends BaseAdapter implements Constants, WebContants {

    public void showSimpleToast(String msg){
        if (msg != null && !msg.isEmpty()){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void goToSubjectsLecturesActivity(Bundle bundle){
        Intent intent = new Intent(context, SubjectsLectureActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public QuizAleartDialog getQuizAleartDialog(Context context,Bundle bundle) {
        QuizAleartDialog dialog = QuizAleartDialog.getInstance(context);
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(context),context.getClass().getSimpleName());
        return dialog;
    }

    public EnrollNowDialog getEnrollDialog(Context context, Bundle bundle) {
        EnrollNowDialog dialog = EnrollNowDialog.getInstance(context);
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(context),context.getClass().getSimpleName());
        return dialog;
    }

    public FragmentManager getFragManager(Context context){
        return ((AppCompatActivity)context).getSupportFragmentManager();
    }

    public void goToMockTestActivity(Context context,Bundle bundle){
        Intent intent = new Intent(context, MockTestsActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public void goToQuizActivity(Context context,Bundle bundle){
        Intent intent = new Intent(context, QuizActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
