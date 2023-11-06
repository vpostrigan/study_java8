package advanced_java2_deitel.chapter15_jmx;

// JMX core packages

import javax.management.*;

// JDMK core packages
//import com.sun.jdmk.comm.RmiConnectorClient;
//import com.sun.jdmk.comm.ClientNotificationHandler;

// Fig. 24.11: PrinterEventHandler.java
// The class adds a listener to the broadcaster MBean and
// defines the event handlers when event occurs.
public class PrinterEventHandler {
/*
    private RmiConnectorClient rmiClient;
    private PrinterEventListener eventTarget;

    // notification listener anonymous inner class
    private NotificationListener notificationListener =
            (notification, handback) -> {
                // retrieve notification type
                String notificationType = notification.getType();

                // handle different notifications
                if (notificationType.equals("PrinterEvent.OUT_OF_PAPER")) {
                    handleOutOfPaperEvent();
                    return;
                }
                if (notificationType.equals("PrinterEvent.LOW_TONER")) {
                    handleLowTonerEvent();
                    return;
                }
                if (notificationType.equals("PrinterEvent.PAPER_JAM")) {
                    handlePaperJamEvent();
                    return;
                }
            };

    public PrinterEventHandler(RmiConnectorClient inputRmiClient, PrinterEventListener inputEventTarget) {
        rmiClient = inputRmiClient;
        eventTarget = inputEventTarget;

        // set notification push mode
        rmiClient.setMode(ClientNotificationHandler.PUSH_MODE);

        // register listener
        try {
            ObjectName objectName = new ObjectName(
                    rmiClient.getDefaultDomain() + ":type=PrinterEventBroadcaster");

            rmiClient.addNotificationListener(objectName, notificationListener, null, null);
        } catch (InstanceNotFoundException e) {    // if MBean does not exist in the MBean server
            e.printStackTrace();
        } catch (MalformedObjectNameException e) { // if the format of the object name is wrong
            e.printStackTrace();
        }
    }

    private void handleOutOfPaperEvent() {
        eventTarget.outOfPaper();
    }

    private void handleLowTonerEvent() {
        eventTarget.lowToner();
    }

    private void handlePaperJamEvent() {
        eventTarget.paperJam();
    }
*/
}
