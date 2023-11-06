package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages

import java.rmi.*;

// StoppableChatServer.java
// StoppableChatServer is a remote interface that provides a
// mechansim to terminate the chat server.
public interface StoppableChatServer extends Remote {

    // stop ChatServer
    public void stopServer() throws RemoteException;
}
