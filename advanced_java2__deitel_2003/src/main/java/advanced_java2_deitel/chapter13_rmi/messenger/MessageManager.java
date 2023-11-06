package advanced_java2_deitel.chapter13_rmi.messenger;

// MessageManager.java
// MessageManger is an interface for objects capable of managing
// communications with a message server.
public interface MessageManager {

    // connect to message server and route incoming messages
    // to given MessageListener
    public void connect(MessageListener listener) throws Exception;

    // disconnect from message server and stop routing
    // incoming messages to given MessageListener
    public void disconnect(MessageListener listener) throws Exception;

    // send message to message server
    public void sendMessage(String from, String message) throws Exception;

    // set listener for disconnect notifications
    public void setDisconnectListener(DisconnectListener listener);

}