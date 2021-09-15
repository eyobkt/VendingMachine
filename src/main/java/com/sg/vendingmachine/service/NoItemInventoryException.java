package com.sg.vendingmachine.service;

/**
 *
 * @author Eyob
 */
public class NoItemInventoryException extends Exception {
    public NoItemInventoryException(String message) {
        super(message);
    }
}
