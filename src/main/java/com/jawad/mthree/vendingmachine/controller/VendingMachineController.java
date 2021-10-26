package com.jawad.mthree.vendingmachine.controller;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;
import com.jawad.mthree.vendingmachine.model.Change;
import com.jawad.mthree.vendingmachine.model.Product;
import com.jawad.mthree.vendingmachine.service.VendingMachineService;
import com.jawad.mthree.vendingmachine.view.VendingMachineView;

import java.util.List;

public class VendingMachineController {

    private final VendingMachineView view;
    private final VendingMachineService service;
    private List<Product> productList;

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        int menuSelection = 0;
        try {
            while(running) {
                productList = service.getProductList();
                view.print("");
                printBalance();
                view.print("");
                menuSelection = getMenuSelection(productList);

                switch (menuSelection) {
                    case 1:
                        // purchase
                        if(purchaseItem()) {
                            returnChange();
                            running = false;
                        }
                        break;
                    case 2:
                        // insert cash
                        insertCash();
                        break;
                    case 3:
                        // exit
                        returnChange();
                        running = false;
                        break;
                    default:
                        throw new VendingMachinePersistenceException("Unknown Command");
                }
            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private boolean purchaseItem() {
        int option = view.getPurchaseOption();
        try {
            Product chosenProduct = productList.get(option-1);
            double productPrice = chosenProduct.getPrice();
            double currentBalance = service.getBalance();
            view.print("Chosen Product:\n" + chosenProduct.toString());
            if(chosenProduct.getQuantity() < 1) {
                if(currentBalance > productPrice) {
                    service.setBalance(currentBalance - productPrice);
                    service.decrementStock(chosenProduct);
                    view.printPurchaseSuccessMessage();
                    return true;
                } else {
                    view.displayErrorMessage("Transaction Failed: Insufficient balance!");
                }
            } else {
                throw new VendingMachinePersistenceException("Option does not exist!");
            }
        } catch(Exception e) {
            view.displayErrorMessage("Option does not exist!");
            return false;
        }
        return false;
    }

    private void insertCash() {
        view.print("");
        view.displayCashInsertionBanner();
        boolean running = true;
        int menuSelection = 0;
        while(running) {
            double balance = service.getBalance();
            menuSelection = view.getCashMenuSelection(balance);
            switch (menuSelection) {
                case 1:
                    // insert Pounds
                    int pounds = view.getPoundInput();
                    service.setBalance(balance + (Change.POUND.value * pounds));
                    break;
                case 2:
                    // insert 50p
                    int fiftyPS = view.get50PenceInput();
                    service.setBalance(balance + (Change.FIFTY_PENCE.value * fiftyPS));
                    break;
                case 3:
                    // insert 20p
                    int twentyPs = view.get20PenceInput();
                    service.setBalance(balance + (Change.TWENTY_PENCE.value * twentyPs));
                    break;
                case 4:
                    // insert 10p
                    int tenPs = view.get10PenceInput();
                    service.setBalance(balance + (Change.TEN_PENCE.value * tenPs));
                    break;
                case 5:
                    // insert 5p
                    int fivePs = view.get5PenceInput();
                    service.setBalance(balance + (Change.FIVE_PENCE.value * fivePs));
                    break;
                case 6:
                    // insert 1p
                    int onePs = view.get1PenceInput();
                    service.setBalance(balance + (Change.ONE_PENCE.value * onePs));
                    break;
                case 7:
                    // exit
                    running = false;
                    break;
            }
        }
    }

    private int getMenuSelection(List<Product> productList) {
        return view.printMenuAndGetSelection(productList);
    }

    private void printBalance() {
        double balance = service.getBalance();
        view.print("Your balance is £" + balance);
    }

    private List<Product> getProducts() throws VendingMachinePersistenceException {
        return service.getProductList();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void returnChange() {
        view.print("");
        view.print("Here is your change:\n");
        double balance = service.getBalance();
        int numOfPounds = (int) Math.floor(balance);
        balance -= numOfPounds;

        int numOf50 = 0;
        while(balance - Change.FIFTY_PENCE.value > 0.00) {
            balance = balance - Change.FIFTY_PENCE.value;
            numOf50++;
        }

        int numOf20 = 0;
        while(balance - Change.TWENTY_PENCE.value > 0.00) {
            balance = balance - Change.TWENTY_PENCE.value;
            numOf20++;
        }

        int numOf10 = 0;
        while(balance - Change.TEN_PENCE.value > 0.00) {
            balance = balance - Change.TEN_PENCE.value;
            numOf10++;
        }

        int numOf5 = 0;
        while(balance - Change.FIVE_PENCE.value > 0.00) {
            balance = balance - Change.FIVE_PENCE.value;
            numOf5++;
        }

        int numOf1 = 0;
        while(balance - Change.ONE_PENCE.value > 0.00) {
            balance = balance - Change.ONE_PENCE.value;
            numOf1++;
        }

        view.displayChange(numOfPounds,numOf50,numOf20,numOf10,numOf5,numOf1);
        view.print("");
        view.displayThankYouMessage();
    }

}
