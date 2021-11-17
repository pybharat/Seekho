package com.seekho.live.Fragments;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seekho.live.AppBase.AppBaseFragment;
import com.seekho.live.R;

public class OffersFragment extends AppBaseFragment {

    RelativeLayout offers_rl;
    TextView offer_tv;

    @Override
    public int layoutResourceID() {
        return R.layout.rv_offers_layout;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null)return;
        offers_rl = getView().findViewById(R.id.offers_rl);
        offer_tv = getView().findViewById(R.id.offer_tv);

        //offers_rl.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        startBlinkAnim(offer_tv);
    }

//    @Override
//    public void onClick(View view) {
//        super.onClick(view);
//        switch (view.getId()){
//            case R.id.offers_rl:
//                Bundle bundle = new Bundle();
//                bundle.putString(WEB_URL,WEB_DOMAIN_URL);
//                goToWebViewActivity(bundle);
//                //((OnOffersListener)getActivity()).onOfferClick(view);
//                break;
//        }
//    }

    public interface OnOffersListener{
        void onOfferClick(View view);
    }
}
