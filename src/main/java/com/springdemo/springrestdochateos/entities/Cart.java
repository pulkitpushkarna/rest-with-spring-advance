package com.springdemo.springrestdochateos.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> list= new ArrayList<>();

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}
