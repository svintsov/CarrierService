package com.bazyl.carrierservice.presenter;

import com.bazyl.carrierservice.contract.FetchOrdersContract;
import com.bazyl.carrierservice.model.ItemPocket;
import com.bazyl.carrierservice.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class FetchOrderPresenter implements FetchOrdersContract.Presenter {
    private List<Order> orders;
    private FetchOrdersContract.View view;
    private DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mOrders = mDataBase.child("orders");


    public FetchOrderPresenter(FetchOrdersContract.View view) {
        this.view = view;
        orders = new ArrayList<>();
    }

    public FetchOrderPresenter() {
    }

    @Override
    public void loadOrders() {
        fetchOrders();
        view.showOrders(orders);
    }

    @Override
    public void saveOrder(Order order) {
        mOrders.push().setValue(order);
    }


    private void fetchOrders() {
        String[] array = {"espresso gold", "espresso black", "espresso white"};
        ItemPocket itemPocket = new ItemPocket(array);
        Order order = new Order("Tanya", itemPocket, "625", "0964955931", "Kyiv");
        orders.add(order);
        order = new Order("Kyrylo", itemPocket, "512", "0965556612", "Kyiv");
        orders.add(order);
        order = new Order("Stanislav", itemPocket, "1027", "0965556612", "Kyiv");
        orders.add(order);
        order = new Order("Nastya", itemPocket, "389", "0965556612", "Kyiv");
        orders.add(order);
        order = new Order("Bazyl", itemPocket, "3800", "0965556612", "Kyiv");
        orders.add(order);
        order = new Order("Max", itemPocket, "999", "0965556612", "Kyiv");
        orders.add(order);
    }


}
