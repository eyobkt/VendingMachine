package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineInvalidIdException;
import com.sg.vendingmachine.dao.VendingMachineItemOutOfStockException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;

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
    public Map<Integer, Item> listItems() throws NoRemainingItemsException {        
        Map<Integer, Item> allItems = null;
        
        try {
            allItems = vendingMachineDao.getItemMap();
            
            for (Integer itemId : allItems.keySet()) {
                if (allItems.get(itemId).getNumberOfItems() == 0) {
                    allItems.remove(itemId);
                }
            }
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
    public void putMoney(String moneyAmount) {
        BigDecimal moneyToAdd = new BigDecimal(moneyAmount);
        moneyToAdd.setScale(2, RoundingMode.HALF_UP);
    
        this.moneyAmount = this.moneyAmount.add(moneyToAdd);
    }
    
    @Override
    public Change buyItem(int itemId) throws InsufficientFundsException, 
            VendingMachineInvalidIdException, VendingMachineItemOutOfStockException {     
        
        Item item = null;
        
        try {
            Optional<Item> optionalItem = vendingMachineDao.getItem(itemId);
            
            if (optionalItem.isPresent()) {
                item = optionalItem.get();
            } else {
                throw new VendingMachineInvalidIdException("Item doesn't exist");
            }
          
            if (item.getNumberOfItems() == 0) {
                throw new VendingMachineItemOutOfStockException("Item out of stock");
            }

        } catch (VendingMachinePersistenceException ex) {
           ex.printStackTrace();
        }
        
        if (item.getCost().compareTo(moneyAmount) > 0) {
            setMoneyAmountToZero();
            throw new InsufficientFundsException("There are not enough funds to purchase this item");
        } else {
            try {
                vendingMachineDao.reduceItemInventory(itemId);
            } catch (VendingMachinePersistenceException | VendingMachineInvalidIdException | 
                    VendingMachineItemOutOfStockException ex) {
                
                ex.printStackTrace();
            }
            
            BigDecimal changeAmount = moneyAmount.subtract(item.getCost());
            changeAmount.setScale(2, RoundingMode.HALF_UP);
            
            setMoneyAmountToZero();
                    
            return new Change(changeAmount);
        }
    } 
    
    private void setMoneyAmountToZero() {
        moneyAmount = new BigDecimal("0");
        moneyAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
