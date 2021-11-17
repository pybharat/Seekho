package com.seekho.live.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;
import com.seekho.live.Dialogs.ProgressDialog;
import com.seekho.live.R;

public class Fun {

    public static final int SHOW_SIMPLE_MESSAGE = 101;
    //public static final int SHOW_PICKER = 102;

    private static ProgressDialog progressDialog;

    public static ProgressDialog showLoader(Context context) {
        progressDialog = ProgressDialog.getInstance(context);
        if (progressDialog != null)
            progressDialog.show(getFragmentManager(context), progressDialog.getClass().getSimpleName());
        return progressDialog;
    }

    public static void finishLoader(Context context) {
        if (context == null) return;
        if (progressDialog == null) return;
        progressDialog.dismiss();
    }

    public static FragmentManager getFragmentManager(Context context) {
        return ((AppCompatActivity) context).getSupportFragmentManager();
    }

    public static View getView(Context context) {
        return ((AppCompatActivity) context).getWindow().getDecorView().getRootView();
    }

    public static void showSnackBar(Context context, int type, String input, int anchorView, int forDuration) {
        Snackbar snackbar = null;
        switch (type) {
            case SHOW_SIMPLE_MESSAGE:
                if (input.length() > 0)
                    snackbar = Snackbar.make(getView(context), input, forDuration);
                break;
//            case SHOW_PICKER:
//                snackbar = showCameraAndImagePicker(context,anchorView,forDuration);
//                break;
        }
        if (anchorView > 0) {
            snackbar.setAnchorView(anchorView);
        }
        snackbar.show();
    }

    private static Snackbar showCameraAndImagePicker(Context context,int anchorView, int forDuration) {
        Snackbar snackbar = null;
        if (context != null){
            snackbar = Snackbar.make(getView(context), "", forDuration);
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_camera_and_image_picker,null);
            if (view != null) {
                layout.setPadding(0,0,0,150);
                layout.addView(view);
            }
        }
        return snackbar;
    }
}
