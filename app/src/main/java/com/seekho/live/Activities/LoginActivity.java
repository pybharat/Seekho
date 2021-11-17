package com.seekho.live.Activities;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Auth.LoginDataModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public class LoginActivity extends AppBaseActivity {

    TextView sign_up_tv, forget_password_tv, login_btn_tv;
    EditText email_or_phone_et, password_et;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_login;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        sign_up_tv = findViewById(R.id.sign_up_tv);
        forget_password_tv = findViewById(R.id.forget_password_tv);
        login_btn_tv = findViewById(R.id.login_btn_tv);
        email_or_phone_et = findViewById(R.id.email_or_phone_et);
        password_et = findViewById(R.id.password_et);

        sign_up_tv.setOnClickListener(this);
        forget_password_tv.setOnClickListener(this);
        login_btn_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_tv:
                goToRegisterActivity(null);
                break;
            case R.id.forget_password_tv:
                getForgetPassDialog(this, null);
                break;
            case R.id.login_btn_tv:
                callLoginApi();
                break;
        }
    }

    private void callLoginApi() {
        if (email_or_phone_et.getText().toString().isEmpty()) {
            email_or_phone_et.setError("Please enter a valid email or 10 digit mobile number");
            return;
        } else if (password_et.getText().toString().isEmpty()
                || password_et.getText().toString().length() < 6) {
            password_et.setError("Please enter a valid 6 digit password");
            return;
        } else {
            Fun.showLoader(this);
            JsonObject parameters = new JsonObject();
            parameters.addProperty(KEY_EMAIL, email_or_phone_et.getText().toString().trim());
            parameters.addProperty(KEY_PASSWORD, password_et.getText().toString().trim());
            if (parameters == null) return;
            makeWebRequest(this).getLogin(parameters, this);
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() == 400) return;
        if (response.code() == 200)
            Fun.finishLoader(this);
            Log.d(getClass().getSimpleName(), response.body().toString());
        if (webRequest.getWebRequestID() == WEB_LOGIN_CODE) {
            handleLogin(response);
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        Log.d(getClass().getSimpleName() + "Error: ", throwable.getMessage());
    }

    private void handleLogin(Response response) {
        Object object = response.body();
        LoginDataModel loginData = (LoginDataModel) object;
        if (loginData != null && loginData.getCode() == 200) {

            Pref.getPrefEditor(this, USER_INFO_PREF, KEY_USER_ID, loginData.getUserid());
            Pref.getPrefEditor(this, USER_INFO_PREF, KEY_TOKEN, loginData.getToken());
            Pref.getPrefEditor(this, USER_INFO_PREF, KEY_VERIFY, loginData.getVerify());

            if (loginData.getVerify().equals("0")) {
                goToOTPVerificationActivity(null);
            } else if (loginData.getVerify().equals("1")) {
                goToDashboardActivity(null);
            }
        } else if (loginData.getCode() > 200 || loginData.getCode() == 400) {
            showSimpleToast(loginData.getMessage());
            Log.d(getClass().getSimpleName(), loginData.getMessage());
        }
    }
}
