package com.bazyl.carrierservice.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.bazyl.carrierservice.R;

public class OrderCreateActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

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
            case R.id.create_order_menu_item:
                Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
