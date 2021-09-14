package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private static final String DELIMITER = "||";
    private static final String ITEM_DELIMITER = "::";

    private final String vendingMachineFile;
    private final Map<Integer, Item> items = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        this.vendingMachineFile = "vendingmachine.txt";
    }
    
    public VendingMachineDaoFileImpl(String vendingMachineFile) {
        this.vendingMachineFile = vendingMachineFile;
    }

    @Override
    public void addItem(int id, Item item) 
            throws VendingMachinePersistenceException,
                   VendingMachineDuplicateIdException {
        loadItems();
        
        if (items.containsKey(id)) {
            throw new VendingMachineDuplicateIdException(
                    "The id value is already a key in our hashmap");
        }
        
        items.put(id, item);
        
        writeItems();
    }

    @Override
    public Optional<Item> getItem(int id) throws VendingMachinePersistenceException {
        loadItems();
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadItems();
        return new ArrayList(items.values());
    }

    @Override
    public void reduceItemInventory(int id) 
            throws VendingMachinePersistenceException,
                   VendingMachineInvalidIdException,
                   VendingMachineItemOutOfStockException {
        loadItems();
        Item item = items.get(id);
        
        if (item == null) {
            throw new VendingMachineInvalidIdException(
                    "id not present in hashmap");
        }
        
        if (item.getNumberOfItems() <= 0) {
            throw new VendingMachineItemOutOfStockException(
                    "there is no items in stock");
        }
        
        items.put(id, new Item(item.getName(),
                               item.getCost(),
                               item.getNumberOfItems() - 1));
        writeItems();
    }
    
    private void loadItems() throws VendingMachinePersistenceException {
        try (Scanner scanner = new Scanner(
                new BufferedReader(
                        new FileReader(vendingMachineFile)))) {
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine()
                                         .split(DELIMITER);
                items.put(Integer.valueOf(tokens[0]),
                          unmarshallItem(tokens[1]));
            }
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load items data into memory.", e);
        }
    }
    
    private void writeItems() throws VendingMachinePersistenceException {
        try (PrintWriter printWriter = new PrintWriter(
                new FileWriter(vendingMachineFile))) {
            for (Map.Entry<Integer, Item> pair : items.entrySet()) {
                printWriter.println(pair.getKey() + DELIMITER 
                        + marshallItem(pair.getValue()));
            }
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }
    }
    
    private String marshallItem(Item item){
        return item.getName() + ITEM_DELIMITER
                + item.getCost() + ITEM_DELIMITER
                + item.getNumberOfItems() + ITEM_DELIMITER;
    }
    
    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(ITEM_DELIMITER);
        return new Item(itemTokens[0],
                        new BigDecimal(itemTokens[1]),
                        Integer.parseInt(itemTokens[2]));
    }
}
