package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Adapters.OffersAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.R;

import java.util.Timer;
import java.util.TimerTask;

public class OffersRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;
    OffersAdapter offersAdapter;
    LinearLayoutManager layoutManager;

    TextView offer_tv;

    public OffersRV(Context context, RecyclerView recyclerView) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;

        setOffersRV();
    }

    private void setOffersRV() {
        if (recyclerView == null)return;


        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        offersAdapter = new OffersAdapter(context,this);
        recyclerView.setAdapter(offersAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        if (recyclerView.getOnFlingListener() == null)
        snapHelper.attachToRecyclerView(recyclerView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (layoutManager.findLastCompletelyVisibleItemPosition() < (offersAdapter.getDataCount() -1)){
                    layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(),layoutManager.findLastCompletelyVisibleItemPosition() + 1);
                } else if (layoutManager.findLastCompletelyVisibleItemPosition() == (offersAdapter.getDataCount() -1)){
                    layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(),0);
                }
            }
        },0,4000);
    }

    @Override
    public void initializeComponents(View view) {
        if (view == null)return;
        offer_tv = view.findViewById(R.id.offer_tv);
    }

    @Override
    public void setData(View view, int position) {
        startBlinkAnim(offer_tv);
    }
}
