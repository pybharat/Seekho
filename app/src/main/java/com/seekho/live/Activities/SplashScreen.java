package com.seekho.live.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.seekho.live.Interfaces.Constants;
import com.seekho.live.Preferences.Pref;
import com.seekho.live.R;
import com.seekho.live.WebBase.WebContants;

public class SplashScreen extends AppCompatActivity {

    ImageView image_view;
    String verify = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFullScreen();
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        image_view = findViewById(R.id.image_view);
        startBounceAnim(image_view);

        if (Pref.getValueFromPref(this,Constants.USER_INFO_PREF, WebContants.KEY_VERIFY) != null){
            verify = Pref.getValueFromPref(this,Constants.USER_INFO_PREF, WebContants.KEY_VERIFY);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (verify.equals("") || verify.equals("0")){
                    goToLoginScreen(null);
                } else if (verify.equals("1")){
                    goToDashboardActivity(null);
                }
            }
        },3000);
    }

    public void startBounceAnim(View view){
        if (view != null){
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_bounce);
            view.startAnimation(animation);
        }
    }

    public void getFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void goToLoginScreen(Bundle bundle){
        Intent intent = new Intent(this, LoginActivity.class);
        if (bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivity(intent);
        finish();
    }

    public void goToDashboardActivity(Bundle bundle) {
        Intent intent = new Intent(this, DashboardActivity.class);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
        finish();
    }
}