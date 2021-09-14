package com.sg.vendingmachine.service;

/**
 *
 * @author Eyob
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException()) {
        super("Sorry, not enough funds to make this purchase");
    }
}
