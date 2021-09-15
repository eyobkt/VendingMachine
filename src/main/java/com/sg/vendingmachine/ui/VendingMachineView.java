/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ychen
 */
public class VendingMachineView {
    
        private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        // 1 - continue to purchase
        // 2 - exit
        io.print("Welcome to Vending Machine");
        io.print("1. List Available Items");
        io.print("2. Insert Money");
        io.print("3. Choose an Item");
        io.print("4. Exit");

        return io.readInt("Please select from the above choices.", 1, 4);
        
    }
    
    public void displayItemList(Map<Integer, Item> inventory) {
        io.print("Today, we are offering the below items: ");
        //int i = 1;
        Set<Integer> keys = inventory.keySet();
        for (Integer itemID : keys) {
            Item item = inventory.get(itemID);
            System.out.println(itemID.toString() + "  " + item.getName() + " .... " + "$" +item.getCost().toString() + 
                    " " + item.getNumberOfItems() + " left");
        }
        
        System.out.println("");
    }
    
    public String getMoney() {
        // ask user the amount of money to input
        return io.readString("Please insert money: ");
    }
    
    public int getItemChoice() {
        // ask user for the item ID
        return io.readInt("Please enter the ID of the item you want to purchase: ");
    }
    
    public int printSelectionError() {
        return io.readInt("Invalid input. Please enter the ID of the item you want to purchase: ");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void printInsufficientFund(String curDeposit) {
        io.print("Not sufficient deposit for the item chosen");
        io.print("Current deposit available: $" + curDeposit);
    }
    
    public void printChange(Change change, int itemID) {
        // successful purchase, print change
        io.print("The item " + itemID +" is being dropped. Here are your changes: ");
        String changeMessage = "";
        int dollarAmount = change.getDollars();
        if (dollarAmount > 0) {
            changeMessage += dollarAmount + " dollar(s)";
        }
        
        int quarterAmount = change.getQuarters();
        if (quarterAmount > 0) {
            if (changeMessage.length() > 0) {
                changeMessage += ", ";
            }
            changeMessage += quarterAmount + " quarter(s)";
        }
        
        int dimeAmount = change.getDimes();
        if (dimeAmount > 0) {
            if (changeMessage.length() > 0) {
                changeMessage += ", ";
            }
            changeMessage += dimeAmount + " dime(s)";
        }
        
        int nickelAmount = change.getNickels();
        if (nickelAmount > 0) {
            if (changeMessage.length() > 0) {
                changeMessage += ", ";
            }
            changeMessage += nickelAmount + " nickel(s)";
        }
        
        int pennyAmount = change.getPennies();
        if (pennyAmount > 0) {
            if (changeMessage.length() > 0) {
                changeMessage += ", ";
            }
            changeMessage += pennyAmount + " penny(s)";
        }
        
        io.print(changeMessage);

    }
    
    public void displayExitBanner() {
        // transaction finishes, exit program
        io.print("Thank you for your purchase!");
        io.print("Have a nice day!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    
    
    
    
}
