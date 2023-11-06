package advanced_java2_deitel.chapter23_ejb.fig14_03;

// Java core libraries

import java.util.*;

// Java standard extensions
import javax.ejb.*;

// InterestCalculatorEJB.java
// InterestCalculator is a stateful session EJB for calculating simple interest.
public class InterestCalculatorEJB implements SessionBean {

    private SessionContext sessionContext;

    // state variables
    private double principal;
    private double interestRate;
    private int term;

    // set principal amount
    public void setPrincipal(double amount) {
        principal = amount;
    }

    // set interest rate
    public void setInterestRate(double rate) {
        interestRate = rate;
    }

    // set loan length in years
    public void setTerm(int years) {
        term = years;
    }

    // get loan balance
    public double getBalance() {
        // calculate simple interest
        return principal * Math.pow(1.0 + interestRate, term);
    }

    // get amount of interest earned
    public double getInterestEarned() {
        return getBalance() - principal;
    }

    // set SessionContext
    public void setSessionContext(SessionContext context) {
        sessionContext = context;
    }

    // create InterestCalculator instance
    public void ejbCreate() {
    }

    // remove InterestCalculator instance
    public void ejbRemove() {
    }

    // passivate InterestCalculator instance
    public void ejbPassivate() {
    }

    // activate InterestCalculator instance
    public void ejbActivate() {
    }

}
