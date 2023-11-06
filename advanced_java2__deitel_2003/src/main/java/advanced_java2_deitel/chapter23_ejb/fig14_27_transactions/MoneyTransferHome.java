package advanced_java2_deitel.chapter23_ejb.fig14_27_transactions;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.*;

// MoneyTransferHome.java
// MoneyTransferHome is the home interface for the MoneyTransferHome EJB.
public interface MoneyTransferHome extends EJBHome {

    // create MoneyTransfer EJB
    public MoneyTransfer create() throws RemoteException, CreateException;

}
