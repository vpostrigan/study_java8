package advanced_java2_deitel.chapter24_ejb_with_data.fig15_07;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.EJBObject;

// Employee.java
// Employee is the remote interface for the Address EJB.
public interface Employee extends EJBObject {

    // get Employee ID
    public Integer getEmployeeID() throws RemoteException;

    // set social security number
    public void setSocialSecurityNumber(String number) throws RemoteException;

    // get social security number
    public String getSocialSecurityNumber() throws RemoteException;

    // set first name
    public void setFirstName(String name) throws RemoteException;

    // get first name
    public String getFirstName() throws RemoteException;

    // set last name
    public void setLastName(String name) throws RemoteException;

    // get last name
    public String getLastName() throws RemoteException;

    // set title
    public void setTitle(String title) throws RemoteException;

    // get title
    public String getTitle() throws RemoteException;

    // set salary
    public void setSalary(Double salary) throws RemoteException;

    // get salary
    public Double getSalary() throws RemoteException;
}
