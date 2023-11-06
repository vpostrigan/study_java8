package advanced_java2_deitel.chapter18_jxta.jini_p2p;

//java core packages

import java.rmi.*;
import java.util.*;

// IMPeer.java
// Interface that all Peer to Peer apps must implement
public interface IMPeer extends Remote {

    // posts Message to peer
    public void sendMessage(Message message) throws RemoteException;

    // information methods
    public String getName() throws RemoteException;

}
