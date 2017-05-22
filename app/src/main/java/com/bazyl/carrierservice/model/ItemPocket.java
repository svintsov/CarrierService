package com.bazyl.carrierservice.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ItemPocket {

    private List<String> itemsList;

    public ItemPocket() {
        itemsList = new ArrayList<>();
    }

    public ItemPocket(List<String> items) {
        itemsList = items;
    }

    public ItemPocket(String[] items) {
        itemsList = new ArrayList<>(Arrays.asList(items));
    }

    public List<String> getItemsList() {
        return itemsList;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Items:\n");
        for (String item : itemsList) {
            stringBuilder.append(item).append("\n");
        }
        return stringBuilder.toString();
    }
}
