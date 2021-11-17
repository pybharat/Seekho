package com.seekho.live.Activities;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Auth.RegisterDataModel;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public class RegisterActivity extends AppBaseActivity {

    TextView create_acc_btn_tv, update_acc_btn_tv;
    EditText name_et, email_et, phone_et, password_et, confirm_password_et;
    CheckBox accept_cb;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_register;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        create_acc_btn_tv = findViewById(R.id.create_acc_btn_tv);
        update_acc_btn_tv = findViewById(R.id.update_acc_btn_tv);
        name_et = findViewById(R.id.name_et);
        email_et = findViewById(R.id.email_et);
        phone_et = findViewById(R.id.phone_et);
        password_et = findViewById(R.id.password_et);
        confirm_password_et = findViewById(R.id.confirm_password_et);
        accept_cb = findViewById(R.id.accept_cb);

        create_acc_btn_tv.setOnClickListener(this);
        accept_cb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.create_acc_btn_tv:
                validate();
                break;
        }
    }

    private void validate() {
        if (name_et.getText().toString().isEmpty() || name_et.getText().toString().length() <= 0) {
            name_et.setError("Please Enter Your Full Name Here!!!");
            return;
        }

        if (email_et.getText().toString().isEmpty() || email_et.getText().toString().length() == 0
                || !Patterns.EMAIL_ADDRESS.matcher(email_et.getText().toString()).matches()) {
            email_et.setError("Please Enter a valid email address");
            return;
        }

        if (phone_et.getText().toString().isEmpty() || phone_et.getText().toString().length() < 10
                || !Patterns.PHONE.matcher(phone_et.getText().toString()).matches()) {
            phone_et.setError("Please Enter a valid 10 digit phone number");
            return;
        }

        if (password_et.getText().toString().isEmpty() || password_et.getText().toString().length() < 8) {
            password_et.setError("Please Enter a valid 8 digit password");
            return;
        }

        if (confirm_password_et.getText().toString().isEmpty() ||
                !confirm_password_et.getText().toString().matches(password_et.getText().toString())) {
            confirm_password_et.setError("Password does not match please check");
            return;
        }

        if (!accept_cb.isChecked()) {
            showSimpleToast("Please accept terms and conditions");
            return;
        } else {
            callRegisterApi();
        }
    }

    private void callRegisterApi() {
        JsonObject parameters = new JsonObject();
        parameters.addProperty(KEY_EMAIL, email_et.getText().toString().trim());
        parameters.addProperty(KEY_PASSWORD, password_et.getText().toString().trim());
        parameters.addProperty(KEY_NAME, name_et.getText().toString().trim());
        parameters.addProperty(KEY_NUMBER, phone_et.getText().toString().trim());
        if (parameters == null) return;
        Fun.showLoader(this);
        makeWebRequest(this).getRegister(parameters, this);
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() == 400) return;
        if (response.code() == 200)
        Fun.finishLoader(this);
        if (webRequest.getWebRequestID() == WEB_REGISTER_CODE) {
            handleRegisterResponse(response);
        }
    }

    private void handleRegisterResponse(Response response) {
        Object object = response.body();
        RegisterDataModel registerData = (RegisterDataModel) object;
        if (registerData != null && registerData.getCode() == 200) {
            Log.d(getClass().getSimpleName(), registerData.toString());
            Bundle bundle = new Bundle();
            bundle.putString(KEY_OTP, registerData.getOtp());
            bundle.putString(KEY_USER_ID, registerData.getUserid());
            bundle.putString(KEY_EMAIL, email_et.getText().toString());
            bundle.putInt(KEY_OTP_TYPE, 0);
            goToOTPVerificationActivity(bundle);
        } else {
            showSimpleToast(registerData.getMessage());
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        Log.d(getClass().getSimpleName(), throwable.getMessage());
    }
}
