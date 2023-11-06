package advanced_java2_deitel.chapter18_jxta.jini_p2p;

// Java core package

import java.io.Serializable;

// Message.java
// Message represents an object that can be sent to an IMPeer;
// contains the sender and content of the message.
public class Message implements Serializable {
    private static final long SerialVersionUID = 20010808L;

    private String from;
    private String content;

    // Message constructor
    public Message(String messageSenderName, String messageContent) {
        from = messageSenderName;
        content = messageContent;
    }

    @Override
    public String toString() {
        return from + ": " + content + "\n";
    }

    // get Message sender's name
    public String getSenderName() {
        return from;
    }

    // get Message content
    public String getContent() {
        return content;
    }
}
