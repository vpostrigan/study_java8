package advanced_java2_deitel.chapter23_ejb.fig14_27_transactions;

// Java core libraries

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

// MoneyTransferEJBClient.java
// MoneyTransferEJBClient is a client for interacting with the MoneyTransfer EJB.
public class MoneyTransferEJBClient extends JFrame {

    private MoneyTransfer moneyTransfer;

    private JTextField bankABCBalanceTextField;
    private JTextField bankXYZBalanceTextField;
    private JTextField transferAmountTextField;

    // MoneyTransferEJBClient constructor
    public MoneyTransferEJBClient(String JNDIName) {
        super("MoneyTransferEJBClient");

        // create MoneyTransfer EJB for transferring money
        createMoneyTransfer(JNDIName);

        // create and lay out GUI components
        createGUI();

        // display current balances at BankABC and BankXYZ
        displayBalances();

        setSize(400, 300);
        setVisible(true);
    }

    // create MoneyTransferEJB for transferring money
    private void createMoneyTransfer(String JNDIName) {
        // look up MoneyTransfer EJB using given JNDIName
        try {
            InitialContext context = new InitialContext();

            // lookup MoneyTransfer EJB
            Object homeObject = context.lookup(JNDIName);

            // get MoneyTransfer interface
            MoneyTransferHome moneyTransferHome = (MoneyTransferHome) PortableRemoteObject.narrow(homeObject, MoneyTransferHome.class);

            // create MathTool EJB instance
            moneyTransfer = moneyTransferHome.create();

        } catch (NamingException namingException) {
            // handle exception when looking up MoneyTransfer EJB
            namingException.printStackTrace();
        } catch (CreateException createException) {
            // handle exception when looking up MoneyTransfer EJB
            createException.printStackTrace();
        } catch (RemoteException remoteException) {
            // handle exception when looking up MoneyTransfer EJB
            remoteException.printStackTrace();
        }
    }

    // display balance in account at BankABC
    private void displayBalances() {
        try {
            // get and display BankABC Account balance
            double balance = moneyTransfer.getBankABCBalance();

            bankABCBalanceTextField.setText(String.valueOf(balance));

            // get and display BankXYZ Account balance
            balance = moneyTransfer.getBankXYZBalance();

            bankXYZBalanceTextField.setText(String.valueOf(balance));
        } catch (RemoteException remoteException) {
            // handle exception when invoke MoneyTransfer EJB methods
            JOptionPane.showMessageDialog(this, remoteException.getMessage());
        }
    }

    // create button to transfer funds between accounts
    private JButton getTransferButton() {
        JButton transferButton = new JButton("Transfer");

        // end method actionPerformed
        transferButton.addActionListener(
                event -> {
                    try {
                        // get transfer amount from JTextField
                        double amount = Double.parseDouble(transferAmountTextField.getText());

                        // transfer money
                        moneyTransfer.transfer(amount);

                        // display new balances
                        displayBalances();
                    } catch (RemoteException remoteException) {
                        // handle exception when transferring money
                        JOptionPane.showMessageDialog(MoneyTransferEJBClient.this, remoteException.getMessage());
                    }
                }
        );

        return transferButton;
    }

    // create and lay out GUI components
    private void createGUI() {
        // create JTextFields for user input and display
        bankABCBalanceTextField = new JTextField(10);
        bankABCBalanceTextField.setEditable(false);

        bankXYZBalanceTextField = new JTextField(10);
        bankXYZBalanceTextField.setEditable(false);

        transferAmountTextField = new JTextField(10);

        // create button to transfer between accounts
        JButton transferButton = getTransferButton();

        // layout user interface
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 2));

        contentPane.add(transferButton);
        contentPane.add(transferAmountTextField);

        contentPane.add(new JLabel("Bank ABC Balance: "));
        contentPane.add(bankABCBalanceTextField);

        contentPane.add(new JLabel("Bank XYZ Balance: "));
        contentPane.add(bankXYZBalanceTextField);
    }

    // get WindowListener for exiting application
    private WindowListener getWindowListener() {
        // remove MoneyTransfer EJB when user exits application
        return new WindowAdapter() {

            public void windowClosing(WindowEvent event) {

                // remove MoneyTransfer EJB
                try {
                    moneyTransfer.remove();
                } catch (RemoveException removeException) {
                    // handle exception removing MoneyTransfer EJB
                    removeException.printStackTrace();
                    System.exit(1);
                } catch (RemoteException remoteException) {
                    // handle exception removing MoneyTransfer EJB
                    remoteException.printStackTrace();
                    System.exit(1);
                }

                System.exit(0);
            } // end method windowClosing
        };
    }

    // execute application
    public static void main(String[] args) {
        // ensure user provided JNDI name for MoneyTransfer EJB
        if (args.length != 1)
            System.err.println("Usage: java MoneyTransferEJBClient <JNDI Name>");
            // start application using provided JNDI name
        else
            new MoneyTransferEJBClient(args[0]);
    }

}
