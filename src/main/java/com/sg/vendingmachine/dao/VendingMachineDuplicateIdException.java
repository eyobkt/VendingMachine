package com.sg.vendingmachine.dao;

public class VendingMachineDuplicateIdException extends Exception {
    public VendingMachineDuplicateIdException(String message) {
        super(message);
    }
    
    public VendingMachineDuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
