package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;

import java.util.ArrayList;
import java.util.List;

public class PromotionsAdapter extends AppBaseAdapter {

    Context context;
    OnRecyclerListener onRecyclerListener;

    CardView card_view;

    ImageView banner_iv;

    private List<Integer> getBannersList(){
        List<Integer> banners_list = new ArrayList<>();
        banners_list.add(R.drawable.banner_0);
        banners_list.add(R.drawable.banner_1);
        banners_list.add(R.drawable.banner_2);
        banners_list.add(R.drawable.banner_3);
        return banners_list;
    }

    public PromotionsAdapter(Context context,OnRecyclerListener recyclerListener) {
        this.context = context;
        this.onRecyclerListener=  recyclerListener;
    }

    public PromotionsAdapter(Context context) {
        this.context = context;
    }

    public PromotionsAdapter() {
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_promotions_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null)return;
        if (onRecyclerListener == null)return;
        onRecyclerListener.onItemClick(view,position);

        banner_iv = view.findViewById(R.id.banner_iv);

        if (getBannersList() != null && getBannersList().size() > 0){
            Glide.with(context).load(getBannersList().get(position)).into(banner_iv);
        }
    }

    @Override
    public int getDataCount() {
        return getBannersList().size();
    }
}
