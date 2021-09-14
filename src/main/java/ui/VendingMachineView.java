/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

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
        return 0;
    }
    
    public void printInsufficientFund() {
        
    }
    
    public void printChange(List<String> changes) {
        // successful purchase, print change
        // changes[0] - dollar amount
        // changes[1] - quarter amount
        // changes[2] - dime amount
        // changes[3] - nickel amount
        // changes[4] - penny amount
    }
    
    public void printExitMessage() {
        // transaction finishes, exit program
    }
    
    
    
    
    
}
