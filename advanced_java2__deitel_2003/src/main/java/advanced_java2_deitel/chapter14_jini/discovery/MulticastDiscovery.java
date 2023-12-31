package advanced_java2_deitel.chapter14_jini.discovery;

// Java core packages

import java.rmi.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;

// Jini core packages
//import net.jini.core.lookup.ServiceRegistrar;
// Jini extension packages
//import net.jini.discovery.*;

public class MulticastDiscovery extends JFrame/* implements DiscoveryListener*/ {
/*
    // number of lookup services discovered through multicast
    private int servicesFound = 0;

    private JTextArea outputArea = new JTextArea(10, 20);

    // MulticastDiscovery constructor
    public MulticastDiscovery() {
        super("MulticastDiscovery");

        // discover lookup services in public group using multicast
        try {
            LookupDiscovery lookupDiscovery = new LookupDiscovery(new String[]{""});

            outputArea.append("Finding lookup services for public group ...\n");

            // listen for DiscoveryEvents
            lookupDiscovery.addDiscoveryListener(this);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        getContentPane().add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    // receive notification of found lookup services
    public void discovered(DiscoveryEvent event) {
        // get the ServiceRegistrars for found lookup services
        ServiceRegistrar[] registrars = event.getRegistrars();
        int order = 0;

        // get the information for each lookup service found
        for (int i = 0; i < registrars.length; i++) {
            ServiceRegistrar registrar = registrars[i];

            if (registrar != null) {

                // append information about discovered services to outputArea
                try {
                    order = servicesFound + i + 1;

                    // get the hostname and port number
                    Runnable appender = new TextAppender(
                            "Lookup Service " + order + ":" +
                                    "\n  Host: " + registrar.getLocator().getHost() + "\n" +
                                    "\n  Port: " + registrar.getLocator().getPort() + "\n" +
                                    "  Group support: ");

                    // append to outputArea on event-dispatch thread
                    SwingUtilities.invokeLater(appender);

                    // get the group(s) the lookup service served
                    String[] groups = registrar.getGroups();

                    StringBuffer names = new StringBuffer();

                    // get the group names, if empty write public
                    for (int j = 0; j < groups.length; j++) {

                        if (groups[j].equals(""))
                            names.append("public\t");
                        else
                            names.append(groups[j] + "\t");
                    }

                    // append group names to outputArea
                    SwingUtilities.invokeLater(new TextAppender(names + "\n"));
                } catch (RemoteException exception) { // handle exception communicating with ServiceRegistrar
                    exception.printStackTrace();
                }
            }
        }

        // update number of services found
        servicesFound = order;
    }

    // receive notification of discarded lookup services that are no longer valid
    public void discarded(DiscoveryEvent event) {
        ServiceRegistrar[] discardedRegistrars = event.getRegistrars();

        SwingUtilities.invokeLater(
                new TextAppender("Number of discarded registrars: " + discardedRegistrars.length + "\n"));
    }

    // TextAppender is a Runnable class for appending
    // text to outputArea on the event-dispatching thread.
    private class TextAppender implements Runnable {

        private String textToAppend; // text to append to outputArea

        // TextAppender constructor
        public TextAppender(String text) {
            textToAppend = text;
        }

        // append text to outputArea and scroll to bottom
        public void run() {
            outputArea.append(textToAppend);
            outputArea.setCaretPosition(outputArea.getText().length());
        }
    }

    // launch MulticastDiscovery application
    public static void main(String args[]) {
        // set SecurityManager
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        MulticastDiscovery discovery = new MulticastDiscovery();
        discovery.setDefaultCloseOperation(EXIT_ON_CLOSE);
        discovery.pack();
        discovery.setVisible(true);
    }
*/
}
