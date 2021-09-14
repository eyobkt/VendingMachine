package com.sg.vendingmachine.dto;

/**
 *
 * @author Eyob
 */
public class Change {
    int pennies;
    int nickels;
    int dimes;
    int quarters;
    int dollars;
    
    public Change(int pennies) {
        
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
}
