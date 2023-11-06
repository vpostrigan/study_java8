package advanced_java2_deitel.chapter16_jiro;

// Java core package

import java.rmi.*;

// Jiro packages
//import javax.fma.common.*;

public class PrinterManagementStarter {
/*
    // PrinterManagementStarter constructor
    public PrinterManagementStarter(String domain) {

        PrinterManagement managementProxy;

        // set security manager
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        // obtain station address
        StationAddress stationAddress =
                new StationAddress(domain, null, null, null, null, null, null, null);

        // get the proxies of dynamic services and start the services
        try {
            managementProxy = new PrinterManagementImplProxy(stationAddress);
        } catch (RemoteException exception) { // handle exception getting proxies and starting policies
            exception.printStackTrace();
        }
    }

    public static void main(String args[]) {
        String domain = "";

        // get the domain name
        if (args.length != 1) {
            System.out.println("Usage: PrinterManagementStarter Domain");
            System.exit(1);
        } else
            domain = args[0];

        PrinterManagementStarter printerManagementStarter = new PrinterManagementStarter(domain);
    }
*/
}