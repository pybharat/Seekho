package com.seekho.live.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.seekho.live.AppBase.AppBaseAdapter;
import com.seekho.live.Interfaces.OnRecyclerListener;
import com.seekho.live.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommunityAdapter extends AppBaseAdapter {

    Context context;
    OnRecyclerListener recyclerListener;

    TextView mem_name_tv,mem_location_tv,mem_desc_tv;

    private List<HashMap<String, String>> communityList() {
        List<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> hashMap_0 = new HashMap<>();
        hashMap_0.put("mem_name", context.getResources().getString(R.string.mem_1_name));
        hashMap_0.put("mem_location", context.getResources().getString(R.string.mem_1_location));
        hashMap_0.put("mem_desc", context.getResources().getString(R.string.mem_1_desc));
        list.add(hashMap_0);

        HashMap<String,String> hashMap_1 = new HashMap<>();
        hashMap_1.put("mem_name", context.getResources().getString(R.string.mem_2_name));
        hashMap_1.put("mem_location", context.getResources().getString(R.string.mem_2_location));
        hashMap_1.put("mem_desc", context.getResources().getString(R.string.mem_2_desc));
        list.add(hashMap_1);

        HashMap<String,String> hashMap_2 = new HashMap<>();
        hashMap_2.put("mem_name", context.getResources().getString(R.string.mem_3_name));
        hashMap_2.put("mem_location", context.getResources().getString(R.string.mem_3_location));
        hashMap_2.put("mem_desc", context.getResources().getString(R.string.mem_3_desc));
        list.add(hashMap_2);

        return list;
    }

    public CommunityAdapter(Context context, OnRecyclerListener onRecyclerListener) {
        this.context = context;
        this.recyclerListener = onRecyclerListener;
    }

    public CommunityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.rv_community_layout;
    }

    @Override
    public void initializeComponents(View view, int position) {
        if (getView() == null)return;
        if (recyclerListener == null)return;
        recyclerListener.onItemClick(view,position);

        mem_name_tv = getView().findViewById(R.id.mem_name_tv);
        mem_location_tv = getView().findViewById(R.id.mem_location_tv);
        mem_desc_tv = getView().findViewById(R.id.mem_desc_tv);

        HashMap<String,String> hashMap = communityList().get(position);
        if (hashMap != null){
            mem_name_tv.setText(hashMap.get("mem_name"));
            mem_location_tv.setText(hashMap.get("mem_location"));
            mem_desc_tv.setText(hashMap.get("mem_desc"));
        }
    }

    @Override
    public int getDataCount() {
        return communityList().size();
    }
}
