package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eyob
 */
public interface VendingMachineService {
    List<Item> getAllItems() throws NoRemainingItemsException, 
            VendingMachinePersistenceException;
    
    Change purchaseItem(BigDecimal amount, int itemId) throws InsufficientFundsException, 
            NoItemInventoryException, VendingMachinePersistenceException;
}
