package com.seekho.live.AppBase;

//Created by Mohammed Faisal 15 March 2021

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;

import com.seekho.live.Activities.DashboardActivity;
import com.seekho.live.Activities.LecturesAndAccompayingMCQActivity;
import com.seekho.live.Activities.OTPVerificationActivity;
import com.seekho.live.Activities.QuizActivity;
import com.seekho.live.Activities.ResultsActivity;
import com.seekho.live.Activities.SubCoursesActivity;
import com.seekho.live.Activities.UpdateProfileActivity;
import com.seekho.live.Base.BaseDialogFragment;
import com.seekho.live.Dialogs.ForgetPasswordDialog;
import com.seekho.live.Dialogs.ProgressDialog;
import com.seekho.live.Interfaces.Constants;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebListener;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public abstract class AppBaseDialogFragment extends BaseDialogFragment implements Constants,
        WebContants, WebListener, OnRecyclerListener {

    @Override
    public void initializeComponents() {
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    public void pickImagesFromGallery(int requestCode) {
        ///Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        ((UpdateProfileActivity)getActivity()).startActivityForResult(intent,requestCode);
    }

    Lifecycle lifecycle = new Lifecycle() {
        @Override
        public void addObserver(@NonNull LifecycleObserver observer) {

        }

        @Override
        public void removeObserver(@NonNull LifecycleObserver observer) {

        }

        @NonNull
        @Override
        public State getCurrentState() {
            return null;
        }
    };

    public void updateViewVisibility(View view, int visibility) {
        if (view == null) return;
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

    public Handler createDelay(long forDuration,Runnable runnable){
        Handler handler = new Handler();
        if (runnable != null){
            handler.postDelayed(runnable,forDuration);
        }
        return handler;
    }

    public void goToSubCoursesActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), SubCoursesActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToDashboardActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToLecturesAndAccMCQActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), LecturesAndAccompayingMCQActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public ForgetPasswordDialog getForgetPassDialog(Context context, Bundle bundle){
        ForgetPasswordDialog dialog = ForgetPasswordDialog.getInstance(context);
        if (bundle != null){
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(),dialog.getClass().getSimpleName());
        return dialog;
    }

    public ProgressDialog showProgress(Context context) {
        ProgressDialog progressDialog = ProgressDialog.getInstance(context);
        if (progressDialog != null)
            progressDialog.show(getFragManager(), progressDialog.getClass().getSimpleName());
        return progressDialog;
    }

    public void finishProgress(Context context){
        ProgressDialog progressDialog = ProgressDialog.getInstance(context);
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void startRotateAnim(View view) {
        if (view != null) {
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_rotate);
            view.startAnimation(animation);
        }
    }

    public void startZoomInZoomOutAnim(View view) {
        if (view != null) {
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_zoom_in_zoom_out);
            view.startAnimation(animation);
        }
    }

    public void showSimpleToast(String msg){
        if (msg != null && !msg.isEmpty()){
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void goToOTPVerificationActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), OTPVerificationActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToTestsActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToQuizActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToResultsActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), ResultsActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //------------------------------------ Web Listeners ------------------------------------------

    public WebRequest makeWebRequest(Context context) {
        WebRequest webRequest = new WebRequest(context);
        if (webRequest != null) {
            return webRequest;
        } else {
            return null;
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {

    }

    @Override
    public void onWebFailure(Throwable throwable) {

    }
}
