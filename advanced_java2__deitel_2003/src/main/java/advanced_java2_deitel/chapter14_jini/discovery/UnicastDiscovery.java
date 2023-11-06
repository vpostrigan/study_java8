package advanced_java2_deitel.chapter14_jini.discovery;

// Java core packages

import java.rmi.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;

// Jini core packages
//import net.jini.core.discovery.LookupLocator;
//import net.jini.core.lookup.ServiceRegistrar;

public class UnicastDiscovery extends JFrame {
/*
    private JTextArea outputArea = new JTextArea(10, 20);
    private JButton discoverButton;

    // hostname for discovering lookup services
    private String hostname;

    // UnicastDiscovery constructor
    public UnicastDiscovery(String host) {
        super("UnicastDiscovery Output");

        hostname = host; // set target hostname for discovery

        // create JButton to discover lookup services
        discoverButton = new JButton("Discover Lookup Services");
        // discover lookup services on given host
        discoverButton.addActionListener(event -> discoverLookupServices());

        Container contentPane = getContentPane();
        contentPane.add(outputArea, BorderLayout.CENTER);
        contentPane.add(discoverButton, BorderLayout.NORTH);
    }

    // discover lookup services on given host and get details
    // about each lookup service from ServiceRegistrar
    public void discoverLookupServices() {
        // construct Jini URL
        String lookupURL = "jini://" + hostname + "/";

        // connect to the lookup service at lookupURL
        try {
            LookupLocator locator = new LookupLocator(lookupURL);
            outputArea.append("Connecting to " + lookupURL + "\n");

            // perform unicast discovery to get ServiceRegistrar
            ServiceRegistrar registrar = locator.getRegistrar();

            // print lookup service information and
            outputArea.append("Got ServiceRegistrar\n" +
                    "  Lookup Service Host: " + locator.getHost() + "\n" +
                    "  Lookup Service Port: " + locator.getPort() + "\n");

            // get groups that lookup service supports
            String[] groups = registrar.getGroups();
            outputArea.append("Lookup service supports " + groups.length + " group(s):\n");

            // get group names; if empty, write public
            for (int i = 0; i < groups.length; i++) {
                if (groups[i].equals(""))
                    outputArea.append("  public\n");
                else
                    outputArea.append("  " + groups[i] + "\n");
            }
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
            outputArea.append(exception.getMessage());
        } catch (RemoteException exception) {
            exception.printStackTrace();
            outputArea.append(exception.getMessage());
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            outputArea.append(exception.getMessage());
        } catch (IOException exception) {
            exception.printStackTrace();
            outputArea.append(exception.getMessage());
        }
    }

    // launch UnicastDiscovery application
    public static void main(String args[]) {
        // set SecurityManager
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        // check command-line arguments for hostname
        if (args.length != 1) {
            System.err.println("Usage: java UnicastDiscovery hostname");
        } else {
            // create UnicastDiscovery for given hostname
            UnicastDiscovery discovery = new UnicastDiscovery(args[0]);

            discovery.setDefaultCloseOperation(EXIT_ON_CLOSE);
            discovery.pack();
            discovery.setVisible(true);
        }
    }
*/
}
