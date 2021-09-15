package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoRemainingItemsException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.util.Map;

/**
 *
 * @author Eyob
 */
public class VendingMachineController {    
    private VendingMachineView view;
    private VendingMachineService service; 
    
    public VendingMachineController(VendingMachineService service, VendingMachineView myView) {
        this.service = service;
        this.view = myView;
    }    
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        listItems();

        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listItems();
                        break;
                    case 2:
                        putMoney();
                        break;
                    case 3:
                        buyItem();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() { 
        return view.printMenuAndGetSelection();
    }

    private void listItems(){
        Map<Integer, Item> ItemMap = null;
        
        try {
            ItemMap = service.listItems(); //returns a map, the view needs to handle print out this map with item id
        } catch (NoRemainingItemsException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
     
        view.displayItemList(ItemMap);
    }
    
    private void buyItem(){        
        boolean hasErrors = false;
        
        do {
            int itemId = view.getItemChoice();
            try {
                Change myChange = service.buyItem(itemId);    
                
                view.printChange(myChange, itemId);
                hasErrors = false;
            } catch (InsufficientFundsException | NoRemainingItemsException e) { //these two exceptions should be in buyItem
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    private void putMoney(){
        String moneyAmount = view.getMoney();
        service.putMoney(moneyAmount);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
     private void exitMessage() {
        view.displayExitBanner();
    }
}
