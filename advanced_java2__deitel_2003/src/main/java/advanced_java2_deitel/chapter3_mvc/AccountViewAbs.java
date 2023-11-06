package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class AccountViewAbs extends JPanel implements Observer {
    private Account account;

    public AccountViewAbs(Account observableAccount) throws NullPointerException {
        if (observableAccount == null) {
            throw new NullPointerException();
        }

        account = observableAccount;
        account.addObserver(this);

        setBackground(Color.white);
        setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));
    }

    protected abstract void updateDisplay();

    public void update(Observable observable, Object object) {
        updateDisplay();
    }

    public Account getAccount() {
        return account;
    }
}
