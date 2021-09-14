package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dto.Change;
import java.math.BigDecimal;

/**
 *
 * @author Eyob
 */
public class VendingMachineServiceImpl implements VendingMachineService {
    private VendingMachineDao vendingMachineDao;

    public VendingMachineServiceImpl(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
    }
    
    Change purchaseItem(BigDecimal amount, String itemName) throws InsufficientFundsException, 
            NoItemInventoryException 
}
