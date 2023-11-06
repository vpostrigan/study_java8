package advanced_java2_deitel.chapter14_jini.discovery;

// Java core packages

import java.rmi.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

// Java swing package
import javax.swing.*;
/*
// Jini core packages
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.discovery.LookupLocator;

// Jini extension packages
import net.jini.discovery.LookupLocatorDiscovery;
import net.jini.discovery.DiscoveryListener;
import net.jini.discovery.DiscoveryEvent;
*/
public class UnicastDiscoveryUtility extends JFrame /*implements DiscoveryListener*/ {
/*
    private JTextArea outputArea = new JTextArea(10, 20);

    // UnicastDiscoveryUtility constructor
    public UnicastDiscoveryUtility(String urls[]) {
        super("UnicastDiscoveryUtility");

        getContentPane().add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // discover lookup services using LookupLocatorDiscovery
        try {
            // create LookupLocator for each URL
            LookupLocator locators[] = new LookupLocator[urls.length];

            for (int i = 0; i < locators.length; i++)
                locators[i] = new LookupLocator(urls[i]);

            // create LookupLocatorDiscovery object
            LookupLocatorDiscovery locatorDiscovery = new LookupLocatorDiscovery(locators);

            // register DiscoveryListener
            locatorDiscovery.addDiscoveryListener(this);

        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }
    }

    // receive notification of found lookup services
    public void discovered(DiscoveryEvent event) {
        // get the proxy registrars for those services
        ServiceRegistrar[] registrars = event.getRegistrars();

        // display information for each lookup service found
        for (int i = 0; i < registrars.length; i++)
            displayServiceDetails(registrars[i]);
    }

    // display details of given ServiceRegistrar
    private void displayServiceDetails(ServiceRegistrar registrar) {
        try {
            final StringBuffer buffer = new StringBuffer();

            // get the hostname and port number
            buffer.append("Lookup Service: ");
            buffer.append("\n   Host: " + registrar.getLocator().getHost());
            buffer.append("\n   Port: " + registrar.getLocator().getPort());
            buffer.append("\n   Group support: ");

            // get lookup service groups
            String[] groups = registrar.getGroups();

            // get group names; if empty write public
            for (int i = 0; i < groups.length; i++) {

                if (groups[i].equals(""))
                    buffer.append("public,");
                else
                    buffer.append(groups[i] + ",");
            }

            buffer.append("\n\n");

            // append information to outputArea
            // append text and update caret position
            SwingUtilities.invokeLater(
                    // create Runnable for appending text
                    () -> {
                        outputArea.append(buffer.toString());
                        outputArea.setCaretPosition(
                                outputArea.getText().length());
                    }
            );

        } catch (RemoteException exception) {
            // handle exception communicating with lookup service
            exception.printStackTrace();
        }
    }

    // ignore discarded lookup services
    public void discarded(DiscoveryEvent event) {
    }

    // launch UnicastDiscoveryUtility application
    public static void main(String args[]) {
        // set SecurityManager
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        // check command-line arguments for hostnames
        if (args.length < 1) {
            System.err.println(
                    "Usage: java UnicastDiscoveryUtility " +
                            "jini://hostname:port [ jini://hostname:port ] ...");
        }

        // launch UnicastDiscoveryUtility for set of hostnames
        else {
            UnicastDiscoveryUtility unicastUtility = new UnicastDiscoveryUtility(args);

            unicastUtility.setDefaultCloseOperation(EXIT_ON_CLOSE);
            unicastUtility.setSize(300, 300);
            unicastUtility.setVisible(true);
        }
    }
*/
}