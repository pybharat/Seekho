package com.seekho.live.Activities;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Auth.ResetPasswordModel;
import com.seekho.live.Models.UserProfile.UpdatePasswordModel;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public class UpdatePasswordActivity extends AppBaseActivity {

    LinearLayout change_pass_ll, reset_pass_ll;

    //-------------------------- ResetPassword --------------------
    TextView mobile_or_email_tv;
    EditText reset_password_et, reset_confirm_password_et;
    TextView change_pass_btn_tv;

    //-------------------------- UpdatePassword --------------------
    EditText password_et, confirm_password_et;

    String email = "";
    int type = 0;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_change_password;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        change_pass_ll = findViewById(R.id.change_pass_ll);
        reset_pass_ll = findViewById(R.id.reset_pass_ll);
        mobile_or_email_tv = findViewById(R.id.mobile_or_email_tv);
        reset_password_et = findViewById(R.id.reset_password_et);
        reset_confirm_password_et = findViewById(R.id.reset_confirm_password_et);
        change_pass_btn_tv = findViewById(R.id.change_pass_btn_tv);
        password_et = findViewById(R.id.password_et);
        confirm_password_et = findViewById(R.id.confirm_password_et);

        if (getIntent().getExtras() != null) {
            email = getIntent().getExtras().getString(KEY_EMAIL);
            type = getIntent().getExtras().getInt(KEY_OTP_TYPE);

            if (type == 0) {
                updateViewVisibility(reset_pass_ll, View.GONE);
                updateViewVisibility(change_pass_ll, View.VISIBLE);
            } else {
                updateViewVisibility(reset_pass_ll, View.VISIBLE);
                updateViewVisibility(change_pass_ll, View.GONE);
            }

            if (email != null && email.length() > 0) {
                mobile_or_email_tv.setText("Email " + email);
            }
        }

        change_pass_btn_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_pass_btn_tv:
                if (type == 0) {
                    validateUpdatePassword();
                } else {
                    validateResetPassword();
                }
                break;
        }
    }

    private void validateUpdatePassword() {
        if (password_et.getText().toString().length() <= 8 || password_et.getText().toString().isEmpty()) {
            password_et.setError("Please enter a valid 8 digit password");
            return;
        } else if (!confirm_password_et.getText().toString().matches(confirm_password_et.getText().toString()) ||
                confirm_password_et.getText().toString().isEmpty()) {
            confirm_password_et.setError("Password does match");
            return;
        } else {
            String user_id = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_USER_ID);
            String token = Pref.getValueFromPref(this, USER_INFO_PREF, KEY_TOKEN);

            if (user_id != null && !user_id.isEmpty() && token != null && !token.isEmpty()) {
                JsonObject authParameter = new JsonObject();
                JsonObject subParameter = new JsonObject();
                authParameter.addProperty(KEY_USER_ID, user_id);
                authParameter.addProperty(KEY_PASSWORD, password_et.getText().toString().trim());
                subParameter.addProperty(KEY_ID, user_id);
                subParameter.addProperty(KEY_TOKEN, token);
                authParameter.add(KEY_AUTH, subParameter);
                if (authParameter == null) return;
                Fun.showLoader(this);
                makeWebRequest(this).getUpdateUserPassword(authParameter, this);
            }
        }
    }

    private void validateResetPassword() {
        if (reset_password_et == null ||
                reset_password_et.getText().toString().length() < 8) {
            reset_password_et.setError("Please enter a valid 8 digit password");
            return;
        } else if (reset_confirm_password_et == null ||
                !reset_confirm_password_et.getText().toString().matches(reset_password_et.getText().toString())) {
            reset_confirm_password_et.setError("Password does not match");
            return;
        } else {
            JsonObject parameters = new JsonObject();
            if (type == 0) {

            } else {
                if (email != null && email.length() > 0) {
                    parameters.addProperty(KEY_EMAIL, email);
                    parameters.addProperty(KEY_PASSWORD, reset_password_et.getText().toString().trim());
                    if (parameters == null) return;
                    Fun.showLoader(this);
                    makeWebRequest(this).getResetPassword(parameters, this);
                }
            }
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() == 400) return;
        if (response.code() == 200)
            Fun.finishLoader(this);
        if (webRequest.getWebRequestID() == WEB_RESET_PASSWORD_CODE) {
                handleResetPasswordResponse(response);
        } else if (webRequest.getWebRequestID() == WEB_UPDATE_USER_PASSWORD_CODE) {
                handleUpdateUserPasswordResponse(response);
        }
    }

    private void handleUpdateUserPasswordResponse(Response response) {
        Object object = response.body();
        UpdatePasswordModel passwordData = (UpdatePasswordModel) object;
        if (passwordData.getCode() == 200) {
            showSimpleToast(passwordData.getMessage());
            Pref.clearPref(this, USER_INFO_PREF);
            goToLoginActivity(null);
        } else {
            return;
        }
    }

    private void handleResetPasswordResponse(Response response) {
        Object object = response.body();
        ResetPasswordModel resetData = (ResetPasswordModel) object;
        if (resetData.getCode() == 200) {
            Log.d(getClass().getSimpleName(), resetData.toString());
            showSimpleToast(resetData.getMessage());
            goToLoginActivity(null);
        } else {
            showSimpleToast(resetData.getMessage());
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        Log.d(getClass().getSimpleName(), throwable.getMessage());
    }
}
