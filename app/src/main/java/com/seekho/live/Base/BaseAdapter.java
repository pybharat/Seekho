package com.seekho.live.Base;

//Created by Mohammed Faisal 15 March 2021

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    public Context context;
    View view;

    public abstract int layoutResourceID();

    public abstract void initializeComponents(View view, int position);

    public abstract int getDataCount();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(layoutResourceID(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        initializeComponents(holder.itemView, position);
    }

    public View getView() {
        return view;
    }

    @Override
    public int getItemCount() {
        return getDataCount();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void updateViewVisibility(View view,int visibility){
        if (view == null)return;
        if (view.getVisibility() != visibility){
            view.setVisibility(visibility);
        }
    }
}
