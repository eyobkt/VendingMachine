package com.sg.vendingmachine.dao;

public class VendingMachineInvalidIdException extends Exception {
    public VendingMachineInvalidIdException(String message) {
        super(message);
    }
    
    public VendingMachineInvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
