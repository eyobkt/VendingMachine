package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String name;
    private BigDecimal cost;
    private int numberOfItems;

    public Item(String name, BigDecimal cost, int numberOfItems) {
        this.name = name;
        this.cost = cost;
        this.numberOfItems = numberOfItems;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.cost);
        hash = 79 * hash + this.numberOfItems;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.numberOfItems != other.numberOfItems) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", numberOfItems=" + numberOfItems + '}';
    }
}
