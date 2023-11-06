package advanced_java2_deitel.chapter23_ejb.fig14_03;

// Java core libraries

import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.text.*;
import java.util.*;

// Java standard extensions
import javax.swing.*;
import javax.rmi.*;
import javax.naming.*;
import javax.ejb.*;

// InterestCalculatorClient.java
// InterestCalculatorClient is a GUI for interacting with the InterestCalculator EJB.
public class InterestCalculatorClient extends JFrame {

    // InterestCalculator remote reference
    private InterestCalculator calculator;

    private JTextField principalTextField;
    private JTextField rateTextField;
    private JTextField termTextField;
    private JTextField balanceTextField;
    private JTextField interestEarnedTextField;

    // InterestCalculatorClient constructor
    public InterestCalculatorClient() {
        super("Stateful Session EJB Example");

        // create InterestCalculator for calculating interest
        createInterestCalculator();

        // create JTextField for entering principal amount
        createPrincipalTextField();

        // create JTextField for entering interest rate
        createRateTextField();

        // create JTextField for entering loan term
        createTermTextField();

        // create uneditable JTextFields for displaying balance
        // and interest earned
        balanceTextField = new JTextField(10);
        balanceTextField.setEditable(false);

        interestEarnedTextField = new JTextField(10);
        interestEarnedTextField.setEditable(false);

        // layout components for GUI
        layoutGUI();

        // add WindowListener to remove EJB instances when user
        // closes window
        addWindowListener(getWindowListener());

        setSize(425, 200);
        setVisible(true);
    }

    // create InterestCalculator EJB instance
    public void createInterestCalculator() {
        // lookup InterestCalculatorHome and create InterestCalculator EJB
        try {
            InitialContext initialContext = new InitialContext();

            // lookup InterestCalculator EJB
            Object homeObject = initialContext.lookup("InterestCalculator");

            // get InterestCalculatorHome interface
            InterestCalculatorHome calculatorHome =
                    (InterestCalculatorHome) PortableRemoteObject.narrow(homeObject, InterestCalculatorHome.class);

            // create InterestCalculator EJB instance
            calculator = calculatorHome.create();
        } catch (NamingException namingException) {
            // handle exception if InterestCalculator EJB not found
            namingException.printStackTrace();
        } catch (RemoteException remoteException) {
            // handle exception when creating InterestCalculator EJB
            remoteException.printStackTrace();
        } catch (CreateException createException) {
            // handle exception when creating InterestCalculator EJB
            createException.printStackTrace();
        }
    }

    // create JTextField for entering principal amount
    public void createPrincipalTextField() {
        principalTextField = new JTextField(10);

        principalTextField.addActionListener(
                event -> {
                    // set principal amount in InterestCalculator
                    try {
                        double principal = Double.parseDouble(principalTextField.getText());

                        calculator.setPrincipal(principal);
                    } catch (RemoteException e) {
                        // handle exception setting principal amount
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        // handle wrong number format
                        e.printStackTrace();
                    }
                }
        );
    }

    // create JTextField for entering interest rate
    public void createRateTextField() {
        rateTextField = new JTextField(10);

        rateTextField.addActionListener(
                event -> {
                    // set interest rate in InterestCalculator
                    try {
                        double rate = Double.parseDouble(rateTextField.getText());

                        // convert from percentage
                        calculator.setInterestRate(rate / 100.0);
                    } catch (RemoteException remoteException) {
                        // handle exception when setting interest rate
                        remoteException.printStackTrace();
                    }
                }
        );
    }

    // create JTextField for entering loan term
    public void createTermTextField() {
        termTextField = new JTextField(10);

        termTextField.addActionListener(
                event -> {
                    // set loan term in InterestCalculator
                    try {
                        int term = Integer.parseInt(termTextField.getText());

                        calculator.setTerm(term);
                    } catch (RemoteException remoteException) {
                        // handle exception when setting loan term
                        remoteException.printStackTrace();
                    }
                }
        );
    }

    // get JButton for starting calculation
    public JButton getCalculateButton() {
        JButton calculateButton = new JButton("Calculate");

        // end method actionPerformed
        calculateButton.addActionListener(
                event -> {
                    // use InterestCalculator to calculate interest
                    try {
                        // get balance and interest earned
                        double balance = calculator.getBalance();
                        double interest = calculator.getInterestEarned();

                        NumberFormat dollarFormatter = NumberFormat.getCurrencyInstance(Locale.US);

                        balanceTextField.setText(dollarFormatter.format(balance));

                        interestEarnedTextField.setText(dollarFormatter.format(interest));
                    } catch (RemoteException remoteException) {
                        // handle exception when calculating interest
                        remoteException.printStackTrace();
                    }
                }
        );
        return calculateButton;
    }

    // lay out GUI components in JFrame
    public void layoutGUI() {
        Container contentPane = getContentPane();

        // layout user interface components
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Principal"));
        inputPanel.add(principalTextField);

        inputPanel.add(new JLabel("Interest Rate (%)"));
        inputPanel.add(rateTextField);

        inputPanel.add(new JLabel("Term (years)"));
        inputPanel.add(termTextField);

        inputPanel.add(new JLabel("Balance"));
        inputPanel.add(balanceTextField);

        inputPanel.add(new JLabel("Interest Earned"));
        inputPanel.add(interestEarnedTextField);

        // add inputPanel to contentPane
        contentPane.add(inputPanel, BorderLayout.CENTER);

        // create JPanel for calculateButton
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(getCalculateButton());
        contentPane.add(controlPanel, BorderLayout.SOUTH);
    }

    // get WindowListener for exiting application
    public WindowListener getWindowListener() {
        return new WindowAdapter() {

            public void windowClosing(WindowEvent event) {
                // check to see if calculator is null
                if (calculator.equals(null)) {
                    System.exit(-1);
                } else {
                    // remove InterestCalculator instance
                    try {
                        calculator.remove();
                    } catch (RemoveException removeException) {
                        // handle exception removing InterestCalculator
                        removeException.printStackTrace();
                        System.exit(-1);
                    } catch (RemoteException remoteException) {
                        // handle exception removing InterestCalculator
                        remoteException.printStackTrace();
                        System.exit(-1);
                    }

                    System.exit(0);
                }
            }
        };
    }

    public static void main(String[] args) {
        new InterestCalculatorClient();
    }

}
