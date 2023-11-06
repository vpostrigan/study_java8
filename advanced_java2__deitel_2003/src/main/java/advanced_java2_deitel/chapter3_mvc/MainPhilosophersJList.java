package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import java.awt.*;

public class MainPhilosophersJList extends JFrame {

    private DefaultListModel philosophers;
    private JList list;

    public MainPhilosophersJList() {
        super("Favorite Philosophers");

        // create a DefaultListModel to store philosophers
        philosophers = new DefaultListModel();
        philosophers.addElement("Socrates");
        philosophers.addElement("Plato");
        philosophers.addElement("Aristotle");
        philosophers.addElement("St. Thomas Aquinas");
        philosophers.addElement("Soren Kierkegaard");
        philosophers.addElement("Immanuel Kant");
        philosophers.addElement("Friedrich Nietzsche");
        philosophers.addElement("Hannah Arendt");

        // create a JList for philosophers DefaultListModel
        list = new JList(philosophers);

        // allow user to select only one philosopher at a time
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // create JButton for adding philosophers
        JButton addButton = new JButton("Add Philosopher");
        addButton.addActionListener(event -> {
            // prompt user for new philosopher's name
            String name = JOptionPane.showInputDialog(
                    MainPhilosophersJList.this, "Enter Name");

            // add new philosopher to model
            philosophers.addElement(name);
        });

        // create JButton for removing selected philosopher
        JButton removeButton = new JButton("Remove Selected Philosopher");
        removeButton.addActionListener(event -> {
            // remove selected philosopher from model
            philosophers.removeElement(list.getSelectedValue());
        });

        // lay out GUI components
        JPanel inputPanel = new JPanel();
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        Container container = getContentPane();
        container.add(list, BorderLayout.CENTER);
        container.add(inputPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MainPhilosophersJList();
    }

}
