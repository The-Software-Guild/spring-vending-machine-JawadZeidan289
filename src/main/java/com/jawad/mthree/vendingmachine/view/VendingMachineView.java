package com.jawad.mthree.vendingmachine.view;

import com.jawad.mthree.vendingmachine.model.Product;

import java.util.List;

public class VendingMachineView {

    private final UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection(List<Product> productList) {
        io.print("Products:");

        productList.stream().forEach(s -> io.print(s.toString()));

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
        return io.readInt("How many 50Ps would you like to insert?");
    }
    public int get20PenceInput() {
        return io.readInt("How many 20Ps would you like to insert?");
    }
    public int get10PenceInput() {
        return io.readInt("How many 10Ps would you like to insert?");
    }

    public int get5PenceInput() {
        return io.readInt("How many 5Ps would you like to insert?");
    }

    public int get1PenceInput() {
        return io.readInt("How many 1Ps would you like to insert?");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayCashInsertionBanner() {
        io.print("=== Insert Cash ===");
    }
}
