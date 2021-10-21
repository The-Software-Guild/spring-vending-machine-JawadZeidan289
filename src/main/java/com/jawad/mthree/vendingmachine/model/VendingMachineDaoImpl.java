package com.jawad.mthree.vendingmachine.model;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoImpl implements VendingMachineDao {

    private final Map<String, Product> products = new HashMap<>();
    public final String ROSTER_FILE;
    public static final String DELIMITER = "::";

    public VendingMachineDaoImpl() {
        ROSTER_FILE= "roster.txt";
    }

    @Override
    public boolean addProduct(String title, Product dvd) throws VendingMachinePersistenceException {
        return false;
    }

    public List<Product> getProducts() throws VendingMachinePersistenceException {
        return new ArrayList(products.values());
    }



}
