package advanced_java2_deitel.chapter18_jxta.multicast_p2p;

// Java core packages

import java.io.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.StringTokenizer;

// IMServiceImpl.java
// IMServiceImpl implements IMService interface is service side of IM application
public class IMServiceImpl extends UnicastRemoteObject implements IMService, Serializable {

    private static final long SerialVersionUID = 20010808L;
    private String userName = "Anonymous";

    public IMServiceImpl() throws RemoteException {
    }

    // IMService constructor takes userName
    public IMServiceImpl(String name) throws RemoteException {
        userName = name;
    }

    // sets service's userName
    public void setUserName(String name) {
        userName = name;
    }

    // return RMI reference to an IMPeer on the receiver side
    public IMPeer connect(IMPeer sender) throws RemoteException {
        // Make a GUI and IMPeerImpl to be sent to remote peer
        IMPeerListener listener = new IMPeerListener(userName);

        IMPeerImpl me = new IMPeerImpl(userName);
        me.addListener(listener);

        // add remote peer to my GUI
        listener.addPeer(sender);

        //send my IMPeerImpl to him
        return me;
    }

}