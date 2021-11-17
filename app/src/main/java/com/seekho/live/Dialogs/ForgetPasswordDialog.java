package com.seekho.live.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.Models.Auth.ForgetPasswordModel;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public class ForgetPasswordDialog extends AppBaseDialogFragment {

    Context context;

    ImageView dismiss_iv;
    TextView sent_otp_btn_tv;

    EditText email_or_phone_et;

    public ForgetPasswordDialog(Context context) {
        this.context = context;
    }

    public ForgetPasswordDialog() {
    }

    public static ForgetPasswordDialog getInstance(Context context) {
        ForgetPasswordDialog forgetPasswordDialog = new ForgetPasswordDialog(context);
        return forgetPasswordDialog;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_forget_password;
    }


    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;
        dismiss_iv = getView().findViewById(R.id.dismiss_iv);
        sent_otp_btn_tv = getView().findViewById(R.id.sent_otp_btn_tv);
        email_or_phone_et = getView().findViewById(R.id.email_or_phone_et);

        dismiss_iv.setOnClickListener(this);
        sent_otp_btn_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.dismiss_iv:
                closeDialog();
                break;
            case R.id.sent_otp_btn_tv:
                callForgetPasswordApi();
                break;
        }
    }

    private void callForgetPasswordApi() {
        if (email_or_phone_et.getText().toString().isEmpty() ||
                email_or_phone_et.getText().toString().length() <= 0 ||
                !Patterns.EMAIL_ADDRESS.matcher(email_or_phone_et.getText().toString()).matches()) {
            email_or_phone_et.setError("Please Enter a valid email address!!!");
            return;
        } else {
            JsonObject parameters = new JsonObject();
            parameters.addProperty(KEY_EMAIL, email_or_phone_et.getText().toString().trim());
            if (parameters == null) return;
            Fun.showLoader(getActivity());
            makeWebRequest(getActivity()).getForgetPassword(parameters, this);
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() == 400) return;
        if (response.code() == 200)
            Fun.finishLoader(getActivity());
        if (webRequest.getWebRequestID() == WEB_FORGET_PASSWORD_CODE) {
            handleForgetPasswordResponse(response);
        }
    }

    private void handleForgetPasswordResponse(Response response) {
        Object object = response.body();
        ForgetPasswordModel forgetData = (ForgetPasswordModel) object;
        if (forgetData.getCode() == 200) {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_EMAIL, email_or_phone_et.getText().toString());
            bundle.putInt(KEY_OTP_TYPE, 1);
            goToOTPVerificationActivity(bundle);
            getDialog().dismiss();
        } else {
            showSimpleToast(forgetData.getMessage());
            getDialog().dismiss();
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(getActivity());
        Log.d(getClass().getSimpleName(), throwable.getMessage());
    }
}
