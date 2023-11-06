package advanced_java2_deitel.chapter23_ejb.fig14_03;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.EJBObject;

// InterestCalculator.java
// InterestCalculator is the remote interface for the InterestCalculator EJB.
public interface InterestCalculator extends EJBObject {

    // set principal amount
    public void setPrincipal(double amount) throws RemoteException;

    // set interest rate
    public void setInterestRate(double rate) throws RemoteException;

    // set loan length in years
    public void setTerm(int years) throws RemoteException;

    // get loan balance
    public double getBalance() throws RemoteException;

    // get amount of interest earned
    public double getInterestEarned() throws RemoteException;
}
