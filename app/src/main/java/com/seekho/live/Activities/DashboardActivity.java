package com.seekho.live.Activities;

import android.os.Handler;
import android.view.View;

import com.seekho.live.Adapters.PagerAdapters.SeekhoPagerAdapter;
import com.seekho.live.AppBase.AppBaseActivity;
import com.seekho.live.Fragments.HomeFragment;
import com.seekho.live.Fragments.MockTestsFragment;
import com.seekho.live.Fragments.ProfileFragment;
import com.seekho.live.R;
import com.seekho.live.Utils.Fun;
import com.seekho.live.customviewpager.CustomViewPager;

public class DashboardActivity extends AppBaseActivity {

    boolean isBackPressed = false;

    public CustomViewPager view_pager;
    SeekhoPagerAdapter seekhoPagerAdapter;

    @Override
    public int layoutResourceID() {
        return R.layout.activity_dashboard;
    }

    @Override
    public int getFragmentContainerResourceID() {
        return R.id.fragment_container_id;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();

        view_pager = findViewById(R.id.view_pager);
        view_pager.addOnPageChangeListener(this);
        view_pager.setSwipeLocked(true);

        setSeekhoMainPager();
        setViews(0);
    }

    private void setSeekhoMainPager() {
        seekhoPagerAdapter = new SeekhoPagerAdapter(getFragManager());
        seekhoPagerAdapter.addFragment(new HomeFragment());
        seekhoPagerAdapter.addFragment(new MockTestsFragment());
        seekhoPagerAdapter.addFragment(new ProfileFragment());
        view_pager.setAdapter(seekhoPagerAdapter);
    }


    @Override
    public void onBottomItemClick(View view) {
        switch (view.getId()) {
            case R.id.home_rl:
                setViews(0);
                break;
            case R.id.mock_tests_rl:
                setViews(1);
                break;
            case R.id.profile_rl:
                setViews(2);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                getBottomNavigationFragment().setView(0);
                setViews(0);
                break;
            case 1:
                getBottomNavigationFragment().setView(1);
                setViews(1);
                break;
            case 2:
                getBottomNavigationFragment().setView(2);
                setViews(2);
                break;
        }
    }

    private void setViews(int viewType){
        if (viewType == 0){
            view_pager.setCurrentItem(0);
        } else if (viewType == 1){
            view_pager.setCurrentItem(1);
        } else if (viewType == 2){
            view_pager.setCurrentItem(2);
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        changeFragment(new HomeFragment(), null, true, false);
//    }

    @Override
    public void onBackPressed() {
        if (isBackPressed) {
            super.onBackPressed();
            return;
        }

        this.isBackPressed = true;
        //showSimpleToast("Double tab for exit");
        Fun.showSnackBar(this,
                Fun.SHOW_SIMPLE_MESSAGE,
                "Double tab for exit",
                R.id.bottom_navigation_id,
                2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 2000);
    }
}