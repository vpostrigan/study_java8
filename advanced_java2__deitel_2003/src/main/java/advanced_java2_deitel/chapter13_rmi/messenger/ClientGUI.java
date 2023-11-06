package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages

import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

// ClientGUI.java
// ClientGUI provides a GUI for sending and receiving chat messages using a MessageManager.
public class ClientGUI extends JFrame {

    // JLabel for displaying connection status
    private JLabel statusBar;

    // JTextAreas for displaying and inputting messages
    private JTextArea messageArea;
    private JTextArea inputArea;

    // Actions for connecting and disconnecting MessageManager and sending messages
    private Action connectAction;
    private Action disconnectAction;
    private Action sendAction;

    // userName to add to outgoing messages
    private String userName = "";

    // MessageManager for communicating with server
    MessageManager messageManager;

    // MessageListener for receiving new messages
    MessageListener messageListener;

    // ClientGUI constructor
    public ClientGUI(MessageManager manager) {
        super("Deitel Messenger");

        messageManager = manager;

        messageListener = new MyMessageListener();

        // create Actions
        connectAction = new ConnectAction();
        disconnectAction = new DisconnectAction();
        disconnectAction.setEnabled(false);
        sendAction = new SendAction();
        sendAction.setEnabled(false);

        // set up File menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        fileMenu.add(connectAction);
        fileMenu.add(disconnectAction);

        // set up JMenuBar and attach File menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // set up JToolBar
        JToolBar toolBar = new JToolBar();
        toolBar.add(connectAction);
        toolBar.add(disconnectAction);

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
        inputArea = new JTextArea(3, 15);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setEditable(false);

        // map Enter key in inputArea to sendAction
        Keymap keyMap = inputArea.getKeymap();
        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        keyMap.addActionForKeyStroke(enterKey, sendAction);

        // lay out inputArea and sendAction JButton in BoxLayout
        // and add Box to messagePanel
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(new JScrollPane(inputArea));
        box.add(new JButton(sendAction));

        panel.add(box, BorderLayout.SOUTH);

        // create statusBar JLabel with recessed border
        statusBar = new JLabel("Not Connected");
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));

        // lay out components
        Container container = getContentPane();
        container.add(toolBar, BorderLayout.NORTH);
        container.add(panel, BorderLayout.CENTER);
        container.add(statusBar, BorderLayout.SOUTH);

        // disconnect and exit if user closes window
        addWindowListener(
                new WindowAdapter() {
                    // disconnect MessageManager when window closes
                    public void windowClosing(WindowEvent event) {
                        // disconnect from chat server
                        try {
                            messageManager.disconnect(messageListener);
                        } catch (Exception exception) {
                            // handle exception disconnecting from server
                            exception.printStackTrace();
                        }

                        System.exit(0);
                    }
                }
        );
    }

    // Action for connecting to server
    private class ConnectAction extends AbstractAction {

        // configure ConnectAction
        public ConnectAction() {
            putValue(Action.NAME, "Connect");
            putValue(Action.SMALL_ICON, new ImageIcon(
                    ClientGUI.class.getResource("images/Connect.gif")));
            putValue(Action.SHORT_DESCRIPTION, "Connect to Server");
            putValue(Action.LONG_DESCRIPTION, "Connect to server to send Instant Messages");
            putValue(Action.MNEMONIC_KEY, new Integer('C'));
        }

        // connect to server
        public void actionPerformed(ActionEvent event) {
            // connect MessageManager to server
            try {

                // clear messageArea
                messageArea.setText("");

                // connect MessageManager and register MessageListener
                messageManager.connect(messageListener);

                // listen for disconnect notifications
                messageManager.setDisconnectListener(new DisconnectHandler());

                // get desired userName
                userName = JOptionPane.showInputDialog(ClientGUI.this, "Please enter your name: ");

                // update Actions, inputArea and statusBar
                connectAction.setEnabled(false);
                disconnectAction.setEnabled(true);
                sendAction.setEnabled(true);
                inputArea.setEditable(true);
                inputArea.requestFocus();
                statusBar.setText("Connected: " + userName);

                // send message indicating user connected
                messageManager.sendMessage(userName, userName + " joined chat");

            } catch (Exception exception) { // handle exception connecting to server
                JOptionPane.showMessageDialog(ClientGUI.this,
                        "Unable to connect to server.", "Error Connecting",
                        JOptionPane.ERROR_MESSAGE);

                exception.printStackTrace();
            }
        }
    }

    // Action for disconnecting from server
    private class DisconnectAction extends AbstractAction {

        // configure DisconnectAction
        public DisconnectAction() {
            putValue(Action.NAME, "Disconnect");
            putValue(Action.SMALL_ICON, new ImageIcon(
                    ClientGUI.class.getResource("images/Disconnect.gif")));
            putValue(Action.SHORT_DESCRIPTION, "Disconnect from Server");
            putValue(Action.LONG_DESCRIPTION, "Disconnect to end Instant Messaging session");
            putValue(Action.MNEMONIC_KEY, new Integer('D'));
        }

        // disconnect from server
        public void actionPerformed(ActionEvent event) {
            // disconnect MessageManager from server
            try {
                // send message indicating user disconnected
                messageManager.sendMessage(userName, userName + " exited chat");

                // disconnect from server and unregister MessageListener
                messageManager.disconnect(messageListener);

                // update Actions, inputArea and statusBar
                sendAction.setEnabled(false);
                disconnectAction.setEnabled(false);
                inputArea.setEditable(false);
                connectAction.setEnabled(true);
                statusBar.setText("Not Connected");

            } catch (Exception exception) {
                // handle exception disconnecting from server
                JOptionPane.showMessageDialog(ClientGUI.this,
                        "Unable to disconnect from server.",
                        "Error Disconnecting", JOptionPane.ERROR_MESSAGE);

                exception.printStackTrace();
            }
        }
    }

    // Action for sending messages
    private class SendAction extends AbstractAction {

        // configure SendAction
        public SendAction() {
            putValue(Action.NAME, "Send");
            putValue(Action.SMALL_ICON, new ImageIcon(
                    ClientGUI.class.getResource("images/Send.gif")));
            putValue(Action.SHORT_DESCRIPTION, "Send Message");
            putValue(Action.LONG_DESCRIPTION, "Send an Instant Message");
            putValue(Action.MNEMONIC_KEY, new Integer('S'));
        }

        // send message and clear inputArea
        public void actionPerformed(ActionEvent event) {
            // send message to server
            try {
                // send userName and text in inputArea
                messageManager.sendMessage(userName, inputArea.getText());

                inputArea.setText("");
            } catch (Exception exception) {
                // handle exception sending message
                JOptionPane.showMessageDialog(ClientGUI.this,
                        "Unable to send message.", "Error Sending Message",
                        JOptionPane.ERROR_MESSAGE);

                exception.printStackTrace();
            }
        }
    }

    // MyMessageListener listens for new messages from the
    // MessageManager and displays the messages in messageArea using a MessageDisplayer.
    private class MyMessageListener implements MessageListener {

        // when new message received, display in messageArea
        public void messageReceived(String from, String message) {
            // append message using MessageDisplayer and invokeLater
            // to ensure thread-safe access to messageArea
            SwingUtilities.invokeLater(new MessageDisplayer(from, message));
        }

    }

    // MessageDisplayer displays a new messaage by appending
    // the message to the messageArea JTextArea.
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
        @Override
        public void run() {
            // append new message
            messageArea.append("\n" + fromUser + "> " + messageBody);

            // move caret to end of messageArea to ensure new
            // message is visible on screen
            messageArea.setCaretPosition(messageArea.getText().length());
        }
    }

    // DisconnectHandler listens for serverDisconnected messages
    // from the MessageManager and updates the user interface.
    private class DisconnectHandler implements DisconnectListener {

        // receive disconnect notifcation
        public void serverDisconnected(final String message) {
            // update GUI in thread-safe manner update Actions, inputs and status bar
            SwingUtilities.invokeLater(
                    () -> {
                        sendAction.setEnabled(false);
                        disconnectAction.setEnabled(false);
                        inputArea.setEditable(false);
                        connectAction.setEnabled(true);
                        statusBar.setText(message);
                    }
            );
        }
    }

}
