package com.seekho.live.RecyclerViews;

import android.content.Context;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seekho.live.Adapters.BlogsAdapter;
import com.seekho.live.AppBase.AppBaseRV;
import com.seekho.live.R;

public class BlogsRV extends AppBaseRV {

    Context context;
    RecyclerView recyclerView;

    BlogsAdapter blogsAdapter;

    CardView card_view;

    public BlogsRV(Context context, RecyclerView recyclerView) {
        super(context);
        this.context = context;
        this.recyclerView = recyclerView;

        setBlogsRV();
    }

    private void setBlogsRV() {
        if (recyclerView == null)return;
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        blogsAdapter = new BlogsAdapter(context,this);
        recyclerView.setAdapter(blogsAdapter);
    }

    @Override
    public void initializeComponents(View view) {
        if (view == null)return;
        card_view = view.findViewById(R.id.card_view);

        card_view.setOnClickListener(this);
    }

    @Override
    public void setData(View view, int position) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_view:
                showSimpleToast("Blogs Clicked");
                break;
        }
    }
}
