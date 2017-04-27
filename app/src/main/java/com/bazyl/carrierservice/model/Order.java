package com.bazyl.carrierservice.model;

/**
 * Created by bazyl on 4/27/17.
 */

public class Order {
    private String consumer;
    private ItemPocket itemPocket;
    private String price;
    private String phone;
    private String location;

    public Order(String consumer, ItemPocket item, String price, String phone, String location) {
        this.consumer = consumer;
        this.itemPocket = item;
        this.price = price;
        this.phone = phone;
        this.location = location;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public ItemPocket getItems() {
        return itemPocket;
    }

    public void setItems(ItemPocket items) {
        this.itemPocket = items;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}