package com.seekho.live.Base;

//Created by Mohammed Faisal 15 March 2021

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    View view;

    public abstract int layoutResourceID();

    public abstract void initializeComponents();

    public int getFragmentContainerResourceID(){
        return getFragmentContainerResourceID();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        view = inflater.inflate(layoutResourceID(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public FragmentManager getFragManager(){
        return getChildFragmentManager();
    }

    public FragmentTransaction getFragTransaction(){
        return getFragManager().beginTransaction();
    }

    public BaseFragment getLatestFragment(int resID){
        if (getFragManager() != null){
            Fragment fragment = getFragManager().findFragmentById(resID);
            if (fragment instanceof BaseFragment){
                return ((BaseFragment)fragment);
            }
        }
        return null;
    }

    public void clearFragmentBackStack(int pos) {
        if (getFragManager().getBackStackEntryCount() > pos) {
            try {
                getFragManager().popBackStack(getFragManager().getBackStackEntryAt(pos).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } catch (IllegalStateException e) {
            }
        }
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
                fragmentTransaction.addToBackStack(null);
            } else {
                fragmentTransaction.addToBackStack(getClass().getSimpleName());
            }

            if (bundle != null){
                fragment.setArguments(bundle);
            }

            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {

    }
}
