/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

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
                        case 2:
                            createItem();
                            break;
                        case 3:
                            viewItem();
                            break;
                        case 4:
                            removeItem();
                            break;
                        case 5:
                            editItem();
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
    
    private Item createItem() throws InsufficientFundsException { //this is where we catch the other 2 exceptions
        //view.displayCreateStudentBanner();
        //dao.addItem(newStudent.getTitle(), newStudent);
        //interface throws exceptions for function need be be thrown aagin or handled
        Item newStudent;
        boolean hasErrors = false;
        
        do {
            newStudent = view.getNewItemInfo();
            try {
                service.addItem(newStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ItemDuplicateIdException | ItemDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
        
        return newStudent;
        
        
        
        
        
        
        
        
        
        
    }
    
    private void viewItem() throws InsufficientFundsException {
        //view.displayDisplayStudentBanner();
        String studentId = view.getItemChoice();
        Item student = service.getItem(studentId);
        view.displayStudent(student);
    }
    
    
    private void removeItem() throws InsufficientFundsException {
        //view.displayRemoveStudentBanner();
        String studentId = view.getItemChoice();
        Item removedStudent = service.removeItem(studentId);
        view.displayRemoveResult(removedStudent);
    }
    private void editItem() throws InsufficientFundsException{
        /**
         * Adding in a Item and removing a existing one. if remove success, edit is sucess.
         * 
         */
        
        String studentId = view.getItemChoice();
        view.displayEditBanner(studentId);
        
        Item newItem = createItem();
        
        Item removedStudent = service.removeItem(studentId);
        if(removedStudent==null){
            service.removeItem(newItem.getTitle());
        }
        view.displayChangeResult(removedStudent);
        
    }
   
    
}
