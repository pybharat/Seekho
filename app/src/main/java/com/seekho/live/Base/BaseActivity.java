package com.seekho.live.Base;

//Created by Mohammed Faisal 15 March 2021

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.seekho.live.Interfaces.OnDayNightStateListener;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public abstract int layoutResourceID();

    public abstract void initializeComponents();

    public int getFragmentContainerResourceID(){
        return getFragmentContainerResourceID();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResourceID());
        initializeComponents();
        hideActionBar();
    }

    public void updateViewVisibility(View view,int visibility){
        if (view == null)return;
        if (view.getVisibility() != visibility){
            view.setVisibility(visibility);
        }
    }

    public void hideActionBar(){
        getSupportActionBar().hide();
    }

    public FragmentManager getFragManager(){
        return getSupportFragmentManager();
    }

    public FragmentTransaction getFragTransaction(){
        return getFragManager().beginTransaction();
    }

    public void changeFragment(Fragment fragment,Bundle bundle,boolean isReplace,boolean backStack){
        if (fragment != null){
            FragmentTransaction fragmentTransaction = getFragTransaction();

            if (isReplace == true){
                fragmentTransaction.replace(getFragmentContainerResourceID(),fragment);
            } else {
                fragmentTransaction.add(getFragmentContainerResourceID(),fragment);
            }

            if (backStack == true){
                fragmentTransaction.addToBackStack(getClass().getSimpleName());
            }

            if (bundle != null){
                fragment.setArguments(bundle);
            }

            fragmentTransaction.commit();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int nightModeFlag = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_NO;
        if (nightModeFlag == Configuration.UI_MODE_NIGHT_NO){
            applyDayNight(OnDayNightStateListener.DAY);
        } else {
            applyDayNight(OnDayNightStateListener.NIGHT);
        }
    }

    public void applyDayNight(int state){
        if (state == OnDayNightStateListener.DAY){
            //apply day colors for your views
        } else {
            //apply night colors for your views
        }
    }

    @Override
    public void onClick(View view) {

    }
}
