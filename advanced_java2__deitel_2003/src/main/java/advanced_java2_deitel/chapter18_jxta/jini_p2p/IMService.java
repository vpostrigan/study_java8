package advanced_java2_deitel.chapter18_jxta.jini_p2p;

// Java core packages

import java.rmi.*;

// IMService.java
// IMService interface defines the methods through which the service proxy communicates with the service.
public interface IMService extends Remote {

    // return RMI reference to a remote IMPeer
    public IMPeer connect(IMPeer sender) throws RemoteException;

}