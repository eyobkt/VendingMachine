package com.sg.vendingmachine.service;

/**
 *
 * @author Eyob
 */
public interface VendingMachineService {
    
    Change purchaseItem(BigDecimal amount, String itemName) throws InsufficientFundsException, 
            NoItemInventoryException {
        
    }
}
