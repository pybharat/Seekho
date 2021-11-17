package com.seekho.live.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public abstract class BaseAleartDialog extends AlertDialog implements View.OnClickListener {

    View view;

    protected BaseAleartDialog(@NonNull Context context) {
        super(context);
        setView(getView());
        initializeComponents();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public abstract int layoutResourseID();

    public abstract void initializeComponents();

    public View getView(){
        view = LayoutInflater.from(getContext().getApplicationContext()).inflate(layoutResourseID(),null);
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
