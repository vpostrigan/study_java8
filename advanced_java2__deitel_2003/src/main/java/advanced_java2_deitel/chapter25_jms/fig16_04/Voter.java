package advanced_java2_deitel.chapter25_jms.fig16_04;

// Java core packages

import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;
import javax.jms.*;
import javax.naming.*;

// Voter.java
// Voter is the GUI that allows the client to vote for a programming language.
// Voter sends the vote to the "Votes" queue as a TextMessage.
public class Voter extends JFrame {

    private String selectedLanguage;

    // JMS variables
    private QueueConnection queueConnection;
    private QueueSession queueSession;
    private QueueSender queueSender;

    public Voter() {
        // lay out user interface
        super("Voter");

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JTextArea voteArea = new JTextArea("Please vote for your\nfavorite programming language");
        voteArea.setEditable(false);
        container.add(voteArea, BorderLayout.NORTH);

        JPanel languagesPanel = new JPanel();
        languagesPanel.setLayout(new GridLayout(0, 1));

        // add each language as its own JCheckBox
        // ButtonGroup ensures exactly one language selected
        ButtonGroup languagesGroup = new ButtonGroup();
        CheckBoxHandler checkBoxHandler = new CheckBoxHandler();
        String languages[] = {"C", "C++", "Java", "Lisp", "Python"};
        selectedLanguage = "";

        // create JCheckBox for each language and add to ButtonGroup and JPanel
        for (int i = 0; i < languages.length; i++) {
            JCheckBox checkBox = new JCheckBox(languages[i]);
            checkBox.addItemListener(checkBoxHandler);
            languagesPanel.add(checkBox);
            languagesGroup.add(checkBox);
        }

        container.add(languagesPanel, BorderLayout.CENTER);

        // create button to submit vote
        JButton submitButton = new JButton("Submit vote!");
        container.add(submitButton, BorderLayout.SOUTH);

        // invoke method submitVote when submitButton clicked
        submitButton.addActionListener(
                event -> submitVote()
        );

        // invoke method quit when window closed
        addWindowListener(
                new WindowAdapter() {

                    public void windowClosing(WindowEvent event) {
                        quit();
                    }
                }
        );

        // connect to message queue
        try {
            // create JNDI context
            Context jndiContext = new InitialContext();

            // retrieve queue connection factory and queue from JNDI context
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("VOTE_FACTORY");
            Queue queue = (Queue) jndiContext.lookup("Votes");

            // create connection, session and sender
            queueConnection = queueConnectionFactory.createQueueConnection();
            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            queueSender = queueSession.createSender(queue);
        } catch (NamingException namingException) {
            // process Naming exception from JNDI context
            namingException.printStackTrace();
            System.exit(1);
        } catch (JMSException jmsException) {
            // process JMS exception from queue connection or session
            jmsException.printStackTrace();
            System.exit(1);
        }
    }

    // submit selected vote to "Votes" queue as TextMessage
    public void submitVote() {
        if (selectedLanguage != "") {
            // create text message containing selected language
            try {
                TextMessage voteMessage = queueSession.createTextMessage();
                voteMessage.setText(selectedLanguage);

                // send the message to the queue
                queueSender.send(voteMessage);
            } catch (JMSException jmsException) {
                // process JMS exception
                jmsException.printStackTrace();
            }
        }
    }

    // close client application
    public void quit() {
        if (queueConnection != null) {
            // close queue connection if it exists
            try {
                queueConnection.close();
            } catch (JMSException jmsException) {
                // process JMS exception
                jmsException.printStackTrace();
            }
        }
        System.exit(0);
    }

    // launch Voter application
    public static void main(String args[]) {
        Voter voter = new Voter();
        voter.pack();
        voter.setVisible(true);
    }

    // CheckBoxHandler handles event when checkbox checked
    private class CheckBoxHandler implements ItemListener {

        // checkbox event
        public void itemStateChanged(ItemEvent event) {
            // update selectedLanguage
            JCheckBox source = (JCheckBox) event.getSource();
            selectedLanguage = source.getText();
        }
    }

}
