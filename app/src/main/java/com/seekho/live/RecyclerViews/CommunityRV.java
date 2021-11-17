package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Adapters.CommunityAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.R;

public class CommunityRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;
    CommunityAdapter communityAdapter;

    CardView card_view;

    public CommunityRV(Context context, RecyclerView recyclerView) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;

        setCommunityRV();
    }

    private void setCommunityRV() {
        if (recyclerView == null) return;

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        communityAdapter = new CommunityAdapter(context, this);
        recyclerView.setAdapter(communityAdapter);
    }

    @Override
    public void initializeComponents(View view) {
        if (view == null) return;
        card_view = view.findViewById(R.id.card_view);

        //card_view.setOnClickListener(this);
    }

    @Override
    public void setData(View view, int position) {

    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.card_view:
//                showSimpleToast("Community Clicked!!!");
//                break;
//        }
//    }
}
