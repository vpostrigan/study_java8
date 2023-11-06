package advanced_java2_deitel.chapter15_jmx;

// JMX core packages

import javax.management.MBeanNotificationInfo;
import javax.management.NotificationBroadcasterSupport;

// Fig. 24.10: PrinterEventBroadcaster.java
// This class defines an MBean that
// provides events information.

// extends NotificationBroadcasterSupport to adopt its functionality.
public class PrinterEventBroadcaster extends NotificationBroadcasterSupport implements PrinterEventBroadcasterMBean {

    private static final String OUT_OF_PAPER = "PrinterEvent.OUT_OF_PAPER";
    private static final String LOW_TONER = "PrinterEvent.LOW_TONER";
    private static final String PAPER_JAM = "PrinterEvent.PAPER_JAM";

    // provide information about deliverable events
    public MBeanNotificationInfo[] getNotificationInfo() {
        // array containing descriptor objects
        MBeanNotificationInfo[] descriptorArray = new MBeanNotificationInfo[1];

        // different event types
        String[] notificationTypes = new String[3];
        notificationTypes[0] = PrinterEventBroadcaster.OUT_OF_PAPER;
        notificationTypes[1] = PrinterEventBroadcaster.LOW_TONER;
        notificationTypes[2] = PrinterEventBroadcaster.PAPER_JAM;

        // notification class type
        String classType = "javax.management.Notification";

        // description of MBeanNotificationInfo
        String description = "Notification types for PrinterEventBroadcaster";

        // populate descriptor array
        descriptorArray[0] = new MBeanNotificationInfo(notificationTypes, classType, description);
        return descriptorArray;
    }

}
