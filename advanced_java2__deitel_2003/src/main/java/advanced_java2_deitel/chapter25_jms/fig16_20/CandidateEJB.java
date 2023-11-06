package advanced_java2_deitel.chapter25_jms.fig16_20;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.*;

// CandidateEJB.java
// CandidateEJB is an entity EJB that uses container-managed
// persistence to persist its Candidate and its vote tally.
public class CandidateEJB implements EntityBean {

    private EntityContext entityContext;

    // container-managed fields
    public Integer voteCount;
    public String name;

    // place vote for this Candidate
    public void incrementVoteCount() {
        int newVoteCount = voteCount.intValue() + 1;
        voteCount = new Integer(newVoteCount);
    }

    // get total vote count for this Candidate
    public Integer getVoteCount() {
        return voteCount;
    }

    // get Candidate's name
    public String getCandidateName() {
        return name;
    }

    // create new Candidate
    public String ejbCreate(String candidateName) throws CreateException {
        name = candidateName;
        voteCount = new Integer(0);

        return null;
    }

    // do post-creation tasks when creating new Candidate
    public void ejbPostCreate(String candidateName) {
    }

    // set EntityContext
    public void setEntityContext(EntityContext context) {
        entityContext = context;
    }

    // unset EntityContext
    public void unsetEntityContext() {
        entityContext = null;
    }

    // activate Candidate instance
    public void ejbActivate() {
        name = (String) entityContext.getPrimaryKey();
    }

    // passivate Candidate instance
    public void ejbPassivate() {
        name = null;
    }

    // load Candidate from database
    public void ejbLoad() {
    }

    // store Candidate in database
    public void ejbStore() {
    }

    // remove Candidate from database
    public void ejbRemove() {
    }
}
