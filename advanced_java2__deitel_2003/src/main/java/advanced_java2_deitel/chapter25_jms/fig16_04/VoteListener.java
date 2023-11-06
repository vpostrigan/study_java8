package advanced_java2_deitel.chapter25_jms.fig16_04;

// Java extension packages

import javax.jms.*;

// VoteListener.java
// VoteListener is the message listener for the receiver of the "Votes" queue.
// It implements the specified onMessage method to update the GUI with the received vote.
public class VoteListener implements MessageListener {

    private VoteCollector voteCollector;

    public VoteListener(VoteCollector collector) {
        voteCollector = collector;
    }

    // receive new message
    @Override
    public void onMessage(Message message) {
        TextMessage voteMessage;

        // retrieve and process message
        try {
            if (message instanceof TextMessage) {
                voteMessage = (TextMessage) message;
                String vote = voteMessage.getText();
                voteCollector.addVote(vote);

                System.out.println("Received vote: " + vote);
            } else {
                System.out.println("Expecting TextMessage object, received " + message.getClass().getName());
            }
        } catch (JMSException jmsException) {
            // process JMS exception from message
            jmsException.printStackTrace();
        }
    }

}
