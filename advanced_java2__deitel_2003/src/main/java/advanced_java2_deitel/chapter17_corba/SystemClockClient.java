package advanced_java2_deitel.chapter17_corba;

// Java core packages

import java.text.DateFormat;
import java.util.*;

// Java extension packages
import javax.swing.JOptionPane;

// OMG CORBA packages
import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class SystemClockClient implements Runnable {
    private SystemClock timeServer;

    // initialize client
    public SystemClockClient(String[] params) throws Exception {
        connectToTimeServer(params);
        startTimer();
    }

    // use NameService to connect to time server
    private void connectToTimeServer(String[] params)
            throws org.omg.CORBA.ORBPackage.InvalidName,
            org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {
        // Connect to SystemClock server
        ORB orb = ORB.init(params, null);

        org.omg.CORBA.Object corbaObject = orb.resolve_initial_references("NameService");
        NamingContext naming = NamingContextHelper.narrow(corbaObject);

        // Resolve object reference in naming
        NameComponent nameComponent = new NameComponent("TimeServer", "");
        NameComponent path[] = {nameComponent};
        corbaObject = naming.resolve(path);
        timeServer = SystemClockHelper.narrow(corbaObject);
    }

    // start timer thread
    private void startTimer() {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Talk to server on regular basis and display results
    public void run() {
        long time = 0;
        Date date = null;
        DateFormat format = DateFormat.getTimeInstance(DateFormat.LONG);
        String timeString = null;
        int response = 0;

        while (true) {
            time = timeServer.currentTimeMillis();
            date = new Date(time);
            timeString = format.format(date);

            response = JOptionPane.showConfirmDialog(null, timeString,
                    "SystemClock Example", JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.CANCEL_OPTION)
                break;  // Get us out of here
        }

        System.exit(0);
    }

    // Main method to execute client application
    public static void main(String args[]) {
        // create client
        try {
            new SystemClockClient(args);
        } catch (Exception exception) { // process exceptions that occur while client executes
            System.out.println("Exception thrown by SystemClockClient:");
            exception.printStackTrace();
        }
    }

}