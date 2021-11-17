package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Adapters.PromotionsAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.R;

import java.util.Timer;
import java.util.TimerTask;

public class PromotionalRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;

    PromotionsAdapter promotionsAdapter;
    LinearLayoutManager layoutManager;

    CardView card_view;

    public PromotionalRV(Context context, RecyclerView recyclerView) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;

        setPromotionalRV();
    }

    private void setPromotionalRV() {
        if (recyclerView == null) return;
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        promotionsAdapter = new PromotionsAdapter(context, this);
        recyclerView.setAdapter(promotionsAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (layoutManager.findLastCompletelyVisibleItemPosition() < (promotionsAdapter.getDataCount() -1)){
                    layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(),layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                } else if (layoutManager.findLastCompletelyVisibleItemPosition() == (promotionsAdapter.getDataCount() -1)){
                    layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(),0);
                }
            }
        },0,4000);
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
//                Bundle bundle = new Bundle();
//                bundle.putString(WEB_URL, WEB_DOMAIN_URL);
//                goToWebViewActivity(bundle);
//                break;
//        }
//    }
}
