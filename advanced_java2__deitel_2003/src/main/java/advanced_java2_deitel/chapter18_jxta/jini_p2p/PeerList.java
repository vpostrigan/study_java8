package advanced_java2_deitel.chapter18_jxta.jini_p2p;

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
/*
// Jini core packages
import net.jini.core.lookup.ServiceItem;
import net.jini.core.lookup.ServiceTemplate;
import net.jini.lookup.*;
import net.jini.discovery.LookupDiscoveryManager;
import net.jini.lease.LeaseRenewalManager;
import net.jini.lookup.entry.Name;
import net.jini.core.entry.Entry;
import net.jini.core.discovery.LookupLocator;
*/
public class PeerList extends JFrame /*implements ServiceDiscoveryListener*/ {
/*
    private DefaultListModel peers;
    private JList peerList;
    private List serviceItems;
    private ServiceDiscoveryManager serviceDiscoveryManager;
    private LookupCache cache;
    private IMServiceManager myManager;
    private LookupDiscoveryManager lookupDiscoveryManager;

    // initialize userName to anonymous
    private String userName = "anonymous";

    // method called when ServiceDiscoveryManager finds
    // IM service adds service proxy to serviceItems adds Service name to ListModel for JList
    public void serviceAdded(ServiceDiscoveryEvent event) {
        // get added serviceItem
        ServiceItem item = event.getPostEventServiceItem();
        Entry attributes[] = item.attributeSets;

        // iterates through attributes to find name
        for (int i = 0; i < attributes.length; i++)

            if (attributes[i] instanceof Name) {
                System.out.println("Added: " + item);
                serviceItems.add(item.service);
                peers.addElement(((Name) attributes[i]).name);
                break;
            }
    }

    // empty method ignores seviceChanged event
    public void serviceChanged(ServiceDiscoveryEvent event) {
    }

    // removes services from PeerList GUI and data structure when serviceRemoved event occurs
    public void serviceRemoved(ServiceDiscoveryEvent event) {
        // getPreEvent because item has been removed getPostEvent would return null
        ServiceItem item = event.getPreEventServiceItem();
        Entry attributes[] = item.attributeSets;

        // debug
        System.out.println("Remove Event!");

        // remove from arraylist and DefaultListModel
        int index = serviceItems.indexOf(item.service);

        // print name of person removed
        if (index >= 0) {
            System.out.println("Removing from List:" +
                    serviceItems.remove(index));

            System.out.println("Removing from DefList" +
                    peers.elementAt(index));

            peers.removeElementAt(index);
        }
    }

    public PeerList() {
        super("Peer List");

        System.setSecurityManager(new RMISecurityManager());

        // get desired userName
        userName = JOptionPane.showInputDialog(PeerList.this, "Please enter your name: ");

        // change title of window
        setTitle(userName + "'s Peer List Window");

        // Init Lists
        serviceItems = new ArrayList();

        Container container = getContentPane();
        peers = new DefaultListModel();

        // init components
        peerList = new JList(peers);
        peerList.setVisibleRowCount(5);
        JButton connectButton = new JButton("Connect");

        // do not allow multiple selections
        peerList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        // set up event handler for connectButton
        connectButton.addActionListener(
                event -> {
                    int itemIndex = peerList.getSelectedIndex();

                    Object selectedService = serviceItems.get(itemIndex);
                    IMService peerProxy = (IMService) selectedService;

                    // send info to remote peer
                    // get RMI reference
                    try {
                        // set up gui and my peerImpl
                        IMPeerListener gui = new IMPeerListener(userName);
                        IMPeerImpl me = new IMPeerImpl(userName);
                        me.addListener(gui);

                        // Connect myGui to remote IMPeer object
                        IMPeer myPeer = peerProxy.connect(me);
                        gui.addPeer(myPeer);
                    } catch (RemoteException re) { // connecting may cause RemoteException
                        JOptionPane.showMessageDialog(null, "Couldn't Connect");
                        re.printStackTrace();
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

        // AddLocator item
        JMenuItem federateItem = new JMenuItem("Add Locators");
        federateItem.setMnemonic('L');
        federateItem.addActionListener(
                event -> {
                    // get LookupService url to be added
                    String locator = JOptionPane.showInputDialog(
                            PeerList.this, "Please enter locator in this" + "form: jini://host:port/");

                    try {
                        LookupLocator newLocator = new LookupLocator(locator);

                        // make one element LookupLocator array
                        LookupLocator[] locators = {newLocator};

                        // because addLocators takes array
                        lookupDiscoveryManager.addLocators(locators);
                    } catch (MalformedURLException urlException) {
                        JOptionPane.showMessageDialog(PeerList.this, "invalid url");
                    }
                }
        );
        fileMenu.add(federateItem);

        // set up JMenuBar and attach File menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // handow window closing event
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent w) {
                        System.out.println("CLOSING WINDOW");

                        // disconnects from lookup services
                        myManager.logout();
                        System.exit(0);
                    }
                }
        );

        // lay out GUI components
        peerList.setFixedCellWidth(100);
        JPanel inputPanel = new JPanel();
        inputPanel.add(connectButton);

        container.add(new JScrollPane(peerList), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.SOUTH);

        setSize(100, 170);
        setVisible(true);

        // peer list displays only other IMServices
        Class[] types = new Class[]{IMService.class};
        ServiceTemplate IMTemplate = new ServiceTemplate(null, types, null);

        // Initialize IMServiceManager, ServiceDiscoveryManager
        try {
            myManager = new IMServiceManager(userName);

            // store LookupDiscoveryManager
            // generated by IMServiceManager
            lookupDiscoveryManager = myManager.getDiscoveryManager();

            // ServiceDiscoveryManager uses lookupDiscoveryManager
            serviceDiscoveryManager = new ServiceDiscoveryManager(lookupDiscoveryManager, null);

            // create a LookupCache
            cache = serviceDiscoveryManager.createLookupCache(IMTemplate, null, this);
        } catch (Exception managerException) { // catch all exceptions and inform user of problem
            JOptionPane.showMessageDialog(null,
                    "Error initializing IMServiceManger" + "or ServiceDisoveryManager");
            managerException.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new PeerList();
    }
*/
}
