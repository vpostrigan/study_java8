package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages
import java.rmi.*;

// ChatClient.java
// ChatClient is a remote interface that defines methods for a
// chat client to receive messages and status information from a ChatServer.
public interface ChatClient extends Remote {

    // method called by server to deliver message to client
    public void deliverMessage(ChatMessage message) throws RemoteException;

    // method called when server shuting down
    public void serverStopping() throws RemoteException;

}
