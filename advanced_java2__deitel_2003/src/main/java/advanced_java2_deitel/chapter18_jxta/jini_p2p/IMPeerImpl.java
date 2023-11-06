package advanced_java2_deitel.chapter18_jxta.jini_p2p;

// Java core packages

import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

// IMPeerImpl.java
// Implements the IMPeer interface
public class IMPeerImpl extends UnicastRemoteObject implements IMPeer {

    private String name;
    private IMPeerListener output;

    public IMPeerImpl() throws RemoteException {
        super();
        name = "anonymous";
    }

    public IMPeerImpl(String myName) throws RemoteException {
        name = myName;
    }

    public void addListener(IMPeerListener listener) {
        output = listener;
    }

    // send message to this peer
    public void sendMessage(Message message) throws RemoteException {
        output.displayMessage(message);
    }

    // accessor for name
    public String getName() throws RemoteException {
        return name;
    }
}

