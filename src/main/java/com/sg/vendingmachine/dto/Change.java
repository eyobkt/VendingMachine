package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {
    private static final int PENNIES_IN_ONE_DOLLAR = 100;
    private static final int PENNIES_IN_ONE_QUARTER = 25;
    private static final int PENNIES_IN_ONE_DIME = 10;
    private static final int PENNIES_IN_ONE_NICKEL = 5;
    
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private int dollars;
    
    public Change(int pennies) {
        this.dollars = pennies / PENNIES_IN_ONE_DOLLAR;
        pennies %= PENNIES_IN_ONE_DOLLAR;
        
        this.quarters = pennies / PENNIES_IN_ONE_QUARTER;
        pennies %= PENNIES_IN_ONE_QUARTER;
        
        this.dimes = pennies / PENNIES_IN_ONE_DIME;
        pennies %= PENNIES_IN_ONE_DIME;
        
        this.nickels = pennies / PENNIES_IN_ONE_NICKEL;
        pennies %= PENNIES_IN_ONE_NICKEL;
        
        this.pennies = pennies;
    }
    
    public Change(BigDecimal amountInDollars) {
        //we might have to throw an exception if scale is greater than 2
        this(amountInDollars.movePointRight(2).intValueExact());
    }

    public int getPennies() {
        return pennies;
    }

    public int getNickels() {
        return nickels;
    }

    public int getDimes() {
        return dimes;
    }

    public int getQuarters() {
        return quarters;
    }
    
    public int getDollars() {
        return dollars;
    }

    @Override
    public String toString() {
        return "Change{" + "pennies=" + pennies + ", nickels=" + nickels + ", dimes=" + dimes + ", quarters=" + quarters + ", dollars=" + dollars + '}';
    }  
}
