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

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        int menuSelection = 0;
        try {
            while(running) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        // purchase
                        break;
                    case 2:
                        // insert cash
                        insertCash();
                        break;
                    case 3:
                        // exit
                        running = false;
                        break;
                    default:
                        throw new VendingMachinePersistenceException("Unknown Command");
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void insertCash() {
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


    private int getMenuSelection() throws VendingMachinePersistenceException {
        List<Product> productList = service.getProductList();
        return view.printMenuAndGetSelection(productList);
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
