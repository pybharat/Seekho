package com.seekho.live.AppBase;

//Created by Mohammed Faisal 15 March 2021

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.seekho.live.Activities.AllMainCoursesActivity;
import com.seekho.live.Activities.DashboardActivity;
import com.seekho.live.Activities.LecturesAndAccompayingMCQActivity;
import com.seekho.live.Activities.LoginActivity;
import com.seekho.live.Activities.MockTestsActivity;
import com.seekho.live.Activities.OTPVerificationActivity;
import com.seekho.live.Activities.RegisterActivity;
import com.seekho.live.Activities.SubjectsLectureActivity;
import com.seekho.live.Activities.UpdatePasswordActivity;
import com.seekho.live.Activities.WebViewActivity;
import com.seekho.live.Base.BaseActivity;
import com.seekho.live.BaseToolsFragment.BottomNavigationFragment;
import com.seekho.live.BaseToolsFragment.NavigationFragment;
import com.seekho.live.BaseToolsFragment.ToolbarFragment;
import com.seekho.live.Dialogs.ActionChooserDialog;
import com.seekho.live.Dialogs.ForgetPasswordDialog;
import com.seekho.live.Dialogs.MockTestsDialog;
import com.seekho.live.Dialogs.QuizAleartDialog;
import com.seekho.live.Dialogs.QuizSummaryDialog;
import com.seekho.live.Interfaces.Constants;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Interfaces.ToolbarListener;
import com.seekho.live.Interfaces.UserListener;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.Courses.SubCourses.SubCategoryData;
import com.seekho.live.Models.Quiz.QuizSummaryModel;
import com.seekho.live.Models.UserProfile.UserInfoDataModel;
import com.seekho.live.R;
import com.seekho.live.RecyclerViews.CoursesRV;
import com.seekho.live.RecyclerViews.PromotionalRV;
import com.seekho.live.RecyclerViews.QuizSummaryRV;
import com.seekho.live.RecyclerViews.SubCoursesRV;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebListener;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

public abstract class AppBaseActivity extends BaseActivity implements ToolbarFragment.OnToolbarClickListener,
        BottomNavigationFragment.OnBottomNavigationListener, ToolbarListener, Constants, WebContants, WebListener,
        CompoundButton.OnCheckedChangeListener, ToolbarFragment.SearchListener, ViewPager.OnPageChangeListener,
        SwipeRefreshLayout.OnRefreshListener, OnRecyclerListener, UserListener {

    ToolbarFragment toolbarFragment;
    BottomNavigationFragment bottomNavigationFragment;
    NavigationFragment navigationFragment;

    @Override
    public void initializeComponents() {
        setStatusBarColor(R.color.navi_blue);

        Fragment fragment_toolbar = getFragManager().findFragmentById(R.id.toolbar_fragment_id);
        if (fragment_toolbar instanceof AppBaseFragment) {
            toolbarFragment = (ToolbarFragment) fragment_toolbar;
            toolbarFragment.initializeComponents();
        }

        Fragment bottom_fragment = getFragManager().findFragmentById(R.id.bottom_navifation_fragment_id);
        if (bottom_fragment instanceof AppBaseFragment) {
            bottomNavigationFragment = (BottomNavigationFragment) bottom_fragment;
            bottomNavigationFragment.initializeComponents();
        }

        Fragment navigation_fragment = getFragManager().findFragmentById(R.id.navigation_fragment_id);
        if (navigation_fragment instanceof AppBaseFragment) {
            navigationFragment = (NavigationFragment) navigation_fragment;
            navigationFragment.initializeComponents();
        }
    }

    public BottomNavigationFragment getBottomNavigationFragment() {
        return bottomNavigationFragment;
    }

    @Override
    public void onUserDataChanged(UserInfoDataModel userData) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onRefresh() {

    }

    Lifecycle lifecycle = new Lifecycle() {
        @Override
        public void addObserver(@NonNull LifecycleObserver observer) {

        }

        @Override
        public void removeObserver(@NonNull LifecycleObserver observer) {

        }

        @NonNull
        @Override
        public State getCurrentState() {
            return null;
        }
    };

    public ToolbarFragment getToolbarFragment() {
        return toolbarFragment;
    }

    public ForgetPasswordDialog getForgetPassDialog(Context context, Bundle bundle) {
        ForgetPasswordDialog dialog = ForgetPasswordDialog.getInstance(context);
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(), dialog.getClass().getSimpleName());
        return dialog;
    }

    public QuizSummaryDialog getQuizSummaryDialog(Context context, Bundle bundle) {
        QuizSummaryDialog dialog = QuizSummaryDialog.getInstance(context);
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(), dialog.getClass().getSimpleName());
        return dialog;
    }

    public QuizAleartDialog getQuizAleartDialog(Context context, Bundle bundle) {
        QuizAleartDialog dialog = QuizAleartDialog.getInstance(context);
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(), dialog.getClass().getSimpleName());
        return dialog;
    }

    public MockTestsDialog getMockTestsDialog(Context context, Bundle bundle) {
        MockTestsDialog dialog = MockTestsDialog.getInstance(context);
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(), dialog.getClass().getSimpleName());
        return dialog;
    }

    public AlertDialog getConfirmationDialog(Context context, Bundle bundle) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_confirmation_aleart, null);
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(view);

        view.findViewById(R.id.continue_test_btn_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.submit_cv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bundle != null) {
                    getQuizSummaryDialog(context, bundle);
                }

                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        return dialog;
    }

    public void goToLoginActivity(Bundle bundle) {
        Intent intent = new Intent(this, LoginActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    public void goToChangePasswordActivity(Bundle bundle) {
        Intent intent = new Intent(this, UpdatePasswordActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    public void goToRegisterActivity(Bundle bundle) {
        Intent intent = new Intent(this, RegisterActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToDashboardActivity(Bundle bundle) {
        Intent intent = new Intent(this, DashboardActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    public void goToLecAndAccPayMCQActivity(Bundle bundle) {
        Intent intent = new Intent(this, LecturesAndAccompayingMCQActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToMockTestsActivity(Bundle bundle) {
        Intent intent = new Intent(this, MockTestsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToWebViewActivity(Bundle bundle) {
        Intent intent = new Intent(this, WebViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToOTPVerificationActivity(Bundle bundle) {
        Intent intent = new Intent(this, OTPVerificationActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToSubjectsLecturesActivity(Bundle bundle) {
        Intent intent = new Intent(this, SubjectsLectureActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToAllMainCoursesActivity(Bundle bundle) {
        Intent intent = new Intent(this, AllMainCoursesActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startZoomInZoomOutAnim(View view) {
        if (view != null) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_zoom_in_zoom_out);
            view.startAnimation(animation);
        }
    }

    public void startRotateAnim(View view) {
        if (view != null) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
            view.startAnimation(animation);
        }
    }

    public void setStatusBarColor(int colorCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(colorCode));
        }
    }

    public int isPhoneOrEmail(EditText editText) {
        String string = editText.getText().toString();
        if (string.matches(EMAIL_PATTERN)) {
            return 0;
        } else if (string.matches(PHONE_PATTERN)) {
            return 1;
        }
        return -1;
    }

    public boolean isValidEmail(EditText editText) {
        String input = editText.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPhoneNumber(EditText editText) {
        String input = editText.getText().toString();
        if (Patterns.PHONE.matcher(input).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public int isEmailOrPhone(EditText editText) {
        String input_type = editText.getText().toString();
        if (Patterns.EMAIL_ADDRESS.matcher(input_type).matches()) {
            return 0;
        } else if (Patterns.PHONE.matcher(input_type).matches()) {
            return 1;
        }
        return -1;
    }

    public void hideKeyBoard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //------------------------------------- Creating Toast -----------------------------------------
    public void showSimpleToast(String msg) {
        if (msg != null && !msg.isEmpty()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    //--------------------------- Toolbar Operations ------------------------------------

    @Override
    public void onToolbarClick(View view) {
        switch (view.getId()) {
            case R.id.menu_iv:
                navigationFragment.handleDrawer();
                break;
        }
    }

    @Override
    public void onTitleChange(String title) {

    }

    //--------------------------- Bottom Navigation Operations ------------------------------------

    @Override
    public void onBottomItemClick(View view) {
//        Fragment fragment = null;
//        switch (view.getId()) {
//            case R.id.home_rl:
//                fragment = new HomeFragment();
//                break;
//            case R.id.mock_tests_rl:
//                fragment = new MockTestsFragment();
//                break;
//            case R.id.profile_rl:
//                fragment = new ProfileFragment();
//                break;
//        }
//        changeFragment(fragment, null, true, false);
    }

    //------------------------------------ Web Listeners ------------------------------------------

    public WebRequest makeWebRequest(Context context) {
        WebRequest webRequest = new WebRequest(context);
        if (webRequest != null) {
            return webRequest;
        } else {
            return null;
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest, Response response) {

    }

    @Override
    public void onWebFailure(Throwable throwable) {

    }

    //------------------------------------ OnCheckedListener --------------------------------------

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    //----------------------------------- Time Conversion -----------------------------------------
    public void getTimeDuration(TextView textView, long millis, boolean isSomething, String input) {
        if (textView == null) return;
        String duration = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        if (isSomething) {
            textView.setText(duration + " " + input);
        } else {
            textView.setText("Time left:  " + duration);
        }
    }

    //----------------------------------- RecyclerViews -------------------------------------------
    public SubCoursesRV setSubCoursesRV(Context context, RecyclerView recyclerView,
                                        List<SubCategoryData> dataList) {
        SubCoursesRV subCoursesRV = new SubCoursesRV(context, recyclerView, dataList);
        if (subCoursesRV == null) {
            return null;
        } else {
            return subCoursesRV;
        }
    }

    public QuizSummaryRV setQuizSummaryRV(Context context, RecyclerView recyclerView,
                                         QuizSummaryModel dataList) {
        QuizSummaryRV quizSummaryRV = new QuizSummaryRV(context, recyclerView, dataList);
        if (quizSummaryRV == null) {
            return null;
        } else {
            return quizSummaryRV;
        }
    }

    public PromotionalRV setPromotionalBannerRV(Context context, RecyclerView recyclerView) {
        PromotionalRV promotionalRV = null;
        if (context != null && recyclerView != null) {
            promotionalRV = new PromotionalRV(context, recyclerView);
        }
        return promotionalRV;
    }

    CoursesRV coursesRV = null;

    public CoursesRV setCoursesRV(Context context, RecyclerView recyclerView, List<AllCoursesData> dataList) {
        if (context != null && recyclerView != null && dataList != null && dataList.size() > 0) {
            coursesRV = new CoursesRV(context, recyclerView, dataList);
        }
        return coursesRV;
    }

    public CoursesRV getCoursesRV() {
        return coursesRV;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //---------------------------------------- SearchListener -------------------------------------

    @Override
    public void onQueryChanged(String newText) {

    }

    // For checking permissions in android 11 or below
    public boolean isPermissionsGranted(Context context) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int readExternalStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
            int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            return readExternalStoragePermission == PackageManager.PERMISSION_GRANTED &&
                    cameraPermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    public void pickImagesFromGallery(int requestCode) {
        ///Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, requestCode);
    }

    //------------------------------------ Action Dialog -----------------------------------------
    public ActionChooserDialog getActionChooserDialog(Context context, Bundle bundle) {
        ActionChooserDialog dialog = ActionChooserDialog.getInstance();
        if (bundle != null) {
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(), context.getClass().getSimpleName());
        return dialog;
    }

    //------------------------------------ OnPageChangeListener -----------------------------------

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //------------------------------------ UserListener ------------------------------------
//    public UserListener userListener = new UserListener() {
//        @Override
//        public void onUserDataChanged(UserInfoDataModel userData) {
//
//        }
//    };

    public void updateViewVisibility(View view, int visibility) {
        if (view == null) return;
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }
}
