package advanced_java2_deitel.chapter16_jiro;

// Java core packages

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

// Jini core packages
//import net.jini.core.event.*;

// PrinterEventListener.java
// This class defines the listener that listens for events issued by a printer.
public class PrinterEventListener /*implements RemoteEventListener*/ {
/*
    private RemoteEventListener eventListener;

    public PrinterEventListener(RemoteEventListener listener) {
        eventListener = listener;

        // export the stub object
        try {
            UnicastRemoteObject.exportObject(this);
        } catch (RemoteException remoteException) { // handle exception exporting stub
            remoteException.printStackTrace();
        }
    }

    // receive the notification
    public void notify(RemoteEvent remoteEvent) throws UnknownEventException, RemoteException {
        // forward notification
        eventListener.notify(remoteEvent);
    }
*/
}