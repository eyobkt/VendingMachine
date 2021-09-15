package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);

        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineService service = new VendingMachineServiceImpl(dao);
        
        VendingMachineController controller = new VendingMachineController(service, view);
        controller.run();
    }
}
