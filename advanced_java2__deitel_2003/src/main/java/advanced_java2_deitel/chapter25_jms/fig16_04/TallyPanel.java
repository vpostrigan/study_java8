package advanced_java2_deitel.chapter25_jms.fig16_04;

// Java core packages

import java.awt.*;

// Java extension packages
import javax.swing.*;

// TallyPanel.java
// TallyPanel is the GUI component which displays the name and tally for a vote candidate.
public class TallyPanel extends JPanel {

    private JLabel nameLabel;
    private JTextField tallyField;
    private String name;
    private int tally;

    public TallyPanel(String voteName, int voteTally) {
        name = voteName;
        tally = voteTally;

        nameLabel = new JLabel(name);
        tallyField = new JTextField(Integer.toString(tally), 10);
        tallyField.setEditable(false);
        tallyField.setBackground(Color.white);

        add(nameLabel);
        add(tallyField);
    }

    // update tally by one vote
    public void updateTally() {
        tally++;
        tallyField.setText(Integer.toString(tally));
    }

}
