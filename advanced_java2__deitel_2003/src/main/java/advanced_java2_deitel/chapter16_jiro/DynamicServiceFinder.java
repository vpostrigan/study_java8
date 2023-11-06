package advanced_java2_deitel.chapter16_jiro;

// Java core packages
/*
import java.rmi.*;
import java.io.*;

// Jini core packages
import net.jini.core.entry.Entry;
import net.jini.core.lookup.*;

// Jini extension packages
import net.jini.discovery.*;
import net.jini.lookup.entry.ServiceInfo;

// Jiro packages
import javax.fma.util.*;
*/
// DynamicServiceFinder.java
// This class discovers lookup services and gets dynamic service proxy.
public class DynamicServiceFinder /*implements DiscoveryListener*/ {
/*
    private int servicesFound = 0;
    private ServiceRegistrar[] registrars;
    private Entry[] entries;

    public DynamicServiceFinder(String domain, Entry[] serviceEntries) {
        System.setSecurityManager(new RMISecurityManager());

        entries = serviceEntries;
        LookupDiscovery lookupDiscovery = null;

        // discover lookup services
        try {
            lookupDiscovery = new LookupDiscovery(new String[]{domain});
        } catch (IOException exception) {
            Debug.debugException("discover lookup service", exception);
        }

        // install a listener
        lookupDiscovery.addDiscoveryListener(this);

        // wait until woken up by notification
        try {
            synchronized (this) {
                wait();
            }
        } catch (Exception exception) { // handle exception waiting for notification
            Debug.debugException("wait for lookup service", exception);
        }
    }

    // discover new lookup services
    public void discovered(DiscoveryEvent event) {
        // get the proxy registrars for those services
        registrars = event.getRegistrars();

        // wake up all waiting threads
        synchronized (this) {
            notifyAll();
        }
    }

    // discover invalid lookup services
    public void discarded(DiscoveryEvent event) {
    }

    // get dynamic service proxy
    public Object getService() {
        // search lookup service to get dynamic service proxy
        try {
            ServiceTemplate template = new ServiceTemplate(null, null, entries);
            Object service = registrars[0].lookup(template);

            return service;
        } catch (Exception exception) { // handle exception getting dynamic service proxy
            Debug.debugException("getting proxy", exception);
        }
        return null;
    }
*/
}
