package com.seekho.live.BaseToolsFragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import com.seekho.live.Activities.AllMainCoursesActivity;
import com.seekho.live.Activities.DashboardActivity;
import com.seekho.live.Activities.LecturesAndAccompayingMCQActivity;
import com.seekho.live.Activities.LoginActivity;
import com.seekho.live.Activities.MockTestsActivity;
import com.seekho.live.Activities.OTPVerificationActivity;
import com.seekho.live.Activities.QuizActivity;
import com.seekho.live.Activities.RegisterActivity;
import com.seekho.live.Activities.ResultsActivity;
import com.seekho.live.Activities.SubCoursesActivity;
import com.seekho.live.Activities.UpdatePasswordActivity;
import com.seekho.live.Activities.UpdateProfileActivity;
import com.seekho.live.Activities.WebViewActivity;
import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.R;

public class ToolbarFragment extends AppBaseFragment {

    CardView card_view, search_cv;
    ImageView back_iv, menu_iv, logo_iv, search_iv,refresh_iv;
    TextView title_tv;
    TextView open_test_btn_tv;
    Button cancel_btn;
    SearchView search_view;

    @Override
    public int layoutResourceID() {
        return R.layout.layout_toolbar;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;

        card_view = getView().findViewById(R.id.card_view);
        back_iv = getView().findViewById(R.id.back_iv);
        menu_iv = getView().findViewById(R.id.menu_iv);
        logo_iv = getView().findViewById(R.id.logo_iv);
        title_tv = getView().findViewById(R.id.title_tv);
        open_test_btn_tv = getView().findViewById(R.id.open_test_btn_tv);
        search_iv = getView().findViewById(R.id.search_iv);
        search_cv = getView().findViewById(R.id.search_cv);
        cancel_btn = getView().findViewById(R.id.cancel_btn);
        search_view = getView().findViewById(R.id.search_view);
        refresh_iv = getView().findViewById(R.id.refresh_iv);

        search_view.setOnQueryTextListener(onQueryTextListener);
        back_iv.setOnClickListener(this);
        menu_iv.setOnClickListener(this);
        search_iv.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        open_test_btn_tv.setOnClickListener(this);
        refresh_iv.setOnClickListener(this);
        setToolbarView();
    }

    public void setTitle(String title) {
        title_tv.setText(title);
    }

    //------------------------------------- Search Setup ------------------------------------------

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            ((SearchListener)getActivity()).onQueryChanged(newText);
            return false;
        }
    };


    public interface SearchListener {
        void onQueryChanged(String newText);
    }

    //------------------------------------- ToolbarView Setup -------------------------------------

    private void setToolbarView() {
        if (getActivity() != null && getActivity() instanceof LoginActivity) {
        } else if (getActivity() != null && getActivity() instanceof RegisterActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            open_test_btn_tv.setVisibility(View.GONE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("Register");
        } else if (getActivity() != null && getActivity() instanceof DashboardActivity) {
            back_iv.setVisibility(View.GONE);
            menu_iv.setVisibility(View.VISIBLE);
            logo_iv.setVisibility(View.VISIBLE);
            title_tv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
        } else if (getActivity() != null && getActivity() instanceof OTPVerificationActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            open_test_btn_tv.setVisibility(View.GONE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("Verify OTP");
        } else if (getActivity() != null && getActivity() instanceof SubCoursesActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
        } else if (getActivity() != null && getActivity() instanceof LecturesAndAccompayingMCQActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
        }
//        else if (getActivity() != null && getActivity() instanceof SubjectsLectureActivity){
//            back_iv.setVisibility(View.VISIBLE);
//            menu_iv.setVisibility(View.GONE);
//            logo_iv.setVisibility(View.GONE);
//            open_test_btn_tv.setVisibility(View.GONE);
//            title_tv.setVisibility(View.VISIBLE);
//            setTitle("Subject Name");
//        }
        else if (getActivity() != null && getActivity() instanceof QuizActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
        } else if (getActivity() != null && getActivity() instanceof ResultsActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("Result");
        } else if (getActivity() != null && getActivity() instanceof UpdateProfileActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("Update Profile");
        } else if (getActivity() != null && getActivity() instanceof UpdatePasswordActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("Change Password");
        } else if (getActivity() != null && getActivity() instanceof MockTestsActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.VISIBLE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("Mock Test");
        } else if (getActivity() != null && getActivity() instanceof WebViewActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.GONE);
            refresh_iv.setVisibility(View.VISIBLE);
        } else if (getActivity() != null && getActivity() instanceof AllMainCoursesActivity) {
            back_iv.setVisibility(View.VISIBLE);
            menu_iv.setVisibility(View.GONE);
            logo_iv.setVisibility(View.GONE);
            open_test_btn_tv.setVisibility(View.GONE);
            title_tv.setVisibility(View.VISIBLE);
            search_iv.setVisibility(View.VISIBLE);
            refresh_iv.setVisibility(View.GONE);
            setTitle("All Courses");
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.back_iv:
                if (getActivity() != null && getActivity() instanceof ResultsActivity) {
                    goToDashboardActivity(null);
                } else {
                    getActivity().finish();
                }
                break;
            case R.id.menu_iv:
                ((OnToolbarClickListener) getActivity()).onToolbarClick(view);
                break;
            case R.id.open_test_btn_tv:
                ((OnToolbarClickListener) getActivity()).onToolbarClick(view);
                break;
            case R.id.search_iv:
                setSearchView(0);
                break;
            case R.id.cancel_btn:
                setSearchView(1);
                break;
            case R.id.refresh_iv:
                ((OnToolbarClickListener) getActivity()).onToolbarClick(view);
                break;
        }
    }

    private void setSearchView(int type) {
        switch (type) {
            case 0:
                search_iv.setVisibility(View.GONE);
                search_cv.setVisibility(View.VISIBLE);
                title_tv.setVisibility(View.GONE);
                break;
            case 1:
                search_iv.setVisibility(View.VISIBLE);
                search_cv.setVisibility(View.GONE);
                title_tv.setVisibility(View.VISIBLE);
                hideKeyboardFrom(getActivity(), search_view);
                break;
        }
    }

    public interface OnToolbarClickListener {
        void onToolbarClick(View view);
    }
}
