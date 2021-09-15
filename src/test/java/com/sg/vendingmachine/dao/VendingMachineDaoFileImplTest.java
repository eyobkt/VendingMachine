package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoFileImplTest {
    private VendingMachineDao dao;
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "vendingmachinetest.txt";
        new FileWriter(testFile); // blank the file
        dao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetItem() throws Exception {
        Item item = new Item("chocolate", new BigDecimal("2.25"), 5);
        dao.addItem(0, item);
        
        Item retrievedItem = dao.getItem(0).get();
        
        assertTrue(item.equals(retrievedItem), "Checking item");
    }
    
    @Test
    public void testAddGetItemMap() throws Exception {
        Item firstItem = new Item("chocolate bar", new BigDecimal("2.25"), 5);
        Item secondItem = new Item("cookie", new BigDecimal("1.75"), 3);
        
        dao.addItem(0, firstItem);
        dao.addItem(1, secondItem);
        Map allItems = dao.getItemMap();
        
        // Check Map
        assertNotNull(allItems, "allItems map must not null");
        assertEquals(2, allItems.size(),"allItems map should contain two items");
        
        // Check Items
        assertTrue(dao.getItemMap().containsValue(firstItem),
                "allItems map should include the firstItem");
        assertTrue(dao.getItemMap().containsValue(secondItem),
                "allItems map should include the firstItem");
    }
    
    @Test
    public void testReduceItemInventory() throws Exception {
        Item item = new Item("chocolate", new BigDecimal("2.25"), 5);
        dao.addItem(0, item);
        
        Item retrievedItem = dao.getItem(0).get();
        assertEquals(5, retrievedItem.getNumberOfItems(),
                "item should have an inventory of five");
        
        dao.reduceItemInventory(0);
        retrievedItem = dao.getItem(0).get();
        assertEquals(4, retrievedItem.getNumberOfItems(),
                "item should have an inventory of four");
    }
}
