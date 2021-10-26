package com.jawad.mthree.vendingmachine.service;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;
import com.jawad.mthree.vendingmachine.model.Product;
import com.jawad.mthree.vendingmachine.model.VendingMachineAuditDao;
import com.jawad.mthree.vendingmachine.model.VendingMachineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendingMachineServiceImpl implements VendingMachineService {

    private double balance = 0;
    private final VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    @Autowired
    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Product> getProductList() throws VendingMachinePersistenceException {
        return dao.getProducts();
    }

    public void decrementStock(Product product) throws VendingMachinePersistenceException {
        dao.decrementStock(product);
        if(product.getQuantity() < 1) {
            dao.removeProduct(product);
            dao.addProduct(product);
        }
        auditDao.writeAuditEntry("Product " + product.getName() + " bought. New quantity is " + product.getQuantity());
    }
}
