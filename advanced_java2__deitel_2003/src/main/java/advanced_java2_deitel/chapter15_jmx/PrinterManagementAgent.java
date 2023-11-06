package advanced_java2_deitel.chapter15_jmx;

// JMX core packages

import javax.management.*;

/**
 * Steps:
 * 1. Start PrinterManagementAgent. создает MBeanServer и инициализирует сервис соединителя RMI.
 * 2. Start ClientPrinterManagement. запускает модель принтера и GUI для управления принтером.
 */
public class PrinterManagementAgent {

    public static void main(String[] args) {
        ObjectInstance rmiConnectorServer = null;
        ObjectInstance printer = null;
        ObjectInstance broadcaster = null;
        ObjectName objectName = null;

        // create an MBeanServer
        MBeanServer server = MBeanServerFactory.createMBeanServer();

        // create an RMI connector service, a printer simulator
        // MBean and a broadcaster MBean
        try {
            // create an RMI connector server
            rmiConnectorServer = server.createMBean("com.sun.jdmk.comm.RmiConnectorServer", null);

            // create a broadcaster MBean
            String name = server.getDefaultDomain() + ":type=PrinterEventBroadcaster";
            String className = "com.deitel.advjhtp1.jmx.PrinterManagement.PrinterEventBroadcaster";

            objectName = new ObjectName(name);
            printer = server.createMBean(className, objectName);

            // create a printer simulator MBean
            name = server.getDefaultDomain() + ":type=Printer";
            className = "com.deitel.advjhtp1.jmx.PrinterManagement.Printer";

            objectName = new ObjectName(name);
            broadcaster = server.createMBean(className, objectName);
        } catch (NotCompliantMBeanException e) {     // handle class not JMX-compliant MBean exception
            e.printStackTrace();
        } catch (MBeanException e) {                 // handle MBean constructor exception
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) { // handle MBean already exists exception
            e.printStackTrace();
        } catch (ReflectionException e) {            // handle MBean constructor exception
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {   // handle invalid object name exception
            e.printStackTrace();
        }

        // set port number
        Object[] parameter = new Object[]{new Integer(5555)};
        String[] signature = new String[]{"int"};

        // invoke method setPort on RmiConnectorServer MBean
        // start the RMI connector service
        try {
            server.invoke(rmiConnectorServer.getObjectName(), "setPort", parameter, signature);
            server.invoke(rmiConnectorServer.getObjectName(), "start", new Object[0], new String[0]);
        } catch (ReflectionException e) {       // handle exception when executing method
            e.printStackTrace();
        } catch (MBeanException e) {            // handle exception communicating with MBean
            e.printStackTrace();
        } catch (InstanceNotFoundException e) { // handle exception if MBean not found
            e.printStackTrace();
        }
    }

}
