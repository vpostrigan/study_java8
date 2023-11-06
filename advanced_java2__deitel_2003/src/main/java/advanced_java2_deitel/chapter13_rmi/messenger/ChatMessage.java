package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages

import java.io.*;

// ChatMessage.java
// ChatMessage is a Serializable object for messages in the RMI ChatClient and ChatServer.
public class ChatMessage implements Serializable {

    private String sender;   // person sending message
    private String message;  // message being sent

    // construct empty ChatMessage
    public ChatMessage() {
        this("", "");
    }

    // construct ChatMessage with sender and message values
    public ChatMessage(String sender, String message) {
        setSender(sender);
        setMessage(message);
    }

    // set name of person sending message
    public void setSender(String name) {
        sender = name;
    }

    // get name of person sending message
    public String getSender() {
        return sender;
    }

    // set message being sent
    public void setMessage(String messageBody) {
        message = messageBody;
    }

    // get message being sent
    public String getMessage() {
        return message;
    }

    // String representation of ChatMessage
    @Override
    public String toString() {
        return getSender() + "> " + getMessage();
    }

}
