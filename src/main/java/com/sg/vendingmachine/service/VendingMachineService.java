package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;

/**
 *
 * @author Eyob
 */
public interface VendingMachineService {
    
    Change purchaseItem(BigDecimal amount, String itemName) throws InsufficientFundsException, 
            NoItemInventoryException;
}
