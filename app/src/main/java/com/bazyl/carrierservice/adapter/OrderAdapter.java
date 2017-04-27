package com.bazyl.carrierservice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bazyl.carrierservice.R;
import com.bazyl.carrierservice.model.Order;

import java.util.List;

/**
 * Created by bazyl on 4/27/17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private List<Order> orders;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView consumer;
        public TextView items;
        public TextView phone;
        public TextView price;
        public TextView location;

        public MyViewHolder(@NonNull View view){
            super(view);
            consumer = (TextView)view.findViewById(R.id.consumer);
            items = (TextView)view.findViewById(R.id.orders);
            phone = (TextView)view.findViewById(R.id.phone);
            price =  (TextView)view.findViewById(R.id.price);
            location = (TextView)view.findViewById(R.id.location);
        }
    }

    public OrderAdapter(List<Order> orders){
        this.orders=orders;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.consumer.setText(order.getConsumer());
        holder.items.setText(order.getItems().toString());
        holder.price.setText(order.getPrice().concat("₴"));
        holder.phone.setText(order.getPhone());
        holder.location.setText(order.getLocation());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
