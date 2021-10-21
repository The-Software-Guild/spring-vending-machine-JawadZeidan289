package com.jawad.mthree.vendingmachine.service;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;
import com.jawad.mthree.vendingmachine.model.Product;

import java.util.List;

public interface VendingMachineService {

    double getBalance();

    void setBalance(double balance);

    List<Product> getProductList() throws VendingMachinePersistenceException;

    void decrementStock(Product product) throws VendingMachinePersistenceException;
}
