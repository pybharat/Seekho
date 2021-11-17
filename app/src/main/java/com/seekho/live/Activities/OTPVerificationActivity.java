package com.seekho.live.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Models.Auth.OTPVerificationModel;
import com.seekho.live.R;
import com.seekho.live.Utils.Constant;
import com.seekho.live.Utils.Fun;
import com.seekho.live.WebBase.WebRequest;

import retrofit2.Response;

public class OTPVerificationActivity extends AppBaseActivity {

    EditText otp_no_1, otp_no_2, otp_no_3, otp_no_4, otp_no_5, otp_no_6;
    RelativeLayout otp_verified_rl;
    ImageView verified_iv;
    TextView duration_tv, mobile_no_tv, resend_otp_tv;
    ProgressBar progress_bar;

    String otp_string = "";
    String number_string = "";
    String user_id = "";
    String email_id = "";

    CountDownTimer countDownTimer;

    int type = 0;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_otp_verification;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        otp_no_1 = findViewById(R.id.otp_no_1);
        otp_no_2 = findViewById(R.id.otp_no_2);
        otp_no_3 = findViewById(R.id.otp_no_3);
        otp_no_4 = findViewById(R.id.otp_no_4);
        otp_no_5 = findViewById(R.id.otp_no_5);
        otp_no_6 = findViewById(R.id.otp_no_6);
        otp_verified_rl = findViewById(R.id.otp_verified_rl);
        verified_iv = findViewById(R.id.verified_iv);
        duration_tv = findViewById(R.id.duration_tv);
        mobile_no_tv = findViewById(R.id.mobile_no_tv);
        resend_otp_tv = findViewById(R.id.resend_otp_tv);
        progress_bar = findViewById(R.id.progress_bar);

        if (getIntent().getExtras() != null) {
            type = getIntent().getExtras().getInt(KEY_OTP_TYPE);
            otp_string = getIntent().getExtras().getString(KEY_OTP);
            user_id = getIntent().getExtras().getString(KEY_USER_ID);
            email_id = getIntent().getExtras().getString(KEY_EMAIL);

            mobile_no_tv.setText("Email " + email_id);
        }

        setAutoNextToEditText();
        setDuration();

        updateViewVisibility(progress_bar, View.VISIBLE);
        resend_otp_tv.setTextColor(getResources().getColor(R.color.grey_text));
        resend_otp_tv.setEnabled(false);
        resend_otp_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resend_otp_tv:
                if (type == 0) {
                    callResendApi();
                } else {
                    callResendForgetOTP();
                }
                break;
        }
    }

    private void callResendApi() {
        if (user_id != null) {
            JsonObject parameters = new JsonObject();
            parameters.addProperty(KEY_USER_ID, user_id);
            if (parameters == null) return;
            Fun.showLoader(this);
            makeWebRequest(this).getResendOTP(parameters, this);
        }
    }

    private void callResendForgetOTP() {
        if (email_id != null && email_id.length() > 0) {
            JsonObject parameters = new JsonObject();
            parameters.addProperty(KEY_EMAIL, email_id);
            if (parameters == null) return;
            Fun.showLoader(this);
            makeWebRequest(this).getResendForgetOTP(parameters, this);
        }
    }

    private CountDownTimer setDuration() {
        countDownTimer = new CountDownTimer(Constant.ONE_MIN_MILLI, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getTimeDuration(duration_tv, millisUntilFinished, true, "Sec");
            }

            @Override
            public void onFinish() {
                //setDuration();
                updateViewVisibility(progress_bar, View.GONE);
                duration_tv.setText("00:00 Sec");
                resend_otp_tv.setTextColor(getResources().getColor(R.color.light_blue));
                resend_otp_tv.setEnabled(true);
            }
        };
        countDownTimer.start();
        return countDownTimer;
    }

    private void setAutoNextToEditText() {
        otp_no_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 1) {
                    otp_no_2.requestFocus();
                }
            }
        });

        otp_no_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 1) {
                    otp_no_3.requestFocus();
                } else if (editable.toString().length() == 0) {
                    otp_no_1.requestFocus();
                }
            }
        });

        otp_no_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 1) {
                    otp_no_4.requestFocus();
                } else if (editable.toString().length() == 0) {
                    otp_no_2.requestFocus();
                }
            }
        });

        otp_no_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 1) {
                    otp_no_5.requestFocus();
                } else if (editable.toString().length() == 0) {
                    otp_no_3.requestFocus();
                }
            }
        });

        otp_no_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 1) {
                    otp_no_6.requestFocus();
                } else if (editable.toString().length() == 0) {
                    otp_no_4.requestFocus();
                }
            }
        });

        otp_no_6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 1) {
                    String otp = otp_no_1.getText().toString().trim() + "" +
                            otp_no_2.getText().toString().trim() + "" +
                            otp_no_3.getText().toString().trim() + "" +
                            otp_no_4.getText().toString().trim() + "" +
                            otp_no_5.getText().toString().trim() + "" +
                            otp_no_6.getText().toString().trim();
                    JsonObject parameters = new JsonObject();

                    if (type == 0) {
                        if (user_id != null && !user_id.equals("")) {
                            parameters.addProperty(KEY_USER_ID, user_id);
                            parameters.addProperty(KEY_OTP, otp);
                            if (parameters == null) return;
                            Fun.showLoader(OTPVerificationActivity.this);
                            makeWebRequest(OTPVerificationActivity.this).getVerifyOTP(parameters,
                                    OTPVerificationActivity.this);
                        }
                    } else {
                        if (email_id != null && !email_id.equals("")) {
                            parameters.addProperty(KEY_EMAIL, email_id);
                            parameters.addProperty(KEY_OTP, otp);
                            if (parameters == null) return;
                            Fun.showLoader(OTPVerificationActivity.this);
                            makeWebRequest(OTPVerificationActivity.this).getVerifyForgetOTP(parameters,
                                    OTPVerificationActivity.this);
                        }
                    }
                } else if (editable.toString().length() == 0) {
                    otp_no_5.requestFocus();
                }
            }
        });
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {
        if (response == null && response.code() == 400) return;
        if (response.code() == 200)
            Fun.finishLoader(OTPVerificationActivity.this);
        if (webRequest.getWebRequestID() == WEB_VERIFY_OTP_CODE) {
                handleVerificationResponse(response);
        } else if (webRequest.getWebRequestID() == WEB_RESEND_OTP_CODE) {
                handleResendResponse(response);
        } else if (webRequest.getWebRequestID() == WEB_VERIFY_FORGET_OTP_CODE) {
                handleVerifyForgetOTP(response);
        } else if (webRequest.getWebRequestID() == WEB_RESEND_FORGET_OTP_CODE) {
                handleResendForgetOTPResponse(response);
        }
    }

    private void handleResendForgetOTPResponse(Response response) {
        Object object = response.body();
        OTPVerificationModel otpData = (OTPVerificationModel) object;
        if (otpData.getCode() == 200) {
            showSimpleToast("An OTP has been send on " + email_id);
            updateViewVisibility(progress_bar, View.VISIBLE);
            resend_otp_tv.setTextColor(getResources().getColor(R.color.grey_text));
            resend_otp_tv.setEnabled(false);
            setDuration();
        } else {
            showSimpleToast(otpData.getMessage());
            Log.d(getClass().getSimpleName(), otpData.toString());
        }
    }

    private void handleResendResponse(Response response) {
        Object object = response.body();
        OTPVerificationModel otpData = (OTPVerificationModel) object;
        if (otpData.getCode() == 200) {
            showSimpleToast("An OTP has been send on " + email_id);
            updateViewVisibility(progress_bar, View.VISIBLE);
            resend_otp_tv.setTextColor(getResources().getColor(R.color.grey_text));
            resend_otp_tv.setEnabled(false);
            setDuration();
        } else {
            showSimpleToast(otpData.getMessage());
            Log.d(getClass().getSimpleName(), otpData.toString());
        }
    }

    private void handleVerificationResponse(Response response) {
        Object object = response.body();
        OTPVerificationModel otpData = (OTPVerificationModel) object;
        if (otpData.getCode() == 200) {
            hideKeyboardFrom(this, otp_no_6);
            otp_verified_rl.setVisibility(View.VISIBLE);
            setStatusBarColor(R.color.lime_green);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToLoginActivity(null);
                }
            }, 2000);
        } else {
            showSimpleToast(otpData.getMessage());
        }
    }

    private void handleVerifyForgetOTP(Response response) {
        Object object = response.body();
        OTPVerificationModel otpData = (OTPVerificationModel) object;
        if (otpData.getCode() == 200) {
            otp_verified_rl.setVisibility(View.VISIBLE);
            setStatusBarColor(R.color.lime_green);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_EMAIL, email_id);
                    bundle.putInt(KEY_OTP_TYPE, 1);
                    goToChangePasswordActivity(bundle);
                }
            }, 2000);
        } else {
            showSimpleToast(otpData.getMessage());
        }
    }

    @Override
    public void onWebFailure(Throwable throwable) {
        if (throwable == null) return;
        Fun.finishLoader(this);
        Log.d(getClass().getSimpleName(), throwable.getMessage());
        showSimpleToast(throwable.getMessage());
    }
}
