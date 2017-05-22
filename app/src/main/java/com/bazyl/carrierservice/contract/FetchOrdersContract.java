package com.bazyl.carrierservice.contract;

import com.bazyl.carrierservice.model.Order;

import java.util.List;

/**
 * Created by bazyl on 4/27/17.
 */

public interface FetchOrdersContract {
    interface View {
        void showOrders(List<Order> orders);
    }

    interface Presenter {
        void loadOrders();

        void saveOrder(Order order);
    }
}