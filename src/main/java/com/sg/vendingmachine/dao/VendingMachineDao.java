package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

public interface VendingMachineDao {
    Item addItem(int id, Item item) throws VendingMachinePersistenceException;
    Item getItem(int id) throws VendingMachinePersistenceException;
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    void buyItem(int id) throws VendingMachinePersistenceException;
}