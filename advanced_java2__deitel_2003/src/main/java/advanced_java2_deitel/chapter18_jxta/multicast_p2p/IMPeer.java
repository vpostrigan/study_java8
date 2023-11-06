package advanced_java2_deitel.chapter18_jxta.multicast_p2p;

//java core packages

import advanced_java2_deitel.chapter18_jxta.jini_p2p.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

// IMPeer.java
// Interface that all Peer to Peer apps must implement
public interface IMPeer extends Remote {
    // posts Message to peer
    public void sendMessage(Message message) throws RemoteException;

    // information methods
    public String getName() throws RemoteException;
}