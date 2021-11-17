package com.seekho.live.AppBase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Activities.LecturesAndAccompayingMCQActivity;
import com.seekho.live.Activities.SubCoursesActivity;
import com.seekho.live.Activities.WebViewActivity;
import com.seekho.live.BaseToolsFragment.ToolbarFragment;
import com.seekho.live.Dialogs.EnrollNowDialog;
import com.seekho.live.Interfaces.Constants;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebListener;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public abstract class AppBaseRV extends RecyclerView implements OnRecyclerListener,
        View.OnClickListener, Constants, WebContants, WebListener, ToolbarFragment.SearchListener {

    Context context;

    public AppBaseRV(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public abstract void initializeComponents(View view);

    public abstract void setData(View view, int position);

    @Override
    public void onItemClick(View view, int position) {
        if (view == null) return;
        initializeComponents(view);
        setData(view, position);
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {

    }

    @Override
    public void onWebFailure(Throwable throwable) {

    }

    public void showSimpleToast(String msg) {
        if (msg != null && !msg.isEmpty()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void updateViewVisibility(View view, int visibility) {
        if (view == null) return;
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }

    public void startBlinkAnim(View view){
        if (view != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_blink);
            view.startAnimation(animation);
        }
    }

    public void goToSubCoursesActivity(Bundle bundle) {
        Intent intent = new Intent(context, SubCoursesActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public void goToLecturesAndAccMCQActivity(Bundle bundle) {
        Intent intent = new Intent(context, LecturesAndAccompayingMCQActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public void goToWebViewActivity(Bundle bundle) {
        Intent intent = new Intent(context, WebViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public FragmentManager getFragManager(Context context) {
        return ((AppCompatActivity) context).getSupportFragmentManager();
    }

    public EnrollNowDialog getEnrollDialog(Context context, Bundle bundle) {
        EnrollNowDialog enrollNowDialog = EnrollNowDialog.getInstance(context);
        if (bundle != null) {
            enrollNowDialog.setArguments(bundle);
        }
        enrollNowDialog.show(getFragManager(context),context.getClass().getSimpleName());
        return enrollNowDialog;
    }

    //--------------------------------------- SearchListener --------------------------------------
    @Override
    public void onQueryChanged(String newText) {

    }
}
