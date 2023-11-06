package advanced_java2_deitel.chapter13_rmi.messenger;

// DisconnectListener.java
// DisconnectListener defines method serverDisconnected, which
// indicates that the server has disconnected the client.
public interface DisconnectListener {

    // receive notification that server disconnected
    public void serverDisconnected(String message);

}
