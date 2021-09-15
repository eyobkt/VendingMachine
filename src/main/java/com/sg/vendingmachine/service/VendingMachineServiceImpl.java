package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
    public List<Item> listItems() throws NoRemainingItemsException, 
            VendingMachinePersistenceException {
        
        List<Item> allItems = vendingMachineDao.getAllItems();
        
        if (allItems.isEmpty()) {
            throw new NoRemainingItemsException("Sorry, there are no remaining "
                    + "items in the vending machine");
        } else {
            return allItems;            
        }
    }
    
    public void putMoney(BigDecimal moneyAmount) {
        this.moneyAmount = this.moneyAmount.add(moneyAmount);
    }
    
    @Override
    public Change buyItem(BigDecimal moneyAmount, int itemId) throws InsufficientFundsException, 
            VendingMachinePersistenceException {
        
        Item item = vendingMachineDao.getItem(itemId).get();
        
        setMoneyAmountToZero();
        
        if (item.getCost().compareTo(moneyAmount) > 0) {
            throw new InsufficientFundsException("There are not enough funds to purchase this item");
        } else {
            vendingMachineDao.reduceItemInventory(itemId);
            return new Change(item.getCost());
        }
    } 
    
    private void setMoneyAmountToZero() {
        this.moneyAmount = new BigDecimal("0");
        this.moneyAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
