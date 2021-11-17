package com.seekho.live.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.R;

public class ProgressDialog extends AppBaseDialogFragment {

    Context context;
    ImageView image_view;

    public static ProgressDialog getInstance(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        return progressDialog;
    }

    public ProgressDialog(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_progress;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.dimAmount = 0.8f;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //dialog.onBackPressed();
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null)return;
        image_view = getView().findViewById(R.id.image_view);
        //startRotateAnim(image_view);
        startZoomInZoomOutAnim(image_view);
    }
}
