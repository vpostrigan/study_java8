package advanced_java2_deitel.chapter23_ejb.fig14_21;

// Java core libraries

import java.awt.*;
import java.awt.event.*;
import java.rmi.*;

// Java standard extensions
import javax.swing.*;
import javax.rmi.*;
import javax.naming.*;
import javax.ejb.*;

// MathToolClient.java
// MathToolClient is a GUI for calculating factorials and Fibonacci series using the MathTool EJB.
public class MathToolClient extends JFrame {

    private MathToolHome mathToolHome;
    private MathTool mathTool;

    private JTextArea resultsTextArea;
    private JTextField numberTextField;

    // MathToolClient constructor
    public MathToolClient() {
        super("Stateless Session EJB Example");

        // create MathTool for calculating factorials and Fibonacci series
        createMathTool();

        // create and lay out GUI components
        createGUI();

        addWindowListener(getWindowListener());

        setSize(425, 200);
        setVisible(true);
    }

    // create MathTool EJB instance
    private void createMathTool() {
        // lookup MathToolHome and create MathTool EJB
        try {
            InitialContext initialContext = new InitialContext();

            // lookup MathTool EJB
            Object homeObject = initialContext.lookup("MathTool");

            // get MathToolHome interface
            mathToolHome = (MathToolHome) PortableRemoteObject.narrow(homeObject, MathToolHome.class);

            // create MathTool EJB instance
            mathTool = mathToolHome.create();
        } catch (NamingException e) {
            // handle exception if MathTool EJB is not found
            e.printStackTrace();
        } catch (RemoteException e) {
            // handle exception when creating MathTool EJB
            e.printStackTrace(System.err);
        } catch (CreateException e) {
            // handle exception when creating MathTool EJB
            e.printStackTrace(System.err);
        }
    }

    // create JButton for calculating factorial
    private JButton getFactorialButton() {
        JButton factorialButton = new JButton("Calculate Factorial");

        // add ActionListener for factorial button end method actionPerformed
        factorialButton.addActionListener(
                event -> {
                    // use MathTool EJB to calculate factorial
                    try {
                        int number = Integer.parseInt(numberTextField.getText());

                        // get Factorial of number input by user
                        int result = mathTool.getFactorial(number);

                        // display results in resultsTextArea
                        resultsTextArea.setText(number + "! = " + result);
                    } catch (RemoteException remoteException) {
                        // handle exception calculating factorial
                        remoteException.printStackTrace();
                    }
                }
        );

        return factorialButton;
    }

    // create JButton for generating Fibonacci series
    private JButton getFibonacciButton() {
        JButton fibonacciButton = new JButton("Fibonacci Series");

        // add ActionListener for generating Fibonacci series end method actionPerformed
        fibonacciButton.addActionListener(
                event -> {
                    // generate Fibonacci series using MathTool EJB
                    try {
                        // get number entered by user
                        int number = Integer.parseInt(numberTextField.getText());

                        // get Fibonacci series
                        int[] series = mathTool.getFibonacciSeries(number);

                        // create StringBuffer to store series
                        StringBuffer buffer = new StringBuffer("The first ");
                        buffer.append(number);
                        buffer.append(" Fibonacci number(s): \n");

                        // append each number in series to buffer
                        for (int i = 0; i < series.length; i++) {
                            // do not add comma before first number
                            if (i != 0)
                                buffer.append(", ");

                            // append next number in series to buffer
                            buffer.append(String.valueOf(series[i]));
                        }

                        // display series in resultsTextArea
                        resultsTextArea.setText(buffer.toString());

                    } catch (RemoteException remoteException) {
                        // handle exception calculating series
                        remoteException.printStackTrace();
                    }
                }
        );

        return fibonacciButton;
    }

    // create lay out GUI components
    public void createGUI() {
        // create JTextArea to show results
        resultsTextArea = new JTextArea();
        resultsTextArea.setLineWrap(true);
        resultsTextArea.setWrapStyleWord(true);
        resultsTextArea.setEditable(false);

        // create JTextField for user input
        numberTextField = new JTextField(10);

        // create JButton for calculating factorial
        JButton factorialButton = getFactorialButton();

        // create JButton for generating Fibonacci series
        JButton fibonacciButton = getFibonacciButton();

        Container contentPane = getContentPane();

        // put resultsTextArea in a JScrollPane
        JScrollPane resultsScrollPane = new JScrollPane(resultsTextArea);

        contentPane.add(resultsScrollPane, BorderLayout.CENTER);

        // add input components to new JPanel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter an integer: "));
        inputPanel.add(numberTextField);

        // add JButton components to new JPanel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(factorialButton);
        buttonPanel.add(fibonacciButton);

        // add inputPanel and buttonPanel to new JPanel
        JPanel controlPanel = new JPanel(new GridLayout(2, 2));

        controlPanel.add(inputPanel);
        controlPanel.add(buttonPanel);

        contentPane.add(controlPanel, BorderLayout.NORTH);
    }

    // get WindowListener for exiting application
    private WindowListener getWindowListener() {
        return new WindowAdapter() {

            public void windowClosing(WindowEvent event) {
                // remove MathTool instance
                try {
                    mathTool.remove();
                } catch (RemoveException removeException) {
                    // handle exception when removing MathTool EJB
                    removeException.printStackTrace();
                    System.exit(-1);
                } catch (RemoteException remoteException) {
                    // handle exception when removing MathTool EJB
                    remoteException.printStackTrace();
                    System.exit(-1);
                }

                System.exit(0);
            } // end method windowClosing
        };
    }

    // execute application
    public static void main(String[] args) {
        MathToolClient client = new MathToolClient();
    }

}
