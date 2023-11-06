package advanced_java2_deitel.chapter15_jmx;

// Java AWT core package

import java.awt.*;
import java.awt.event.*;

// Java standard extensions
import javax.swing.*;

// JMX core packages
import javax.management.*;

// JDMX core packages
//import com.sun.jdmk.comm.RmiConnectorClient;
//import com.sun.jdmk.comm.RmiConnectorAddress;

public class PrinterManagementGUI extends JFrame implements PrinterEventListener {

    // TextAppender appends text to a JTextArea.
    // This Runnable object should be executed only using SwingUtilities
    // methods invokeLater or invokeAndWait as it modifies a live Swing component.
    private class TextAppender implements Runnable {

        private String text;
        private JTextArea textArea;

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
/*
    private ObjectName objectName;
    private RmiConnectorClient client;
*/
    private JTextArea printerStatusTextArea = new JTextArea();
    private JTextArea printerEventTextArea = new JTextArea();
/*
    public PrinterManagementGUI(RmiConnectorClient rmiClient) {
        super("JMX Printer Management Example");

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

        // define action for Add Paper button
        JButton addPaperButton = new JButton("Add Paper");
        addPaperButton.addActionListener(event -> addPaperButtonAction(event));

        // define action for Cancel Pending Print Jobs button
        JButton cancelPendingPrintJobsButton = new JButton("Cancel Pending Print Jobs");
        cancelPendingPrintJobsButton.addActionListener(event -> cancelPendingPrintJobsButtonAction(event));

        // add three buttons to the panel
        buttonPanel.add(checkStatusButton, null);
        buttonPanel.add(addPaperButton, null);
        buttonPanel.add(cancelPendingPrintJobsButton, null);

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

        // set RmiConnectorClient reference
        client = rmiClient;

        // invoke method startPrinting of the PrinterSimulator MBean
        try {
            String name = client.getDefaultDomain() + ":type=Printer";
            objectName = new ObjectName(name);
            client.invoke(objectName, "startPrinting", new Object[0], new String[0]);
        } catch (MalformedObjectNameException e) { // invalid object name
            e.printStackTrace();
        } catch (ReflectionException e) {          // if cannot invoke the method
            e.printStackTrace();
        } catch (MBeanException e) {               // if invoked method throws exception
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {    // if MBean is not registered with MBean server
            e.printStackTrace();
        }

        // instantiate PrinterEventNotifier
        PrinterEventHandler printerEventHandler = new PrinterEventHandler(client, this);

        // unregister MBean when close the window
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent event) {
                        // unregister MBean
                        try {
                            // unregister the PrinterSimulator MBean
                            client.unregisterMBean(objectName);

                            // unregister the PrinterEventBroadcaster MBean
                            String name = client.getDefaultDomain()
                                    + ":type=" + "PrinterEventBroadcaster";
                            objectName = new ObjectName(name);
                            client.unregisterMBean(objectName);
                        } catch (MalformedObjectNameException exception) {     // if invalid object name
                            exception.printStackTrace();
                        } catch (MBeanRegistrationException exception) {
                            // if exception is caught from method preDeregister
                            exception.printStackTrace();
                        } catch (InstanceNotFoundException exception) {
                            // if MBean is not registered with MBean server
                            exception.printStackTrace();
                        }
                        // terminate the program
                        System.exit(0);
                    }
                }
        );
    }
*/
    // out of paper events
    @Override
    public void outOfPaper() {
        SwingUtilities.invokeLater(new TextAppender(printerEventTextArea, "\nEVENT: Out of Paper!\n"));
    }

    // toner low events
    @Override
    public void lowToner() {
        SwingUtilities.invokeLater(new TextAppender(printerEventTextArea, "\nEVENT: Toner Low!\n"));
    }

    // paper jam events
    @Override
    public void paperJam() {
        SwingUtilities.invokeLater(new TextAppender(printerEventTextArea, "\nEVENT: Paper Jam!\n"));
    }
/*
    // add paper to the paper tray
    public void addPaperButtonAction(ActionEvent event) {
        try {
            client.invoke(objectName, "replenishPaperTray", new Object[0], new String[0]);
        } catch (ReflectionException exception) { // if cannot invoke the method
            exception.printStackTrace();
        } catch (MBeanException exception) { // if invoked method throws exception
            exception.printStackTrace();
        } catch (InstanceNotFoundException exception) { // if MBean is not registered with MBean server
            exception.printStackTrace();
        }
    }

    // cancel pending print jobs
    public void cancelPendingPrintJobsButtonAction(ActionEvent event) {
        try {
            client.invoke(objectName, "cancelPendingPrintJobs", new Object[0], new String[0]);
        } catch (ReflectionException exception) { // if cannot invoke the method
            exception.printStackTrace();
        } catch (MBeanException exception) { // if invoked method throws exception
            exception.printStackTrace();
        } catch (InstanceNotFoundException exception) { // if MBean is not registered with MBean server
            exception.printStackTrace();
        }
    }

    public void checkStatusButtonAction(ActionEvent event) {
        Object onlineResponse = null;
        Object paperJamResponse = null;
        Object printingResponse = null;
        Object paperTrayResponse = null;
        Object pendingPrintJobsResponse = null;

        // manage printer remotely
        try {
            // check if the printer is on line
            onlineResponse = client.invoke(objectName, "isOnline", new Object[0], new String[0]);

            // check if the printer is paper jammed
            paperJamResponse = client.invoke(objectName, "isPaperJam", new Object[0], new String[0]);

            // check if the printing is pringint
            printingResponse = client.invoke(objectName, "isPrinting", new Object[0], new String[0]);

            // get the paper tray
            paperTrayResponse = client.invoke(objectName, "getPaperTray", new Object[0], new String[0]);

            // get pending print jobs
            pendingPrintJobsResponse = client.invoke(objectName, "getPendingPrintJobs", new Object[0], new String[0]);
        } catch (ReflectionException exception) { // if cannot invoke the method
            exception.printStackTrace();
        } catch (MBeanException exception) { // if invoked method throws exception
            exception.printStackTrace();
        } catch (InstanceNotFoundException exception) { // if MBean is not registered with MBean server
            exception.printStackTrace();
        }

        // status for the online condition
        boolean isOnline = ((Boolean) onlineResponse).booleanValue();

        // display status
        if (isOnline) {
            SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea, "\nPrinter is ONLINE.\n"));
        } else {
            SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea, "\nPrinter is OFFLINE.\n"));
        }

        // status for the paper jam condition
        boolean isPaperJam = ((Boolean) paperJamResponse).booleanValue();

        // display status
        if (isPaperJam) {
            SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea, "Paper jammed.\n"));
        } else {
            SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea, "No Paper Jam.\n"));
        }

        // status for the printing condition
        boolean isPrinting = ((Boolean) printingResponse).booleanValue();

        // display status
        if (isPrinting) {
            SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea,
                    "Printer is currently printing.\n"));
        } else {
            SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea,
                    "Printer is not printing.\n"));
        }

        // status for paper tray condition
        int paperRemaining = ((Integer) paperTrayResponse).intValue();

        // display status
        SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea,
                "Printer paper tray has " + paperRemaining + " pages remaining.\n"));

        // status for pending print jobs
        Object[] pendingPrintJobs = (Object[]) pendingPrintJobsResponse;
        int pendingPrintJobsNumber = pendingPrintJobs.length;

        // display status
        SwingUtilities.invokeLater(new TextAppender(printerStatusTextArea,
                "Number of pending print jobs: " + pendingPrintJobsNumber + "\n"));
    }
*/
}
