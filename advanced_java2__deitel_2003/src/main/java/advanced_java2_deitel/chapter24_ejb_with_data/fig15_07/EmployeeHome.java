package advanced_java2_deitel.chapter24_ejb_with_data.fig15_07;

// Java core libraries

import java.rmi.*;

// Java standard extensions
import javax.ejb.*;

// EmployeeHome.java
// EmployeeHome is the home interface for the Employee EJB.
public interface EmployeeHome extends EJBHome {

    // find Employee with given primary key
    public Employee findByPrimaryKey(Integer primaryKey) throws RemoteException, FinderException;

    // create new Employee EJB
    public Employee create(Integer primaryKey) throws RemoteException, CreateException;

}
