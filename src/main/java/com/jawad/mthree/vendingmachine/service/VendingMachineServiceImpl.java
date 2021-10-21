package com.jawad.mthree.vendingmachine.service;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;
import com.jawad.mthree.vendingmachine.model.Product;
import com.jawad.mthree.vendingmachine.model.VendingMachineAuditDao;
import com.jawad.mthree.vendingmachine.model.VendingMachineDao;

import java.util.List;

public class VendingMachineServiceImpl implements VendingMachineService {

    private double balance = 0;
    private final VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        System.out.println("whent thru set balance method");
        this.balance = balance;
    }

    public List<Product> getProductList() throws VendingMachinePersistenceException {
        return dao.getProducts();
    }
}
