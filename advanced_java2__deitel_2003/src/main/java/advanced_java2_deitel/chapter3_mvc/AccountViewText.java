package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.Locale;

public class AccountViewText extends AccountViewAbs {
    private JTextField balanceTextField = new JTextField(10);

    private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public AccountViewText(Account account) {
        super(account);

        balanceTextField.setEditable(false);

        add(new JLabel("Balance: "));
        add(balanceTextField);

        updateDisplay();
    }

    @Override
    public void updateDisplay() {
        balanceTextField.setText(moneyFormat.format(getAccount().getBalance()));
    }

}
