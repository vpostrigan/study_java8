package advanced_java2_deitel.chapter18_jxta.multicast_p2p;

// Java core packages

import advanced_java2_deitel.chapter18_jxta.jini_p2p.Message;

import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

// Java extension packages
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

// IMPeerListener.java
// IMPeerListener extends JFrame and provides GUI for conversations with other peers
public class IMPeerListener extends JFrame {

    // JTextAreas for displaying and inputting messages
    private JTextArea messageArea;
    private JTextArea inputArea;

    // Actions for sending messages, etc.
    private Action sendAction;

    // userName to add to outgoing messages
    private String userName = "";

    // IMPeer to send messages to peer
    private IMPeer remotePeer;

    // constructor
    public IMPeerListener(String name) {
        super("Conversation Window");

        // set user name
        userName = name;

        // init sendAction
        sendAction = new SendAction();

        // create JTextArea for displaying messages
        messageArea = new JTextArea(15, 15);

        // disable editing and wrap words at end of line
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // create JTextArea for entering new messages
        inputArea = new JTextArea(4, 12);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        // map Enter key in inputArea area to sendAction
        Keymap keyMap = inputArea.getKeymap();
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        keyMap.addActionForKeyStroke(enterKey, sendAction);

        // lay out inputArea and sendAction JButton in Box
        Box box = Box.createVerticalBox();
        box.add(new JScrollPane(inputArea));
        box.add(new JButton(sendAction));

        panel.add(box, BorderLayout.SOUTH);

        // lay out components
        Container container = getContentPane();
        container.add(panel, BorderLayout.CENTER);

        setSize(200, 400);
        setVisible(true);
    }

    // Action for sending messages
    private class SendAction extends AbstractAction {

        public SendAction() {
            putValue(Action.NAME, "Send");
            putValue(Action.SHORT_DESCRIPTION, "Send Message");
            putValue(Action.LONG_DESCRIPTION, "Send an Instant Message");
        }

        // send message and clear inputArea
        public void actionPerformed(ActionEvent event) {
            // send message to server
            try {
                Message message = new Message(userName, inputArea.getText());

                // use RMI reference to send a Message
                remotePeer.sendMessage(message);

                // clear inputArea
                inputArea.setText("");
                displayMessage(message);
            } catch (RemoteException remoteException) { // catch error sending message
                JOptionPane.showMessageDialog(null, "Unable to send message.");

                remoteException.printStackTrace();
            }
        }
    }

    public void displayMessage(Message message) {
        // displayMessage uses SwingUntilities.invokeLater
        // to ensure thread-safe access to messageArea
        SwingUtilities.invokeLater(new MessageDisplayer(message.getSenderName(), message.getContent()));
    }

    // MessageDisplayer displays a new message by appending the message to the messageArea JTextArea.
    // This Runnable object should be executed only on the event-dispatch
    // thread, as it modifies a live Swing component.
    private class MessageDisplayer implements Runnable {

        private String fromUser;
        private String messageBody;

        public MessageDisplayer(String from, String body) {
            fromUser = from;
            messageBody = body;
        }

        // display new message in messageArea
        public void run() {
            // append new message
            messageArea.append("\n" + fromUser + "> " + messageBody);

            // move caret to end of messageArea to ensure new message is visible on screen
            messageArea.setCaretPosition(messageArea.getText().length());
        }
    }

    // addPeer takes IMPeer as arg associates IMPeer with sendAction to send messages
    public void addPeer(IMPeer peer) throws RemoteException {
        remotePeer = peer;

        // change title of window to name of peer
        setTitle(remotePeer.getName());
    }

}
