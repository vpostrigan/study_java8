package advanced_java2_deitel.chapter25_jms.fig16_20;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.EJBObject;

// Candidate.java
// Candidate is the remote interface for the Candidate EJB, which maintains a tally of votes.
public interface Candidate extends EJBObject {

    // place vote for this Candidate
    public void incrementVoteCount() throws RemoteException;

    // get total vote count for this Candidate
    public Integer getVoteCount() throws RemoteException;

    // get Candidate's name
    public String getCandidateName() throws RemoteException;
}
