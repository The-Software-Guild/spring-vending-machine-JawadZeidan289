package main.java.com.jawad.mthree.vendingmachine.model;

import main.java.com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;

import java.util.List;

public interface VendingMachineDao {

    boolean addProduct(String title, Product dvd) throws VendingMachinePersistenceException;

}
