package advanced_java2_deitel.chapter16_jiro;

// Java core packages

import java.rmi.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

// Java standard extensions
import javax.swing.*;
/*
// Jini core package
import net.jini.core.lease.Lease;
import net.jini.core.event.*;
import net.jini.core.entry.Entry;

// Jini extension package
import net.jini.lease.LeaseRenewalManager;
import net.jini.lookup.entry.*;

// Jiro packages
import javax.fma.common.*;
import javax.fma.services.*;
import javax.fma.services.event.EventService;

import com.sun.jiro.util.*;

// Deitel packages
import com.deitel.advjhtp1.jiro.DynamicService.common.*;
import com.deitel.advjhtp1.jiro.DynamicService.service.*;
*/
public class PrinterClientGUI /*extends JFrame implements RemoteEventListener*/ {
/*
    private PrinterManagement printerManagementProxy;
    private JTextArea printerStatusTextArea = new JTextArea();
    private JTextArea printerEventTextArea = new JTextArea();
    private Lease observerLease;
    private LeaseRenewalManager leaseRenewalManager;

    public PrinterClientGUI(String domain) {
        super("JIRO Printer Management Example");

        // set security manager
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        // obtain reference to proxy
        printerManagementProxy = getPrinterManagementProxy(domain);

        // subscribe to event service as an observer listener
        subscriberObserver(domain);

        Container container = getContentPane();

        // status panel
        JPanel printerStatusPanel = new JPanel();
        printerStatusPanel.setPreferredSize(new Dimension(512, 200));
        JScrollPane statusScrollPane = new JScrollPane();
        statusScrollPane.setAutoscrolls(true);
        statusScrollPane.setPreferredSize(new Dimension(400, 150));
        statusScrollPane.getViewport().add(printerStatusTextArea, null);
        printerStatusPanel.add(statusScrollPane, null);

        // buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(512, 200));

        // define action for Check Status button
        JButton checkStatusButton = new JButton("Check Status");
        checkStatusButton.addActionListener(event -> checkStatusButtonAction(event));

        // define action for Remove Jammed Paper button
        JButton cancelJobsButton = new JButton("Cancel Pending Print Jobs");
        cancelJobsButton.addActionListener(event -> cancelJobsButtonAction(event));

        // add three buttons to the panel
        buttonPanel.add(checkStatusButton, null);
        buttonPanel.add(cancelJobsButton, null);

        // events panel
        JPanel printerEventPanel = new JPanel();
        printerEventPanel.setPreferredSize(new Dimension(512, 200));
        JScrollPane eventsScrollPane = new JScrollPane();
        eventsScrollPane.setAutoscrolls(true);
        eventsScrollPane.setPreferredSize(new Dimension(400, 150));
        eventsScrollPane.getViewport().add(printerEventTextArea, null);
        printerEventPanel.add(eventsScrollPane, null);

        // initialize the text
        printerStatusTextArea.setText("Printer Status: ---\n");
        printerEventTextArea.setText("Events: --- \n");

        // put all the panels together
        container.add(printerStatusPanel, BorderLayout.NORTH);
        container.add(printerEventPanel, BorderLayout.SOUTH);
        container.add(buttonPanel, BorderLayout.CENTER);

        // release observer listener and cancel scheduled task when window closing
        addWindowListener(

                new WindowAdapter() {
                    public void windowClosing(WindowEvent event) {
                        // release listener and cancel scheduled task
                        try {
                            leaseRenewalManager.remove(observerLease);
                        } catch (Exception exception) { // handle exception releasing observer listener
                            exception.printStackTrace();
                        }
                        // terminate the program
                        System.exit(0);
                    }
                }
        );
    }

    // check print status
    public void checkStatusButtonAction(ActionEvent event) {
        boolean isOnline = false;
        boolean isPaperJam = false;
        boolean isPrinting = false;
        int paperRemaining = 0;
        String[] pendingJobs = null;

        // manage printer remotely
        try {
            // check if the printer is on line
            isOnline = printerManagementProxy.isOnline();

            // check if the printer is paper jammed
            isPaperJam = printerManagementProxy.isPaperJam();

            // check if the printing is pringint
            isPrinting = printerManagementProxy.isPrinting();

            // get the paper tray
            paperRemaining = printerManagementProxy.getPaperInTray();

            // get pending jobs
            pendingJobs = printerManagementProxy.getPendingPrintJobs();
        } catch (Exception exception) { // handle exception calling dynamic service methods
            exception.printStackTrace();
        }

        // status for the online condition
        if (isOnline)
            printerStatusTextArea.append("\nPrinter is ONLINE.\n");
        else
            printerStatusTextArea.append("\nPrinter is OFFLINE.\n");

        // status for the paper jam condition
        if (isPaperJam)
            printerStatusTextArea.append("Paper jammed.\n");
        else
            printerStatusTextArea.append("No Paper Jam.\n");

        // status for the printing condition
        if (isPrinting)
            printerStatusTextArea.append("Printer is currently printing.\n");
        else
            printerStatusTextArea.append("Printer is not printing.\n");

        // status for paper tray condition
        printerStatusTextArea.append("Printer paper tray has " + paperRemaining + " pages remaining.\n");

        // number of pending jobs
        printerStatusTextArea.append("Number of pending jobs: " + pendingJobs.length + "\n");
    }

    // cancel print jobs
    public void cancelJobsButtonAction(ActionEvent event) {
        // cancel pending print jobs
        try {
            printerManagementProxy.cancelPendingPrintJobs();
        } catch (Exception exception) { // handle exception canceling pending print jobs
            exception.printStackTrace();
        }
    }

    // get dynamic service proxy
    public PrinterManagement getPrinterManagementProxy(String domain) {
        Entry[] entries = new Entry[]{
                new ServiceInfo("PrinterManagementImpl",
                        "Deitel Association, Inc.",
                        "Deitel Association, Inc",
                        "1.0", "Model 0", "0.0.0.1")
        };

        DynamicServiceFinder finder = new DynamicServiceFinder(domain, entries);

        // return dynamic service proxy
        return (PrinterManagement) finder.getService();
    }

    // listener for events
    public void subscriberObserver(String domain) {
        // subscribe to printer events
        try {
            EventService eventService = ServiceFinder.getEventService(domain);

            // subscribe as an observing listener to certain event
            RemoteEventListener listener = new RemoteEventListenerImpl(this);
            observerLease = eventService.subscribeObserver(".Printer.Error", listener, null, 10 * 60 * 1000);
            leaseRenewalManager = new LeaseRenewalManager(observerLease, Lease.FOREVER, null);
        } catch (Exception exception) { // handle exception subscribing to events
            exception.printStackTrace();
        }
    }

    // receive notification
    public void notify(RemoteEvent event) {
        String output = "\nEVENT: " + (String) event.getSource() + "\n";
        SwingUtilities.invokeLater(new TextAppender(printerEventTextArea, output));
    }

    // method main
    public static void main(String args[]) {
        String domain = "";

        // get the domain
        if (args.length != 1) {
            System.out.println("Usage: PrinterClientGUI Domain");
            System.exit(1);
        } else
            domain = args[0];

        PrinterClientGUI client = new PrinterClientGUI(domain);
        client.setSize(500, 500);
        client.setVisible(true);
    }

    // TextAppender appends text to a JTextArea. This Runnable
    // object should be executed only using SwingUtilities
    // methods invokeLater or invokeAndWait as it modifies a live Swing component.
    private class TextAppender implements Runnable {

        private String text;
        private JTextArea textArea;

        // TextAppender constructor
        public TextAppender(JTextArea area, String newText) {
            text = newText;
            textArea = area;
        }

        // display new text in JTextArea
        public void run() {
            // append new message
            textArea.append(text);

            // move caret to end of messageArea to ensure new message is visible on screen
            textArea.setCaretPosition(textArea.getText().length());
        }
    }
*/
}