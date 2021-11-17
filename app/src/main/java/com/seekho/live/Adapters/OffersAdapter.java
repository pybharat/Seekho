package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;

import java.util.ArrayList;
import java.util.List;

public class OffersAdapter extends AppBaseAdapter {

    Context context;
    OnRecyclerListener recyclerListener;

    private List<String> getOffersList() {
        List<String> offers_list = new ArrayList<>();
        offers_list.add(context.getResources().getString(R.string.offers_0_text));
        offers_list.add(context.getResources().getString(R.string.offers_text));
        offers_list.add(context.getResources().getString(R.string.offers_0_text));
        offers_list.add(context.getResources().getString(R.string.offers_text));
        offers_list.add(context.getResources().getString(R.string.offers_0_text));
        return offers_list;
    }

    public OffersAdapter(Context context, OnRecyclerListener recyclerListener) {
        this.context = context;
        this.recyclerListener = recyclerListener;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_offers_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (view == null)return;
        if (recyclerListener == null)return;
        recyclerListener.onItemClick(view,position);
    }

    @Override
    public int getDataCount() {
        return getOffersList().size();
    }
}
