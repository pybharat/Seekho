package com.seekho.live.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Enroll.EnrollDataModel;
import com.seekho.live.Models.UserProfile.UserInfoDataModel;
import com.seekho.live.Models.UserProfile.UserInfoModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Constant;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class UpdateProfileActivity extends AppBaseActivity {

    TextView update_acc_btn_tv;
    EditText first_name_et, last_name_et;
    CircleImageView profile_iv;
    ProgressBar profile_iv_progress;

    Bitmap profile_bitmap;

    String first_name = "";
    String last_name = "";
    String profile_image = "";
    Uri capturedImagedUri;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_update_profile;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        update_acc_btn_tv = findViewById(R.id.update_acc_btn_tv);
        first_name_et = findViewById(R.id.first_name_et);
        last_name_et = findViewById(R.id.last_name_et);
        profile_iv = findViewById(R.id.profile_iv);
        profile_iv_progress = findViewById(R.id.profile_iv_progress);

        if (getIntent().getExtras() != null) {
            first_name = getIntent().getExtras().getString(KEY_FIRST_NAME);
            last_name = getIntent().getExtras().getString(KEY_LAST_NAME);
            profile_image = getIntent().getExtras().getString(KEY_IMG);

            first_name_et.setText(first_name);
            if (last_name != null && last_name.length() > 0 && !last_name.equals("")) {
                last_name_et.setText(last_name);
            }

            if (profile_image != null && profile_image.length() > 0 && !profile_image.equals("")) {
                Glide.with(this)
                        .load(profile_image)
                        .into(profile_iv);
            } else {
                Glide.with(this)
                        .load(R.drawable.bg_no_profile)
                        .into(profile_iv);
            }
        }

        update_acc_btn_tv.setOnClickListener(this);
        profile_iv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_acc_btn_tv:
                callUpdateUserProfileApi();
                break;
            case R.id.profile_iv:
                if (isPermissionsGranted(this)) {
                    getActionChooserDialog(this, null);
                } else {
                    getPermissionsInAndroid11OrBelowOrAbove();
                }
                break;
        }
    }

    private void callUpdateUserProfileApi() {
        if (first_name_et == null || first_name_et.getText().toString().isEmpty()) {
            first_name_et.setError("Please enter your first name");
            return;
        } else if (last_name_et == null || last_name_et.getText().toString().isEmpty()) {
            last_name_et.setError("Please enter your last name");
            return;
        } else {
            String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
            String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

            JsonObject authParameter = new JsonObject();
            JsonObject subParameter = new JsonObject();
            if (user_id != null && !user_id.isEmpty() && !user_id.equals("") &&
                    token != null && !token.isEmpty() && !token.equals("")) {
                authParameter.addProperty(KEY_USER_ID, user_id);
                authParameter.addProperty(KEY_FIRST_NAME, first_name_et.getText().toString());
                authParameter.addProperty(KEY_LAST_NAME, last_name_et.getText().toString());
                subParameter.addProperty(KEY_ID, user_id);
                subParameter.addProperty(KEY_TOKEN, token);
                authParameter.add(KEY_AUTH, subParameter);
                if (authParameter == null) return;
                Fun.showLoader(this);
                makeWebRequest(this).getUpdateUserProfile(authParameter, this);
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() > 200 && response.code() >= 400) return;
        if (response.code() == 200)
            if (webRequest.getWebRequestID() == WEB_UPDATE_USER_PROFILE_CODE) {
                handleUpdatedUserData(response);
            } else if (webRequest.getWebRequestID() == WEB_UPDATE_PROFILE_IMAGE_CODE) {
                handleUpdateProfileImageResponse(response);
            }
    }

    private void handleUpdateProfileImageResponse(Response response) {
        Object object = response.body();
        EnrollDataModel profileData = (EnrollDataModel) object;
        if (profileData.getCode() == 200) {
            profile_iv.setEnabled(true);
            updateViewVisibility(profile_iv_progress, View.GONE);
            finish();
            showSimpleToast("Profile picture updated successfully");
        } else {
            return;
        }
    }

    private void handleUpdatedUserData(Response response) {
        Object object = response.body();
        UserInfoModel userInfo = (UserInfoModel) object;
        if (userInfo.getCode() == 200) {
            Fun.finishLoader(this);
            UserInfoDataModel userData = userInfo.getMessage().getUserdet();
            if (userData != null) {
                Pref.setUserDataPref(this, userData);
                finish();
            }
            showSimpleToast("Profile Updated Successfully!!!");
        } else {
            return;
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        showSimpleToast(throwable.getMessage());
        if (throwable.getMessage().equals("timeout")) {
            updateViewVisibility(profile_iv_progress, View.GONE);
            profile_iv.setEnabled(true);
        }
    }

    //---------------------------- Profile Picture Setup -----------------------------------------

    private String compressedIntoBase64() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        profile_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    private void getPermissionsInAndroid11OrBelowOrAbove() {
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
//            try {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                intent.addCategory("android.intent.category.DEFAULT");
//                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
//                startActivityForResult(intent, Constant.STORAGE_AND_ALL_FILES_ACCESS_REQ_CODE);
//            } catch (Exception exception) {
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
//                startActivityForResult(intent, Constant.STORAGE_AND_ALL_FILES_ACCESS_REQ_CODE);
//            }
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.CAMERA}, Constant.STORAGE_AND_CAMERA_PERMISSION_REQ_CODE);
//        }
        if (!isPermissionsGranted(this)){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA}, Constant.STORAGE_AND_CAMERA_PERMISSION_REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            if (requestCode == Constant.STORAGE_AND_ALL_FILES_ACCESS_REQ_CODE) {
//                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
//                    if (Environment.isExternalStorageManager()) {
//                        getActionChooserDialog(this, null);
//                    } else {
//                        showSimpleToast("Permission Denied!!!");
//                    }
//                }
//            } else
            if (requestCode == Constant.PICK_IMAGE_FROM_GALLERY_CODE) {
                if (data != null) {
                    CropImage.activity(data.getData())
                            .setCropShape(CropImageView.CropShape.OVAL)
                            .start(this);
                }
            } else if (requestCode == Constant.PICK_CAPTURED_IMAGE_FROM_CAMERA_CODE) {
                CropImage.activity(capturedImagedUri)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .start(this);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri uri = result.getUri();
                    if (uri != null) {
                        try {
                            profile_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            Glide.with(this).load(uri).into(profile_iv);
                            updateViewVisibility(profile_iv_progress, View.VISIBLE);

                            callUpdateProfileImageApi();
                            profile_iv.setEnabled(false);
                            updateViewVisibility(profile_iv_progress, View.VISIBLE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void callUpdateProfileImageApi() {
        String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
        String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);
        String profile_base64 = compressedIntoBase64();

        JsonObject authParameters = new JsonObject();
        JsonObject subParameters = new JsonObject();
        if (user_id != null && user_id.length() > 0 && token != null && token.length() > 0) {
            if (profile_base64 != null && !profile_base64.isEmpty()) {
                authParameters.addProperty(KEY_USER_ID, user_id);
                authParameters.addProperty(KEY_IMG, profile_base64);
                subParameters.addProperty(KEY_ID, user_id);
                subParameters.addProperty(KEY_TOKEN, token);
                authParameters.add(KEY_AUTH, subParameters);
                if (authParameters == null) return;
                makeWebRequest(this).getUpdateProfileImage(authParameters, this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.STORAGE_AND_CAMERA_PERMISSION_REQ_CODE:
                if (grantResults.length > 0) {
                    boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (readExternalStorage && cameraPermission) {
                        getActionChooserDialog(this, null);
                    } else {
                        getPermissionsInAndroid11OrBelowOrAbove();
                    }
                }
                break;
        }
    }

    public void pickCapturedImageFromCamera(int requestCode) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(getExternalFilesDir(null), "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            capturedImagedUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImagedUri);
        } else {
            capturedImagedUri = Uri.fromFile(file);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImagedUri);
        }
        cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, requestCode);
        }
    }
}
