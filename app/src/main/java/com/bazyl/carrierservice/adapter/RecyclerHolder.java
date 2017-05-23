package com.bazyl.carrierservice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.bazyl.carrierservice.R;


public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public TextView consumer;
    public TextView items;
    public TextView phone;
    public TextView price;
    public TextView location;
    public View view;

    public RecyclerHolder(@NonNull View view) {
        super(view);
        this.view = view;
        consumer = (TextView) view.findViewById(R.id.consumer);
        items = (TextView) view.findViewById(R.id.orders);
        phone = (TextView) view.findViewById(R.id.phone);
        price = (TextView) view.findViewById(R.id.price);
        location = (TextView) view.findViewById(R.id.location);
        view.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.add(Menu.NONE, R.id.phone, Menu.NONE, R.string.call);
        contextMenu.add(Menu.NONE, R.id.map, Menu.NONE, R.string.map);
    }

}
