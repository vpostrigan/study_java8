package advanced_java2_deitel.chapter18_jxta.multicast_p2p;

// Java core packages

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.List;
import java.io.IOException;
import java.rmi.*;

// Java extension packages
import javax.swing.*;
import javax.swing.event.*;

// PeerList.java
// Starts broadcasting and receiving threads and lists all IM peers in a window
public class PeerList extends JFrame implements PeerDiscoveryListener, IMConstants {

    // initialize userName to anonymous
    private String userName = "anonymous";
    private MulticastSendingThread multicastSender;
    private MulticastReceivingThread multicastReceiver;

    // list variables
    private DefaultListModel peerNames;   // contains peer names
    private List peerStubAddresses;       // contains peer stubs
    private JList peerJList;

    // add peer name and peer stub to lists
    public void peerAdded(String name, String peerStubAddress) {
        // add name to peerNames
        peerNames.addElement(name);

        // add stub to peerStubAddresses
        peerStubAddresses.add(peerStubAddress);
    }

    // removes services from PeerList GUI and data structure
    public void peerRemoved(String name) {
        // remove name from peerNames
        int index = peerNames.indexOf(name);
        peerNames.removeElementAt(index);

        // remove stub from peerStubAddresses
        peerStubAddresses.remove(index);
    }

    public PeerList() {
        super("Peer List");

        // get desired userName
        userName = JOptionPane.showInputDialog(PeerList.this, "Please enter your name: ");

        // change title of window
        setTitle(userName + "'s Peer List Window");

        // Init List data structures
        peerNames = new DefaultListModel();
        peerStubAddresses = new ArrayList();

        // init components
        Container container = getContentPane();
        peerJList = new JList(peerNames);
        peerJList.setVisibleRowCount(5);
        JButton connectButton = new JButton("Connect");

        // do not allow multiple selections
        peerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // set up event handler for connectButton
        connectButton.addActionListener(
                event -> {
                    int itemIndex = peerJList.getSelectedIndex();

                    String stubAddress = (String) peerStubAddresses.get(itemIndex);

                    // get RMI reference to IMService and IMPeer
                    try {
                        IMService peerStub = (IMService) Naming.lookup("rmi://" + stubAddress);

                        // set up gui and my peerImpl
                        IMPeerListener gui = new IMPeerListener(userName);
                        IMPeerImpl me = new IMPeerImpl(userName);
                        me.addListener(gui);

                        // Connect myGui to remote IMPeer object
                        IMPeer myPeer = peerStub.connect(me);
                        gui.addPeer(myPeer);
                    } catch (MalformedURLException e) { // malformedURL passed to lookup
                        JOptionPane.showMessageDialog(null, "Stub address incorrectly formatted");
                        e.printStackTrace();
                    } catch (NotBoundException e) { // Remote object not bound to remote registry
                        JOptionPane.showMessageDialog(null, "Remote object not present in Registry");
                        e.printStackTrace();
                    } catch (RemoteException e) { // connecting may cause RemoteException
                        JOptionPane.showMessageDialog(null, "Couldn't Connect");
                        e.printStackTrace();
                    }
                }
        );

        // set up File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        // about Item
        JMenuItem aboutItem = new JMenuItem("About...");
        aboutItem.setMnemonic('A');
        aboutItem.addActionListener(
                event -> JOptionPane.showMessageDialog(PeerList.this,
                        "Deitel Instant Messenger",
                        "About", JOptionPane.PLAIN_MESSAGE)
        );

        fileMenu.add(aboutItem);

        // set up JMenuBar and attach File menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // handow window closing event
        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent w) {
                        System.out.println("CLOSING WINDOW");

                        // disconnects from lookup services
                        multicastSender.logout();
                        multicastReceiver.logout();

                        // join threads
                        try {
                            multicastSender.join();
                            multicastReceiver.join();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }

                        System.exit(0);
                    }
                }
        );

        // layout GUI components
        peerJList.setFixedCellWidth(100);
        JPanel inputPanel = new JPanel();
        inputPanel.add(connectButton);

        container.add(new JScrollPane(peerJList), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.SOUTH);

        // Initialize threads
        try {
            multicastReceiver = new MulticastReceivingThread(userName, this);
            multicastReceiver.start();

            multicastSender = new MulticastSendingThread(userName);
            multicastSender.start();
        } catch (Exception managerException) { // catch all exceptions and inform user of problem
            JOptionPane.showMessageDialog(null, "Error initializing MulticastSendingThread or MulticastReceivingThread");
            managerException.printStackTrace();
        }
    }

    public static void main(String args[]) {
        PeerList peerlist = new PeerList();
        peerlist.setSize(100, 170);
        peerlist.setVisible(true);
    }

}
