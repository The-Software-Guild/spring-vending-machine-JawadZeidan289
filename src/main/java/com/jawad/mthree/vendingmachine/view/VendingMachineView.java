package com.jawad.mthree.vendingmachine.view;

import com.jawad.mthree.vendingmachine.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendingMachineView {

    private final UserIO io;

    @Autowired
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(List<Product> productList) {
        io.print("Products:");

        int index = 1;
        for(Product p : productList) {
            if(p.getQuantity() > 0) {
                io.print(index + ". " + p.toString());
                index++;
            }
        }

        io.print("");
        io.print("1. Purchase Product");
        io.print("2. Insert Cash");
        io.print("3. Exit");

        return io.readInt("What would you like to do?", 1, 3);
    }

    public int getCashMenuSelection(double balance) {
        io.print("Your balance is £" + balance);
        io.print("");
        io.print("1. Insert Pounds");
        io.print("2. Insert 50Ps");
        io.print("3. Insert 20Ps");
        io.print("4. Insert 10Ps");
        io.print("5. Insert 5Ps");
        io.print("6. Insert 1Ps");
        io.print("7. Go back");
        return io.readInt("What would you like to do?", 1, 7);
    }

    public int getPoundInput() {
        return io.readInt("How many Pounds would you like to insert?");
    }

    public int get50PenceInput() {
        return io.readInt("How many 50 Pennies would you like to insert?");
    }

    public int get20PenceInput() {
        return io.readInt("How many 20 Pennies would you like to insert?");
    }

    public int get10PenceInput() {
        return io.readInt("How many 10 Pennies would you like to insert?");
    }

    public int get5PenceInput() {
        return io.readInt("How many 5 Pennies would you like to insert?");
    }

    public int get1PenceInput() {
        return io.readInt("How many 1 Pennies would you like to insert?");
    }

    public int getPurchaseOption() {
        return io.readInt("Which item would you like to buy?");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void printPurchaseSuccessMessage() {
        io.print("Transaction Successful!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayCashInsertionBanner() {
        io.print("=== Insert Cash ===");
    }

    public void print(String msg) {
        io.print(msg);
    }

    public void printErr(String msg) {
        io.printErr(msg);
    }

    public void displayChange(int numOfPounds, int numOf50, int numOf20, int numOf10, int numOf5, int numOf1) {
        io.print("Pounds: " + numOfPounds);
        io.print("50 Pennies: " + numOf50);
        io.print("20 Pennies: " + numOf20);
        io.print("10 Pennies: " + numOf10);
        io.print("5 Pennies: " + numOf5);
        io.print("1 Pennies: " + numOf1);
    }

    public void displayThankYouMessage() {
        io.print("Thanks for using the vending machine!");
    }
}
