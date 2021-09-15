package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author Eyob
 */
public interface VendingMachineService {    
    Map<Integer, Item> listItems() throws NoRemainingItemsException;
    
    void putMoney(BigDecimal moneyAmount);
    
    Change buyItem(BigDecimal amount, int itemId) throws InsufficientFundsException, NoRemainingItemsException;
}
