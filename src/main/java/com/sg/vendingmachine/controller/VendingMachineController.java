/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

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
    private VendingService service; //variable of iterface?? oh so you can pass in any child type of that implements this interface.
    
    public VendingMachineController(VendingService service, VendingView myView) {
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
                        
                        case 4:
                            removeItem();
                            break;
                       
                        case 6:
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
    
    private void listItems() throws InsufficientFundsException{
        //List<Item> ItemList = dao.getAllItem();
        List<Item> ItemList = service.getAllItem();
        view.displayStudentList(ItemList);
    }
    
    private void removeItem() throws InsufficientFundsException {
        //view.displayRemoveStudentBanner();
        String studentId = view.getItemChoice();
        Item removedStudent = service.removeItem(studentId);
        view.displayRemoveResult(removedStudent);
    }

   
    
}
