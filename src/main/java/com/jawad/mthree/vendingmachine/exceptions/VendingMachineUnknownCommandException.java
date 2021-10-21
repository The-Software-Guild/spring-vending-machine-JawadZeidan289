package com.jawad.mthree.vendingmachine.exceptions;

public class VendingMachineUnknownCommandException extends Exception {

    public VendingMachineUnknownCommandException(String message) {
        super(message);
    }

    public VendingMachineUnknownCommandException(String message, Throwable cause) {
        super(message, cause);
    }

}
