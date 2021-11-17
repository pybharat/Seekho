package com.seekho.live.BaseToolsFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.R;

public class BottomNavigationFragment extends AppBaseFragment {

    RelativeLayout home_rl, mock_tests_rl, profile_rl;
    ImageView home_iv, mock_tests_iv, profile_iv;
    TextView profile_tv, mock_tests_tv, home_tv;

    @Override
    public int layoutResourceID() {
        return R.layout.layout_bottom_navigation;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null) return;

        home_rl = getView().findViewById(R.id.home_rl);
        mock_tests_rl = getView().findViewById(R.id.mock_tests_rl);
        profile_rl = getView().findViewById(R.id.profile_rl);
        home_iv = getView().findViewById(R.id.home_iv);
        mock_tests_iv = getView().findViewById(R.id.mock_tests_iv);
        profile_iv = getView().findViewById(R.id.profile_iv);
        home_tv = getView().findViewById(R.id.home_tv);
        mock_tests_tv = getView().findViewById(R.id.mock_tests_tv);
        profile_tv = getView().findViewById(R.id.profile_tv);

        home_rl.setOnClickListener(this);
        mock_tests_rl.setOnClickListener(this);
        profile_rl.setOnClickListener(this);

        setView(0);
    }

    public void setView(int postion) {
        if (getActivity() != null) {
            switch (postion) {
                case 0:
                    home_iv.setImageResource(R.drawable.home_fill_icon);
                    mock_tests_iv.setImageResource(R.drawable.moke_test_border_icon);
                    profile_iv.setImageResource(R.drawable.profile_border_icon);
                    break;
                case 1:
                    home_iv.setImageResource(R.drawable.home_border_icon);
                    mock_tests_iv.setImageResource(R.drawable.moke_test_fill_icon);
                    profile_iv.setImageResource(R.drawable.profile_border_icon);
                    break;
                case 2:
                    home_iv.setImageResource(R.drawable.home_border_icon);
                    mock_tests_iv.setImageResource(R.drawable.moke_test_border_icon);
                    profile_iv.setImageResource(R.drawable.profile_fill_icon);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.home_rl:
                setView(0);
                ((OnBottomNavigationListener) getActivity()).onBottomItemClick(view);
                break;
            case R.id.mock_tests_rl:
                setView(1);
                ((OnBottomNavigationListener) getActivity()).onBottomItemClick(view);
                break;
            case R.id.profile_rl:
                setView(2);
                ((OnBottomNavigationListener) getActivity()).onBottomItemClick(view);
                break;
        }
    }

    public interface OnBottomNavigationListener {
        void onBottomItemClick(View view);
    }
}
