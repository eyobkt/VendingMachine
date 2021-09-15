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
    List<Item> listItems() throws NoRemainingItemsException, 
            VendingMachinePersistenceException;
    
    Change buyItem(BigDecimal amount, int itemId) throws InsufficientFundsException, 
            VendingMachinePersistenceException;
}
