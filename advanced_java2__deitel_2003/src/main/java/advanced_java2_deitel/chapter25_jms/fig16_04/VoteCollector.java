package advanced_java2_deitel.chapter25_jms.fig16_04;

// Java core packages

import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Java extension packages
import javax.swing.*;
import javax.jms.*;
import javax.jms.Queue;
import javax.naming.*;

// VoteCollector.java
// VoteCollector tallies and displays the votes posted as TextMessages to the "Votes" queue.
public class VoteCollector extends JFrame {

    private JPanel displayPanel;
    private Map tallies = new HashMap<>();

    // JMS variables
    private QueueConnection queueConnection;

    public VoteCollector() {
        super("Vote Tallies");

        Container container = getContentPane();

        // displayPanel will display tally results
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(0, 1));
        container.add(new JScrollPane(displayPanel));

        // invoke method quit when window closed
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent event) {
                        quit();
                    }
                }
        );

        // connect to "Votes" queue
        try {
            // create JNDI context
            Context jndiContext = new InitialContext();

            // retrieve queue connection factory and queue from JNDI context
            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("VOTE_FACTORY");
            Queue queue = (Queue) jndiContext.lookup("Votes");

            // create connection, session and receiver
            queueConnection = queueConnectionFactory.createQueueConnection();
            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueReceiver queueReceiver = queueSession.createReceiver(queue);

            // initialize and set message listener
            queueReceiver.setMessageListener(new VoteListener(this));

            // start connection
            queueConnection.start();
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

    // add vote to corresponding tally
    public void addVote(String vote) {
        if (tallies.containsKey(vote)) {
            // if vote already has corresponding tally
            TallyPanel tallyPanel = (TallyPanel) tallies.get(vote);
            tallyPanel.updateTally();
        } else {
            // add to GUI and tallies
            TallyPanel tallyPanel = new TallyPanel(vote, 1);
            displayPanel.add(tallyPanel);
            tallies.put(vote, tallyPanel);
            validate();
        }
    }

    // quit application
    public void quit() {
        if (queueConnection != null) {
            // close the queue connection if it exists
            try {
                queueConnection.close();
            } catch (JMSException jmsException) {
                // process JMS exception
                jmsException.printStackTrace();
                System.exit(1);
            }
        }
        System.exit(0);
    }

    public static void main(String args[]) {
        VoteCollector voteCollector = new VoteCollector();
        voteCollector.setSize(200, 200);
        voteCollector.setVisible(true);
    }

}
