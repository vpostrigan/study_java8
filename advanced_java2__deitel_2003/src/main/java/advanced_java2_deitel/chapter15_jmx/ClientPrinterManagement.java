package advanced_java2_deitel.chapter15_jmx;

// Java core packages

import java.awt.*;
import java.awt.event.*;

// JMX core packages
import javax.management.*;

// JDMX core packages
//import com.sun.jdmk.comm.RmiConnectorClient;
//import com.sun.jdmk.comm.RmiConnectorAddress;

// Fig. 24.12: ClientPrinterManagement.java
// This application establishes a connection to the MBeanServer
// and creates an MBean for PrinterSimulator.
public class ClientPrinterManagement {
/*
    private RmiConnectorClient rmiClient;

    // instantiate client connection
    public ClientPrinterManagement() {
        // create connector client instance
        rmiClient = new RmiConnectorClient();

        // create address instance
        RmiConnectorAddress rmiAddress = new RmiConnectorAddress();

        // specify port
        rmiAddress.setPort(5555);

        // establish connection
        rmiClient.connect(rmiAddress);
    }

    // return RmiConnectorClient referrence
    public RmiConnectorClient getClient() {
        return rmiClient;
    }

    public static void main(String[] args) {
        // instantiate client connection
        ClientPrinterManagement clientManager = new ClientPrinterManagement();

        // get RMIConnectorClient handle
        RmiConnectorClient client = clientManager.getClient();

        // start GUI
        PrinterManagementGUI printerManagementGUI = new PrinterManagementGUI(client);

        // display the output
        printerManagementGUI.setSize(new Dimension(500, 500));
        printerManagementGUI.setVisible(true);
    }
*/
}