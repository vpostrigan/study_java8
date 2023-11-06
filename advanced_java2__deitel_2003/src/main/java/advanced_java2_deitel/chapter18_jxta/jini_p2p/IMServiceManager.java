package advanced_java2_deitel.chapter18_jxta.jini_p2p;

// Java core packages

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.io.IOException;
/*
// Jini core packages
import net.jini.core.lookup.ServiceID;
import net.jini.core.entry.Entry;

// Jini extension packages
import net.jini.lookup.entry.Name;
import net.jini.lease.LeaseRenewalManager;
import net.jini.lookup.JoinManager;
import net.jini.discovery.LookupDiscoveryManager;
import net.jini.lookup.ServiceIDListener;
*/
public class IMServiceManager /*implements ServiceIDListener*/ {
/*
    JoinManager manager;
    LookupDiscoveryManager lookupManager;
    String serviceName;

    // constructor takes name of the service
    public IMServiceManager(String screenName) {
        System.setSecurityManager(new RMISecurityManager());

        // sets the serviceName of this service
        serviceName = screenName;

        // use JoinManager to register SeminarInfo service and manage lease
        try {
            // create LookupDiscoveryManager for discovering lookup services
            lookupManager = new LookupDiscoveryManager(new String[]{""}, null, null);

            // create and set Entry name for service name used from constructor
            Entry[] entries = new Entry[1];
            entries[0] = new Name(serviceName);

            // create JoinManager
            manager = new JoinManager(createProxy(), entries, this, lookupManager, new LeaseRenewalManager());
        } catch (IOException exception) { // handle exception creating JoinManager
            exception.printStackTrace();
        }
    }

    // return the LookupDiscoveryManager created by JoinManager
    public LookupDiscoveryManager getDiscoveryManager() {
        return lookupManager;
    }

    // create service proxy
    private IMService createProxy() {
        // get SeminarProxy for SeminarInfo service
        try {
            return (new IMServiceImpl(serviceName));
        } catch (RemoteException exception) { // handle exception creating SeminarProxy
            exception.printStackTrace();
        }
        return null;
    }

    // receive notification of ServiceID assignment
    public void serviceIDNotify(ServiceID serviceID) {
        System.err.println("Service ID: " + serviceID);
    }

    // informs all lookup services that service is ending
    public void logout() {
        manager.terminate();
    }
*/
}
