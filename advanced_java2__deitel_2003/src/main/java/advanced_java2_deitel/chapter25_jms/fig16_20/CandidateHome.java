package advanced_java2_deitel.chapter25_jms.fig16_20;

// Java core libraries

import java.rmi.*;
import java.util.*;

// Java standard extensions
import javax.ejb.*;

// CandidateHome.java
// CandidateHome is the home interface for the Candidate EJB.
public interface CandidateHome extends EJBHome {

    // find Candidate with given name
    public Candidate findByPrimaryKey(String candidateName) throws RemoteException, FinderException;

    // find all Candidates
    public Collection findAllCandidates() throws RemoteException, FinderException;

    // create new Candidate EJB
    public Candidate create(String candidateName) throws RemoteException, CreateException;
}
