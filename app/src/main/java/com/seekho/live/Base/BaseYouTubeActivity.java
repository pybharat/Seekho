package com.seekho.live.Base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeBaseActivity;

public abstract class BaseYouTubeActivity extends YouTubeBaseActivity implements View.OnClickListener {

    public abstract int layoutResourceID();
    public abstract void initializeComponents();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(layoutResourceID());
        initializeComponents();
    }

    public FragmentActivity getFragmentActivity(){
        return new FragmentActivity();
    }

    public FragmentTransaction getFragTransaction(Activity context){
        return getFragManager(context).beginTransaction();
    }

    public FragmentManager getFragManager(Activity activity){
        return ((AppCompatActivity)activity).getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {

    }
}
