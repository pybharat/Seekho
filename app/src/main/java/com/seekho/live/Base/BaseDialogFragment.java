package com.seekho.live.Base;

//Created by Mohammed Faisal 15 March 2021

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.seekho.live.Utils.DeviceScreenUtil;

public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener {

    View view;

    public abstract int layoutResourceID();

    public abstract void initializeComponents();

    public int getFragmentContainerResourceID(){
        return getFragmentContainerResourceID();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutResourceID(),container,false);
        initializeComponents();
        return view;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        LayoutInflater inflate = LayoutInflater.from(getActivity());
        View layout = inflate.inflate(layoutResourceID(), null);

        //set dialog_country view
        dialog.setContentView(layout);
        //setup dialog_country window param
        WindowManager.LayoutParams wlmp = dialog.getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER;
        wlmp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //wlmp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlmp.width = DeviceScreenUtil.getInstance().getWidth(0.95f);

        dialog.setTitle(null);
        setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public FragmentManager getFragManager(){
        return getActivity().getSupportFragmentManager();
    }

    public FragmentTransaction getFragTransaction(){
        return getFragManager().beginTransaction();
    }

    public void changeFragment(Fragment fragment, Bundle bundle, boolean isReplace, boolean backStack){
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

    public void closeDialog(){
        getDialog().dismiss();
    }

    @Override
    public void onClick(View view) {

    }
}
