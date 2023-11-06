package advanced_java2_deitel.chapter2_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainActionSample extends JFrame {
    private Action sampleAction;
    private Action exitAction;

    public MainActionSample() {
        super("Using Actions");

        sampleAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainActionSample.this, "The sampleAction was invoked");
                exitAction.setEnabled(true);
            }
        };
        sampleAction.putValue(Action.NAME, "Sample Action");
        sampleAction.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("Help24.gif")));
        sampleAction.putValue(Action.SHORT_DESCRIPTION, "A Sample Action");
        sampleAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf('S'));

        exitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainActionSample.this, "The exitAction was invoked");
                System.exit(0);
            }
        };
        exitAction.putValue(Action.NAME, "Exit");
        exitAction.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("Exit.gif")));
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit Application");
        exitAction.putValue(Action.MNEMONIC_KEY, Integer.valueOf('x'));
        exitAction.setEnabled(false);

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(sampleAction);
        fileMenu.add(exitAction);
        fileMenu.setMnemonic('F');

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        toolBar.add(sampleAction);
        toolBar.add(exitAction);

        JButton sampleButton = new JButton();
        sampleButton.setAction(sampleAction);

        JButton exitButton = new JButton(exitAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sampleButton);
        buttonPanel.add(exitButton);

        Container container = getContentPane();
        container.add(toolBar, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainActionSample sample = new MainActionSample();
        sample.setDefaultCloseOperation(EXIT_ON_CLOSE);
        sample.pack();
        sample.setVisible(true);
    }

}
