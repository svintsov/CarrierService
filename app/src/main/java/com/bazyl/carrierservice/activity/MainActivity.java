package com.bazyl.carrierservice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.bazyl.carrierservice.R;
import com.bazyl.carrierservice.adapter.OrderAdapter;
import com.bazyl.carrierservice.contract.FetchOrdersContract;
import com.bazyl.carrierservice.model.Order;
import com.bazyl.carrierservice.presenter.FetchOrderPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchOrdersContract.View {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private FetchOrderPresenter fetchOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        registerForContextMenu(recyclerView);
        fetchOrderPresenter = new FetchOrderPresenter(this);
        fetchOrderPresenter.loadOrders();

    }


    @Override
    public void showOrders(List<Order> orders) {
        orderAdapter = new OrderAdapter(orders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new OrderDivider(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(orderAdapter);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.phone:
                Log.i("ITEM1", "CLICK");
                break;
            case R.id.map:
                Log.i("ITEM2", "CLICK");
                break;
        }
        return super.onContextItemSelected(item);
    }

}

