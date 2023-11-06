package advanced_java2_deitel.chapter23_ejb.fig14_21;

// Java standard extensions

import javax.ejb.*;

// MathToolEJB.java
// MathToolEJB is a stateless session EJB with methods for
// calculating Fibonacci series and factorials.
public class MathToolEJB implements SessionBean {

    private SessionContext sessionContext;

    // get Fibonacci series
    public int[] getFibonacciSeries(int howMany) throws IllegalArgumentException {
        // throw IllegalArgumentException if series length is less than zero
        if (howMany < 2)
            throw new IllegalArgumentException("Cannot generate Fibonacci series of length less than two.");

        // starting points
        int startPoint1 = 0;
        int startPoint2 = 1;

        // array to contain Fibonacci sequence
        int[] series = new int[howMany];

        // set base cases
        series[0] = 0;
        series[1] = 1;

        // generate Fibonacci series
        for (int i = 2; i < howMany; i++) {

            // calculate next number in series
            series[i] = startPoint1 + startPoint2;

            // set start points for next iteration
            startPoint1 = startPoint2;
            startPoint2 = series[i];
        }

        return series;
    }

    // get factorial of given integer
    public int getFactorial(int number) throws IllegalArgumentException {
        // throw IllegalArgumentException if number less than zero
        if (number < 0)
            throw new IllegalArgumentException("Cannot calculate factorial of negative numbers.");

        // base case for recursion, return 1
        if (number == 0)
            return 1;
            // call getFactorial recursively to calculate factorial
        else
            return number * getFactorial(number - 1);
    }

    // set SessionContext
    public void setSessionContext(SessionContext context) {
        sessionContext = context;
    }

    // create new MathTool instance
    public void ejbCreate() {
    }

    // remove MathTool instance
    public void ejbRemove() {
    }

    // activate MathTool instance
    public void ejbActivate() {
    }

    // passivate MathTool instance
    public void ejbPassivate() {
    }

}
