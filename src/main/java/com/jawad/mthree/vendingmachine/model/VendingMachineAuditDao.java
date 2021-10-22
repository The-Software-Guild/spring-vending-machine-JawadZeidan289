package com.jawad.mthree.vendingmachine.model;

import com.jawad.mthree.vendingmachine.exceptions.VendingMachinePersistenceException;

public interface VendingMachineAuditDao {

    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
