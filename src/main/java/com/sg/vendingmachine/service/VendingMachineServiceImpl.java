package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineInvalidIdException;
import com.sg.vendingmachine.dao.VendingMachineItemOutOfStockException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eyob
 */
public class VendingMachineServiceImpl implements VendingMachineService {
    private final VendingMachineDao vendingMachineDao;
    private BigDecimal moneyAmount;

    public VendingMachineServiceImpl(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
        
        setMoneyAmountToZero();
    }
    
    @Override
    public Map<Integer, Item> getMenuSelection() throws NoRemainingItemsException {        
        Map<Integer, Item> allItems = null;
        
        try {
            allItems = vendingMachineDao.getItemMap();
        } catch (VendingMachinePersistenceException ex) {
            ex.printStackTrace();
        }
        
        if (allItems.isEmpty()) {
            throw new NoRemainingItemsException("Sorry, there are no remaining "
                    + "items in the vending machine");
        } else {
            return allItems;            
        }
    }
    
    @Override
    public Map<Integer, Item> listItems() throws NoRemainingItemsException {
        
        return getMenuSelection();
    }
    
    public void putMoney(BigDecimal moneyAmount) {
        this.moneyAmount = this.moneyAmount.add(moneyAmount);
    }
    
    @Override
    public Change buyItem(BigDecimal moneyAmount, int itemId) throws InsufficientFundsException {        
        Item item = null;
        
        try {
            item = vendingMachineDao.getItem(itemId).get();
        } catch (VendingMachinePersistenceException ex) {
           ex.printStackTrace();
        }
        
        setMoneyAmountToZero();
        
        if (item.getCost().compareTo(moneyAmount) > 0) {
            throw new InsufficientFundsException("There are not enough funds to purchase this item");
        } else {
            try {
                vendingMachineDao.reduceItemInventory(itemId);
            } catch (VendingMachinePersistenceException | VendingMachineInvalidIdException | 
                    VendingMachineItemOutOfStockException ex) {
                
                ex.printStackTrace();
            }
            
            return new Change(item.getCost());
        }
    } 
    
    private void setMoneyAmountToZero() {
        this.moneyAmount = new BigDecimal("0");
        this.moneyAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
