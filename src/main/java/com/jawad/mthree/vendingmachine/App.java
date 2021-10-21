package com.jawad.mthree.vendingmachine;

import com.jawad.mthree.vendingmachine.controller.VendingMachineController;
import com.jawad.mthree.vendingmachine.model.VendingMachineAuditDao;
import com.jawad.mthree.vendingmachine.model.VendingMachineAuditDaoImpl;
import com.jawad.mthree.vendingmachine.model.VendingMachineDao;
import com.jawad.mthree.vendingmachine.model.VendingMachineDaoImpl;
import com.jawad.mthree.vendingmachine.service.VendingMachineService;
import com.jawad.mthree.vendingmachine.service.VendingMachineServiceImpl;
import com.jawad.mthree.vendingmachine.view.UserIO;
import com.jawad.mthree.vendingmachine.view.UserIOConsoleImpl;
import com.jawad.mthree.vendingmachine.view.VendingMachineView;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
}
