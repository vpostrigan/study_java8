package advanced_java2_deitel.chapter23_ejb.fig14_21;

// Java core libraries

import java.rmi.RemoteException;

// Java standard extensions
import javax.ejb.EJBObject;

// MathTool.java
// MathTool is the remote interface for the MathTool EJB.
public interface MathTool extends EJBObject {
    // get Fibonacci series
    public int[] getFibonacciSeries(int howMany) throws RemoteException, IllegalArgumentException;

    // get factorial of given integer
    public int getFactorial(int number) throws RemoteException, IllegalArgumentException;
}
