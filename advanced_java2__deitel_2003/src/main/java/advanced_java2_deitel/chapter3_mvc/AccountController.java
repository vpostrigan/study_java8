package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import java.awt.*;

public class AccountController extends JPanel {
    private Account account;
    private JTextField amountTextField;

    public AccountController(Account controlledAccount) {
        super();

        account = controlledAccount;

        amountTextField = new JTextField(10);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(event -> {
            try {
                account.deposit(Double.parseDouble(amountTextField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(AccountController.this,
                        "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(event -> {
            try {
                account.withdraw(Double.parseDouble(amountTextField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(AccountController.this,
                        "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Amount: "));
        add(amountTextField);
        add(depositButton);
        add(withdrawButton);
    }

}
