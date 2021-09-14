package com.sg.vendingmachine.service;

/**
 *
 * @author Eyob
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
