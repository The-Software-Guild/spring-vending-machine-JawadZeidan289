package com.jawad.mthree.vendingmachine.model;

public enum Change {
    POUND(1.0),
    FIFTY_PENCE(0.5),
    TWENTY_PENCE(0.2),
    TEN_PENCE(0.1),
    FIVE_PENCE(0.05),
    ONE_PENCE(0.01);

    public final double value;

    private Change(double value) {
        this.value = value;
    }

    public Change getChange() {
        return null;
    }
}