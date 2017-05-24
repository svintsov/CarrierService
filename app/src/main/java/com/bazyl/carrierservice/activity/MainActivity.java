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
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bazyl.carrierservice.R;
import com.bazyl.carrierservice.adapter.OrderAdapter;
import com.bazyl.carrierservice.adapter.RecyclerHolder;
import com.bazyl.carrierservice.contract.FetchOrdersContract;
import com.bazyl.carrierservice.graphics.OrderDivider;
import com.bazyl.carrierservice.model.Order;
import com.bazyl.carrierservice.presenter.FetchOrderPresenter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchOrdersContract.View {
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private FetchOrderPresenter fetchOrderPresenter;
    private FirebaseRecyclerAdapter<Order, RecyclerHolder> mAdapter;
    private int positionLongClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        //toolbar.inflateMenu(R.menu.toolbar_create_order_menu);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        registerForContextMenu(recyclerView);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(MainActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition();
                mAdapter.getRef(position).removeValue();
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mOrders = ref.child("orders");
        //orderAdapter = new OrderAdapter(orders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new OrderDivider(this, LinearLayoutManager.VERTICAL));
        mAdapter = new FirebaseRecyclerAdapter<Order, RecyclerHolder>(Order.class, R.layout.list_item_view, RecyclerHolder.class, mOrders) {
            @Override
            public void populateViewHolder(final RecyclerHolder holder, Order order, int position) {
                holder.consumer.setText(order.getConsumer());
                holder.items.setText(order.getItems().toString());
                holder.price.setText(order.getPrice().concat("₴"));
                holder.phone.setText(order.getPhone());
                holder.location.setText(order.getLocation());
                holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        setLongClickPosition(holder.getLayoutPosition());
                        return false;
                    }
                });
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    void setLongClickPosition(int position) {
        positionLongClicked = position;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.phone:
                Log.i("ITEM1", "CLICK");
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mAdapter.getItem(positionLongClicked).getPhone()));
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

