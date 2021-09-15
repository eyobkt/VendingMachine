package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.Map;
import java.util.Optional;

public interface VendingMachineDao {
    void addItem(int id, Item item) 
            throws VendingMachinePersistenceException,
                   VendingMachineDuplicateIdException;
    
    Optional<Item> getItem(int id) throws VendingMachinePersistenceException;
    
    Map<Integer, Item> getItemMap() throws VendingMachinePersistenceException;
    
    void reduceItemInventory(int id) 
            throws VendingMachinePersistenceException,
                   VendingMachineInvalidIdException,
                   VendingMachineItemOutOfStockException;
}