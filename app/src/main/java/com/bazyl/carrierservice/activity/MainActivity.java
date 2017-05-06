package com.bazyl.carrierservice.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bazyl.carrierservice.R;
import com.bazyl.carrierservice.adapter.OrderAdapter;
import com.bazyl.carrierservice.contract.FetchOrdersContract;
import com.bazyl.carrierservice.graphics.OrderDivider;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //toolbar.inflateMenu(R.menu.toolbar_create_order_menu);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        registerForContextMenu(recyclerView);
        fetchOrderPresenter = new FetchOrderPresenter(this);
        fetchOrderPresenter.loadOrders();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        OrderAdapter orderAdapter = (OrderAdapter) recyclerView.getAdapter();
        try {
            position = orderAdapter.getPosition();
        } catch (Exception e) {
            Log.d("TAG", e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.phone:
                Log.i("ITEM1", "CLICK");
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + orderAdapter.getOrder(position).getPhone()));
                startActivity(intent);
                break;
            case R.id.map:
                Log.i("ITEM2", "CLICK");
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void startOrderCreateActivity(View v) {
        Intent intent = new Intent(this, OrderCreateActivity.class);
        startActivity(intent);
    }

}

