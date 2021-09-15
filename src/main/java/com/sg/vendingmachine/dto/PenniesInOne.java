package com.sg.vendingmachine.dto;

public enum PenniesInOne {
    DOLLAR(100), QUARTER(25), DIME(10), NICKEL(5);
    
    private final int value;
    
    PenniesInOne(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
