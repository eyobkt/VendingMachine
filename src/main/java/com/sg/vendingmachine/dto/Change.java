package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

public class Change {
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private int dollars;
    
    public Change(int pennies) {
        dollars = pennies / PenniesInOne.DOLLAR.getValue();
        pennies %= PenniesInOne.DOLLAR.getValue();
        
        quarters = pennies / PenniesInOne.QUARTER.getValue();
        pennies %= PenniesInOne.QUARTER.getValue();
        
        dimes = pennies / PenniesInOne.DIME.getValue();
        pennies %= PenniesInOne.DIME.getValue();
        
        nickels = pennies / PenniesInOne.NICKEL.getValue();
        pennies %= PenniesInOne.NICKEL.getValue();
        
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
