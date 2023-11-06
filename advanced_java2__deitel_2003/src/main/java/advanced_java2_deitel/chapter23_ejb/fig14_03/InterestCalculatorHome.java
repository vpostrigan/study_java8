package advanced_java2_deitel.chapter23_ejb.fig14_03;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.*;

// InterestCalculatorHome.java
// InterestCalculatorHome is the home interface for the
// InterestCalculator EJB.
public interface InterestCalculatorHome extends EJBHome {

    // create InterestCalculator EJB
    public InterestCalculator create() throws RemoteException, CreateException;

}

