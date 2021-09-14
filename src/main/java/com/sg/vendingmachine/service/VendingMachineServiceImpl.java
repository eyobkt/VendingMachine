package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eyob
 */
public class VendingMachineServiceImpl implements VendingMachineService {
    private final VendingMachineDao vendingMachineDao;

    public VendingMachineServiceImpl(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
    }
    
    @Override
    public List<Item> getAllItems() throws NoRemainingItemsException, 
            VendingMachinePersistenceException {
        
        List<Item> allItems = vendingMachineDao.getAllItems();
        
        if (allItems.isEmpty()) {
            throw new NoRemainingItemsException("Sorry, there are no remaining "
                    + "items in the vending machine");
        } else {
            return vendingMachineDao.getAllItems();            
        }
    }
    
    @Override
    public Change purchaseItem(BigDecimal amount, int itemId) throws InsufficientFundsException, 
            NoItemInventoryException, VendingMachinePersistenceException {
        
        Item item = vendingMachineDao.getItem(itemId);
        
        
    } 
}
