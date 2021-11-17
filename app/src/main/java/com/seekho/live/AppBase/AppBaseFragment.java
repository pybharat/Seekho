package com.seekho.live.AppBase;

//Created by Mohammed Faisal 15 March 2021

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.seekho.live.Activities.AllMainCoursesActivity;
import com.seekho.live.Activities.UpdatePasswordActivity;
import com.seekho.live.Activities.DashboardActivity;
import com.seekho.live.Activities.LoginActivity;
import com.seekho.live.Activities.MockTestsActivity;
import com.seekho.live.Activities.RegisterActivity;
import com.seekho.live.Activities.SubCoursesActivity;
import com.seekho.live.Activities.UpdateProfileActivity;
import com.seekho.live.Activities.WebViewActivity;
import com.seekho.live.Base.BaseFragment;
import com.seekho.live.Dialogs.ForgetPasswordDialog;
import com.seekho.live.Dialogs.ProgressDialog;
import com.seekho.live.Fragments.OffersFragment;
import com.seekho.live.Interfaces.Constants;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.Interfaces.UserListener;
import com.seekho.live.Models.Courses.AllCoursesData;
import com.seekho.live.Models.UserProfile.UserInfoDataModel;
import com.seekho.live.R;
import com.seekho.live.RecyclerViews.BlogsRV;
import com.seekho.live.RecyclerViews.CommunityRV;
import com.seekho.live.RecyclerViews.CoursesRV;
import com.seekho.live.RecyclerViews.OffersRV;
import com.seekho.live.RecyclerViews.PromotionalRV;
import com.seekho.live.WebBase.WebContants;
import com.seekho.live.WebBase.WebListener;
import com.seekho.live.WebBase.WebRequest;

import java.util.List;

import retrofit2.Response;

public abstract class AppBaseFragment extends BaseFragment implements Constants, WebContants, WebListener,
        SwipeRefreshLayout.OnRefreshListener,UserListener, OnRecyclerListener {

    OffersFragment offersFragment;

    @Override
    public void initializeComponents() {

        Fragment offers_fragment = getFragManager().findFragmentById(R.id.offers_fragment_id);
        if (offers_fragment instanceof AppBaseFragment){
            offersFragment = (OffersFragment)offers_fragment;
            offersFragment.initializeComponents();
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onUserDataChanged(UserInfoDataModel userData) {

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

    public ForgetPasswordDialog getForgetPassDialog(Context context, Bundle bundle){
        ForgetPasswordDialog dialog = ForgetPasswordDialog.getInstance(context);
        if (bundle != null){
            dialog.setArguments(bundle);
        }
        dialog.show(getFragManager(),dialog.getClass().getSimpleName());
        return dialog;
    }

    public ProgressDialog showProgress(Context context) {
        ProgressDialog progressDialog = ProgressDialog.getInstance(context);
        if (progressDialog != null)
            progressDialog.show(getFragManager(), progressDialog.getClass().getSimpleName());
        return progressDialog;
    }

    public void finishProgress(Context context){
        ProgressDialog progressDialog = ProgressDialog.getInstance(context);
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    public void goToDashboardActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToSubCoursesActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), SubCoursesActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToRegisterActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToUpdateActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToChangePassActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), UpdatePasswordActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToLoginActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().finish();
    }

    public void goToMockTestsActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), MockTestsActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToWebViewActivity(Bundle bundle){
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void goToAllMainCoursesActivity(Bundle bundle) {
        Intent intent = new Intent(getActivity(), AllMainCoursesActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    public void showSimpleToast(String msg){
        if (msg != null && !msg.isEmpty()){
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void hideKeyBoard(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null){
            InputMethodManager manager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    //--------------------------------------- Web Listener ----------------------------------------

    public WebRequest makeWebRequest(Context context){
        WebRequest webRequest = new WebRequest(context);
        if (webRequest != null){
            return webRequest;
        } else {
            return null;
        }
    }

    @Override
    public void onWebRequestSuccess(WebRequest webRequest,Response response) {

    }

    @Override
    public void onWebFailure(Throwable throwable) {

    }

    //-------------------------------------- Set RecyclerViews ------------------------------------
    public CoursesRV setCoursesRV(Context context, List<AllCoursesData> allCoursesDataList,
                                  RecyclerView recyclerView){
        CoursesRV coursesRV = null;
        if (context != null && recyclerView != null){
            coursesRV = new CoursesRV(context,recyclerView,allCoursesDataList);
        }
        return coursesRV;
    }

    public OffersRV setOffersRV(Context context,
                                 RecyclerView recyclerView){
        OffersRV offersRV = null;
        if (context != null && recyclerView != null){
            offersRV = new OffersRV(context,recyclerView);
        }
        return offersRV;
    }

    public PromotionalRV setPromotionalBannerRV(Context context, RecyclerView recyclerView){
        PromotionalRV promotionalRV = null;
        if (context != null && recyclerView != null){
            promotionalRV = new PromotionalRV(context,recyclerView);
        }
        return promotionalRV;
    }

    public BlogsRV setBlogsRV(Context context, RecyclerView recyclerView){
        BlogsRV blogsRV = null;
        if (context != null && recyclerView != null){
            blogsRV = new BlogsRV(context,recyclerView);
        }
        return blogsRV;
    }

    public CommunityRV setCommunityRV(Context context, RecyclerView recyclerView){
        CommunityRV communityRV = null;
        if (context != null && recyclerView != null){
            communityRV = new CommunityRV(context,recyclerView);
        }
        return communityRV;
    }

    public void startBlinkAnim(View view){
        if (view != null){
            Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_blink);
            view.startAnimation(animation);
        }
    }

    public void startSlideFromRightAnim(View view){
        if (view != null){
            Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_slide_right_and_blink);
            view.startAnimation(animation);
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void updateViewVisibility(View view, int visibility) {
        if (view == null) return;
        if (view.getVisibility() != visibility) {
            view.setVisibility(visibility);
        }
    }
}
