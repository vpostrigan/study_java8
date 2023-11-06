package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainAccountManager extends JFrame {

    public MainAccountManager() {
        super("Account Manager");

        Account account1 = new Account("Account 1", 1000.00);
        JPanel account1Panel = createAccountPanel(account1);

        Account account2 = new Account("Account 2", 3000.00);
        JPanel account2Panel = createAccountPanel(account2);

        AssetPieChartView pieChartView = new AssetPieChartView();
        pieChartView.addAccount(account1);
        pieChartView.addAccount(account2);

        JPanel pieChartPanel = new JPanel();
        pieChartPanel.setBorder(new TitledBorder("Assets"));
        pieChartPanel.add(pieChartView);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(account1Panel);
        contentPane.add(account2Panel);
        contentPane.add(pieChartPanel);

        setSize(425, 450);
    }

    private JPanel createAccountPanel(Account account) {
        JPanel accountPanel = new JPanel();
        accountPanel.setBorder(new TitledBorder(account.getName()));

        AccountViewText accountTextView = new AccountViewText(account);
        AccountViewBarGraph accountBarGraphView = new AccountViewBarGraph(account);

        AccountController accountController = new AccountController(account);
        accountPanel.add(accountController);
        accountPanel.add(accountTextView);
        accountPanel.add(accountBarGraphView);

        return accountPanel;
    }

    public static void main(String[] args) {
        MainAccountManager manager = new MainAccountManager();
        manager.setDefaultCloseOperation(EXIT_ON_CLOSE);
        manager.setVisible(true);
    }

}
