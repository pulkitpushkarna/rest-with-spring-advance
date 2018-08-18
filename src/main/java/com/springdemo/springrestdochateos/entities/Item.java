package com.springdemo.springrestdochateos.entities;

import org.springframework.hateoas.ResourceSupport;

public class Item extends ResourceSupport {

    private Integer itemId;
    private String name;
    private Integer price;

    public Item(Integer itemId, String name, Integer price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
