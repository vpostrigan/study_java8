package advanced_java2_deitel.chapter23_ejb.fig14_27_transactions;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extenstions
import javax.ejb.EJBObject;

// MoneyTransfer.java
// MoneyTransfer is the remote interface for the MoneyTransfer EJB.
public interface MoneyTransfer extends EJBObject {

    // transfer amount from BankABC to BankXYZ
    public void transfer(double amount) throws RemoteException;

    // get  BankABC account balance
    public double getBankABCBalance() throws RemoteException;

    // get BankXYZ account balance
    public double getBankXYZBalance() throws RemoteException;
}
