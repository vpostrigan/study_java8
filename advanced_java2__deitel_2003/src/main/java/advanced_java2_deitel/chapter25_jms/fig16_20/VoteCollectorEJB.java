package advanced_java2_deitel.chapter25_jms.fig16_20;

// Java core packages

import java.util.*;
import java.rmi.*;

// Java extension packages
import javax.ejb.*;
import javax.rmi.*;
import javax.jms.*;
import javax.naming.*;

// VoteCollectorEJB.java
// VoteCollectorEJB is a MessageDriven EJB that tallies votes.
public class VoteCollectorEJB implements MessageDrivenBean, MessageListener {

    private MessageDrivenContext messageDrivenContext;

    // receive new message
    public void onMessage(Message message) {
        TextMessage voteMessage;

        // retrieve and process message
        try {
            if (message instanceof TextMessage) {
                voteMessage = (TextMessage) message;
                String vote = voteMessage.getText();
                countVote(vote);

                System.out.println("Received vote: " + vote);
            } else {
                System.out.println("Expecting TextMessage object, received " + message.getClass().getName());
            }
        } catch (JMSException jmsException) { // process JMS exception from message
            jmsException.printStackTrace();
        }
    }

    // add vote to corresponding tally
    private void countVote(String vote) {
        // CandidateHome reference for finding/creating Candidates
        CandidateHome candidateHome = null;

        // find Candidate and increment vote count
        try {
            // look up Candidate EJB
            Context initialContext = new InitialContext();

            Object object = initialContext.lookup("java:comp/env/ejb/Candidate");

            candidateHome = (CandidateHome) PortableRemoteObject.narrow(object, CandidateHome.class);

            // find Candidate for whom the user voted
            Candidate candidate = candidateHome.findByPrimaryKey(vote);

            // increment Candidate's vote count
            candidate.incrementVoteCount();
        } catch (FinderException finderException) { // if Candidate not found, create new Candidate
            // create new Candidate and increment its vote count
            try {
                Candidate newCandidate = candidateHome.create(vote);
                newCandidate.incrementVoteCount();
            } catch (Exception exception) { // handle exceptions creating new Candidate
                throw new EJBException(exception);
            }
        } catch (NamingException namingException) { // handle exception when looking up OrderProducts EJB
            throw new EJBException(namingException);
        } catch (RemoteException remoteException) { // handle exception when invoking OrderProducts methods
            throw new EJBException(remoteException);
        }
    }

    // set message driven context
    public void setMessageDrivenContext(MessageDrivenContext context) {
        messageDrivenContext = context;
    }

    // create bean instance
    public void ejbCreate() {
    }

    // remove bean instance
    public void ejbRemove() {
    }

}
