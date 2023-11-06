package advanced_java2_deitel.chapter23_ejb.fig14_21;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.EJBHome;
import javax.ejb.CreateException;

// MathToolHome.java
// MathToolHome is the home interface for the MathTool EJB.
public interface MathToolHome extends EJBHome {

    // create new MathTool EJB
    public MathTool create() throws RemoteException, CreateException;

}
