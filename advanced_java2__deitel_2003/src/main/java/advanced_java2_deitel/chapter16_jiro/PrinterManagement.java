package advanced_java2_deitel.chapter16_jiro;

// Java core package

import java.rmi.*;
import java.util.*;

// Jini core package
//import net.jini.core.event.*;

// Fig: PrinterManagement.java
// This class defines the interface for the dynamic service.
public interface PrinterManagement /*extends RemoteEventListener*/ {

    public void addPaper(int amount) throws RemoteException;

    public boolean isPrinting() throws RemoteException;

    public boolean isPaperJam() throws RemoteException;

    public int getPaperInTray() throws RemoteException;

    public boolean isOnline() throws RemoteException;

    public void cancelPendingPrintJobs() throws RemoteException;

    public void terminateScheduledTasks() throws RemoteException;

    public void addToner() throws RemoteException;

    public String[] getPendingPrintJobs() throws RemoteException;
}
