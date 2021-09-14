/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineService;
import java.math.BigDecimal;
import java.util.List;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author Eyob
 */
public class VendingMachineController {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
    
    private UserIO io = new UserIOConsoleImpl();
    
    private VendingView view;
    //private ItemDao dao;
    //replacing dao with service instead
    private VendingMachineService service; //variable of iterface?? oh so you can pass in any child type of that implements this interface.
    
    public VendingMachineController(VendingMachineService service, VendingView myView) {
        //this.dao = myDao;
        this.service = service;
        this.view = myView;
    }
    
    
    public void run(){
            boolean keepGoing = true;
            int menuSelection = 0;
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
            } catch (InsufficientFundsException e) {
                view.displayErrorMessage(e.getMessage());
            }
            }
    
    
    private int getMenuSelection() { //all function in controller are private because only called in here
        return view.printMenuAndGetSelection();
    }
    
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void listItems(){
        //List<Item> ItemList = dao.getAllItem();
        List<Item> ItemList = service.listItems();
        view.displayItemList(ItemList);
    }
    
    private void buyItem(){
        //view.displayRemoveStudentBanner();
        int itemId = view.getItemChoice();
        Item removedItem = service.buyItem(itemId);
        view.displayRemoveResult(removedItem);
    }
    
    private void putMoney(){
        String moneyAmount = view.getMoney();
        service.putMoney(moneyAmount);
    }

   
    
}
