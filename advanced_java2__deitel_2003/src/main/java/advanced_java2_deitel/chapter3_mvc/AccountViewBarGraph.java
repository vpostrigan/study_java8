package advanced_java2_deitel.chapter3_mvc;

import java.awt.*;

public class AccountViewBarGraph extends AccountViewAbs {

    public AccountViewBarGraph(Account account) {
        super(account);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        double balance = getAccount().getBalance();

        int barLength = (int) ((balance / 10_000) * 200);
        if (balance >= 0.0) {
            g.setColor(Color.black);
            g.fillRect(105, 15, barLength, 20);
        } else {
            g.setColor(Color.red);
            g.fillRect(105 + barLength, 15, -barLength, 20);
        }

        g.setColor(Color.black);
        g.drawLine(5, 25, 205, 25);
        g.drawLine(105, 5, 105, 45);

        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g.drawString("-$5,000", 5, 10);
        g.drawString("$0", 110, 10);
        g.drawString("+$5,000", 166, 10);
    }

    public void updateDisplay() {
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(210, 50);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

}
