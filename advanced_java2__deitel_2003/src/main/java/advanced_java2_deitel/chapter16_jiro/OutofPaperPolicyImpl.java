package advanced_java2_deitel.chapter16_jiro;

// Java core packages

import java.io.Serializable;
import java.rmi.*;
import java.util.*;

// Java standard extensions
import javax.swing.*;

/*
// Jini core packages
import net.jini.core.event.*;
import net.jini.core.entry.*;
import net.jini.core.lease.*;

// Jini extension packages
import net.jini.lease.LeaseRenewalManager;
import net.jini.lookup.entry.*;

// Jiro packages
import javax.fma.services.ServiceFinder;
import javax.fma.services.event.*;
import javax.fma.services.log.*;
import javax.fma.util.*;
import javax.fma.common.*;
import javax.fma.server.*;

// Deitel packages
import com.deitel.advjhtp1.jiro.DynamicService.service.*;
import com.deitel.advjhtp1.jiro.DynamicService.common.*;
*/
// OutofPaperPolicyImpl.java
// Handles events generated by printer by registering as a responsible listener
public class OutofPaperPolicyImpl /*implements OutofPaperPolicy*/ {
/*
    private Lease listenerLease;
    private LeaseRenewalManager leaseRenewalManager;

    private LogService logService;
    private PrinterEventListener listener;
    private PrinterManagement printerManagementProxy;

    public OutofPaperPolicyImpl() {
        // subscribe as an responsible listener to certain event
        listener = new PrinterEventListener(this);

        // start the OutofPaper management policy
        try {
            // obtain referrence to dynamic service entry point
            printerManagementProxy = getPrinterManagementProxy();

            // obtain referrence to log service
            logService = ServiceFinder.getLogService();

            // obtain referrence to log service
            EventService eventService =
                    ServiceFinder.getEventService();

            // subscribe as responsible listener
            listenerLease = eventService.subscribeResponsibleBefore(
                    ".Printer.Error.OutofPaper", null, listener,
                    "OutofPaperEventListener", null, Lease.FOREVER);

            // renew lease indefinitely
            leaseRenewalManager = new LeaseRenewalManager(listenerLease, Lease.FOREVER, null);

        } catch (Exception exception) {
            System.out.println("OutofPaperPolicyImpl: " + "Exception occurred when starting policy.");
            System.out.println("Please read debug file ... \n");
            Debug.debugException("starting LowTonerPolicy", exception);
        }
        System.out.println("OutofPaperPolicyImpl: started.");
    }

    // stop OutofPaperPolicyImpl
    public void stopPolicy() {
        // stopping OutofPaper management policy
        try {

            // expire lease
            leaseRenewalManager.cancel(listenerLease);
            System.out.println("OutofPaperPolicyImpl: stopping.");
        }

        // handle exception canceling lease
        catch (Exception exception) {
            System.out.println("OutofPaperPolicyImpl: " + "Exception occurred when canceling lease.");
            System.out.println("Please read debug file ... \n");
            Debug.debugException("stopping OutofPaper policy", exception);
        }
    }

    // receive notification calls
    public void notify(RemoteEvent remoteEvent) throws UnknownEventException, RemoteException, EventNotHandledException {
        Object sourceObject = null;

        // event source
        try {
            // get event source
            sourceObject = remoteEvent.getSource();
        } catch (Exception exception) {
            System.out.println("OutofPaperPolicyImpl: " + "Exception occurred when getting event source.");
            System.out.println("Please read debug file ... \n");
            Debug.debugException("getting event source", exception);
        }

        // definitely not from our printer
        if (!(sourceObject instanceof String)) {
            throw new EventNotHandledException();
        }

        // obtain String value
        String source = (String) sourceObject;

        // verify origin of event
        if (source.equals("com.deitel.advjhtp1.jiro." + "DynamicService.printer.ErrorMessage=OutofPaper")) {

            System.out.println("OutfPaperPolicy: " + "handling OutofPaperEvent...");

            // take action
            try {
                // replenish paper tray
                printerManagementProxy.addPaper(50);

                // generate the log message parameters
                Serializable params[] = new Serializable[2];
                params[0] = source;
                params[1] = new Date();

                // generate localizable message
                LocalizableMessage localizableMessage =
                        new LocalizableMessage(OutofPaperPolicyImpl.class, "Action", params, Locale.US);

                // generate log message
                LogMessage logMessage = new LogMessage(
                        localizableMessage, LogMessage.TRACE
                        + ".OutofPaperEvent." + source, null);

                // log action message
                logService.log(logMessage);
            } catch (Exception exception) {
                System.out.println("OutofPaperPolicyImpl: " + "Exception occurred when posting log message.");
                System.out.println("Please read debug file ...\n");
                Debug.debugException("log service", exception);
            }
        } else { // not event from our printer
            System.out.println("OutfPaperPolicy: " + " NOT handling OutofPaperEvent...");

            // responsible listener requirement when not handling event.
            throw new EventNotHandledException();
        }
    }

    // get dynamic services proxies
    public PrinterManagement getPrinterManagementProxy() {
        Entry[] entries = new Entry[]{
                new ServiceInfo("PrinterManagementImpl",
                        "Deitel Association, Inc.",
                        "Deitel Association, Inc",
                        "1.0", "Model 0", "0.0.0.1")
        };

        String domain = System.getProperty("javax.fma.domain");
        DynamicServiceFinder finder = new DynamicServiceFinder(domain, entries);

        // return proxy
        return (PrinterManagement) finder.getService();
    }

    // defines class as dynamic service during deployment
    private Entry[] getLookupEntries() {
        return (new Entry[]{
                new ServiceInfo("OutofPaperPolicyImpl",
                        "Deitel Association, Inc.",
                        "Deitel Association, Inc",
                        "1.0", "Model 0", "0.0.0.1")
        }
        );
    }*/
}
