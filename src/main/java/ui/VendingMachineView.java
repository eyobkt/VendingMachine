/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author ychen
 */
public class VendingMachineView {
    
        private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection(List<Item> inventory) {
        // 1 - continue to purchase
        // 2 - exit
        io.print("Welcome to Vending Machine");
        io.print("Today, we are offering the below items: ");
        int i = 1;
        for (Item item : inventory) {
            System.out.println(i + "  " + item.getName() + " .... " + "$" +item.getCost().toString());
        }
        
        System.out.println("");
        //System.out.println("You may perform the fo");
        return io.readInt("Please enter 1 to proceed, or enter 2 to exit", 1, 2);
    }
    
    public double printMoneyDeposit() {
        // ask user the amount of money to input
        return io.readDouble("Please insert money: ");
    }
    
    public int printItemSelection() {
        // ask user for the item ID
        return io.readInt("Please enter the ID of the item you want to purchase: ");
    }
    
    public int printSelectionError() {
        return io.readInt("Invalid input. Please enter the ID of the item you want to purchase: ");
    }
    
    public void printInsufficientFund(String curDeposit) {
        io.print("Not sufficient deposit for the item chosen");
        io.print("Current deposit available: $" + curDeposit);
    }
    
    public void printChange(Change change) {
        // successful purchase, print change
        io.print("The item is being dropped. Here are your changes: ");
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
            changeMessage += pennyAmount + "penny(s)";
        }
        
        io.print(changeMessage);

    }
    
    public void printExitMessage() {
        // transaction finishes, exit program
        io.print("Thank you for your purchase!");
        io.print("Have a nice day!");
    }
    
    
    
    
    
}
