package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages
import java.rmi.RMISecurityManager;

// DeitelMessenger.java
// DeitelMessenger uses a ClientGUI and RMIMessageManager to
// implement an RMI-based chat client.
public class DeitelMessenger {

    // launch DeitelMessenger application
    public static void main(String args[]) throws Exception {
        // install RMISecurityManager
        System.setSecurityManager(new RMISecurityManager());

        MessageManager messageManager;

        // create new DeitelMessenger
        if (args.length == 0)
            messageManager = new RMIMessageManager("localhost");
        else
            messageManager = new RMIMessageManager(args[0]);

        // finish configuring window and display it
        ClientGUI clientGUI = new ClientGUI(messageManager);
        clientGUI.pack();
        clientGUI.setResizable(false);
        clientGUI.setVisible(true);
    }

}
