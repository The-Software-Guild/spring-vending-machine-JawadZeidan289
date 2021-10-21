package com.jawad.mthree.vendingmachine.model;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;

import java.util.List;

public interface VendingMachineDao {

    boolean addProduct(String title, Product dvd) throws VendingMachinePersistenceException;

    List<Product> getProducts() throws VendingMachinePersistenceException;

}
