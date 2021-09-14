package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal cost;

    public Item(String name, BigDecimal cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
