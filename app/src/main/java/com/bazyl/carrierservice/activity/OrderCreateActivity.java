package com.bazyl.carrierservice.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.bazyl.carrierservice.R;
import com.bazyl.carrierservice.model.ItemPocket;
import com.bazyl.carrierservice.model.Order;
import com.bazyl.carrierservice.presenter.FetchOrderPresenter;

public class OrderCreateActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    FetchOrderPresenter fetchOrderPresenter = new FetchOrderPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_create);

        Toolbar toolbar = (Toolbar) findViewById(R.id.order_create_toolbar);
        toolbar.inflateMenu(R.menu.toolbar_create_order_menu);
        //setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_order_menu_item: {
                Order order = getOrderFromForms();
                Toast.makeText(this, order.getPhone(), Toast.LENGTH_SHORT).show();
                fetchOrderPresenter.saveOrder(order);
            }
        }
        return true;
    }

    Order getOrderFromForms() {
        Order order = new Order();
        TextInputLayout consumerWrapper = (TextInputLayout) findViewById(R.id.consumerwrapper);
        TextInputLayout priceWrapper = (TextInputLayout) findViewById(R.id.pricewrapper);
        TextInputLayout phoneWrapper = (TextInputLayout) findViewById(R.id.phonewrapper);
        TextInputLayout locationWrapper = (TextInputLayout) findViewById(R.id.locationwrapper);
        TextInputLayout itemsWrapper = (TextInputLayout) findViewById(R.id.itemswrapper);
        if (validateConsumer(consumerWrapper)) {
            order.setConsumer(consumerWrapper.getEditText().getText().toString());
        } else {
            consumerWrapper.setError("Set consumer");
        }
        if (validatePrice(priceWrapper)) {
            order.setPrice(priceWrapper.getEditText().getText().toString());
        } else {
            priceWrapper.setError("Set price");
        }
        if (validateLocation(locationWrapper)) {
            order.setLocation(locationWrapper.getEditText().getText().toString());
        } else {
            locationWrapper.setError("Set location");
        }
        if (validatePhone(phoneWrapper)) {
            order.setPhone(phoneWrapper.getEditText().getText().toString());
        } else {
            phoneWrapper.setError("Set phone");
        }
        if (validateItems(itemsWrapper)) {
            order.setItems(new ItemPocket(itemsWrapper.getEditText().getText().toString().trim().split(",")));
        } else {
            itemsWrapper.setError("Set items");
        }

        return order;
    }

    boolean validateConsumer(TextInputLayout consumerWrapper) {
        String consumer = consumerWrapper.getEditText().getText().toString();
        return consumer != null && !consumer.isEmpty();

    }

    boolean validatePrice(TextInputLayout priceWrapper) {
        String price = priceWrapper.getEditText().getText().toString();
        return price != null && !price.isEmpty();
    }

    boolean validatePhone(TextInputLayout phoneWrapper) {
        String phone = phoneWrapper.getEditText().getText().toString();
        return phone != null && !phone.isEmpty();
    }

    boolean validateLocation(TextInputLayout locationWrapper) {
        String location = locationWrapper.getEditText().getText().toString();
        return location != null && !location.isEmpty();
    }

    boolean validateItems(TextInputLayout itemsWrapper) {
        String items = itemsWrapper.getEditText().getText().toString();
        return items != null && !items.isEmpty();
    }

}
