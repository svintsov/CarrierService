package com.bazyl.carrierservice.presenter;

import com.bazyl.carrierservice.contract.FetchOrdersContract;
import com.bazyl.carrierservice.model.ItemPocket;
import com.bazyl.carrierservice.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bazyl on 4/27/17.
 */

public class FetchOrderPresenter implements FetchOrdersContract.Presenter {
    private List<Order> orders;
    private FetchOrdersContract.View view;

    public FetchOrderPresenter(FetchOrdersContract.View view) {
        this.view = view;
        orders = new ArrayList<>();
    }

    @Override
    public void loadOrders() {
        fetchOrders();
        view.showOrders(orders);
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
