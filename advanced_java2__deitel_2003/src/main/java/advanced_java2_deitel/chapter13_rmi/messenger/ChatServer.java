package advanced_java2_deitel.chapter13_rmi.messenger;

import java.rmi.*;

// ChatServer.java
// ChatServer is a remote interface that defines how a client
// registers for a chat, leaves a chat and posts chat messages.
public interface ChatServer extends Remote {

    // register new ChatClient with ChatServer
    public void registerClient(ChatClient client) throws RemoteException;

    // unregister ChatClient with ChatServer
    public void unregisterClient(ChatClient client) throws RemoteException;

    // post new message to ChatServer
    public void postMessage(ChatMessage message) throws RemoteException;

}
