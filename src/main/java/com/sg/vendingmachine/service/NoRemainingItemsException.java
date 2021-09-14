package com.sg.vendingmachine.service;

/**
 *
 * @author Eyob
 */
public class NoRemainingItemsException extends Exception {
    public NoRemainingItemsException(String message) {
        super(message);
    }
}
