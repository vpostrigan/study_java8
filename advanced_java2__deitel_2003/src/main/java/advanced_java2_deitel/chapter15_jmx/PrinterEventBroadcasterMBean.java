package advanced_java2_deitel.chapter15_jmx;

// JMX core packages

import javax.management.Notification;

// Fig. 24.9: PrinterEventBroadcasterMBean.java
// This class defines the MBean interface.
public interface PrinterEventBroadcasterMBean {
    public void sendNotification(Notification notification);
}