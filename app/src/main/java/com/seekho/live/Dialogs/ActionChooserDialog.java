package com.seekho.live.Dialogs;

import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.seekho.live.Activities.UpdateProfileActivity;
import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.R;
import com.seekho.live.Utils.Constant;
import com.seekho.live.Utils.DeviceScreenUtil;

public class ActionChooserDialog extends AppBaseDialogFragment {

    RelativeLayout camera_rl,gallery_rl;

    public static ActionChooserDialog getInstance(){
        return new ActionChooserDialog();
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_camera_and_image_picker;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null)return;

        camera_rl = getView().findViewById(R.id.camera_rl);
        gallery_rl = getView().findViewById(R.id.gallery_rl);

        camera_rl.setOnClickListener(this);
        gallery_rl.setOnClickListener(this);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        LayoutInflater inflate = LayoutInflater.from(getActivity());
        View layout = inflate.inflate(layoutResourceID(), null);
        dialog.setContentView(layout);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = DeviceScreenUtil.getInstance().getWidth(1f);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camera_rl:
                ((UpdateProfileActivity)getActivity()).pickCapturedImageFromCamera(Constant.PICK_CAPTURED_IMAGE_FROM_CAMERA_CODE);
                getDialog().dismiss();
                break;
            case R.id.gallery_rl:
                pickImagesFromGallery(Constant.PICK_IMAGE_FROM_GALLERY_CODE);
                getDialog().dismiss();
                break;
        }
    }
}
