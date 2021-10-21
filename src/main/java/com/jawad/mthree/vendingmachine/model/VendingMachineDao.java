package com.jawad.mthree.vendingmachine.model;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;

import java.util.List;

public interface VendingMachineDao {

    List<Product> getProducts() throws VendingMachinePersistenceException;

    void decrementStock(Product product) throws VendingMachinePersistenceException;

    void removeProduct(Product product) throws VendingMachinePersistenceException;

    void addProduct(Product product) throws VendingMachinePersistenceException;

}
