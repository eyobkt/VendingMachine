package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineInvalidIdException;
import com.sg.vendingmachine.dao.VendingMachineItemOutOfStockException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.util.Map;

/**
 *
 * @author Eyob
 */
public interface VendingMachineService {    
    Map<Integer, Item> listItems() throws NoRemainingItemsException;
    
    void putMoney(String moneyAmount);
    
    Change buyItem(int itemId) throws InsufficientFundsException, VendingMachineInvalidIdException, 
            VendingMachineItemOutOfStockException;
}
