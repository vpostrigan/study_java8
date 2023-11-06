package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class AssetPieChartView extends JPanel implements Observer {

    private List accounts = new ArrayList<>();
    private Map colors = new HashMap<>();

    public void addAccount(Account account) {
        if (account == null)
            throw new NullPointerException();
        accounts.add(account);
        colors.put(account, getRandomColor());
        account.addObserver(this);

        repaint();
    }

    public void removeAccount(Account account) {
        account.deleteObserver(this);
        accounts.remove(account);
        colors.remove(account);

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawPieChart(g);

        drawLegend(g);
    }

    private void drawPieChart(Graphics g) {
        double totalBalance = getTotalBalance();

        double percentage = 0.0;
        int startAngle = 0;
        int arcAngle = 0;

        Iterator accountIterator = accounts.iterator();
        Account account = null;

        while (accountIterator.hasNext()) {
            account = (Account) accountIterator.next();

            if (!includeAccountInChart(account))
                continue;

            percentage = account.getBalance() / totalBalance;

            arcAngle = (int) Math.round(percentage * 360);

            g.setColor((Color) colors.get(account));
            g.fillArc(5, 5, 100, 100, startAngle, arcAngle);

            startAngle += arcAngle;
        }
    }

    private void drawLegend(Graphics g) {
        Iterator accountIterator = accounts.iterator();
        Account account = null;

        Font font = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(font);

        FontMetrics metrics = getFontMetrics(font);
        int ascent = metrics.getMaxAscent();
        int offsetY = ascent + 2;

        for (int i = 1; accountIterator.hasNext(); i++) {
            account = (Account) accountIterator.next();

            g.setColor((Color) colors.get(account));
            g.fillRect(125, offsetY * i, ascent, ascent);

            g.setColor(Color.black);
            g.drawString(account.getName(), 140, offsetY * i + ascent);
        }
    }

    private double getTotalBalance() {
        double sum = 0.0;

        Iterator accountIterator = accounts.iterator();
        Account account = null;

        while (accountIterator.hasNext()) {
            account = (Account) accountIterator.next();

            if (includeAccountInChart(account))
                sum += account.getBalance();
        }

        return sum;
    }

    protected boolean includeAccountInChart(Account account) {
        return account.getBalance() > 0.0;
    }

    private Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return new Color(red, green, blue);
    }

    public void update(Observable observable, Object object) {
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(210, 110);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

}
