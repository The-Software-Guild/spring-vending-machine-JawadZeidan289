package com.jawad.mthree.vendingmachine.exceptions;

public class VendingMachineCurrencyException extends Exception {

    public VendingMachineCurrencyException(String message) {
        super(message);
    }

    public VendingMachineCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }
}