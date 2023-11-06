package advanced_java2_deitel.chapter3_mvc;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class MainPhilosophersJTree extends JFrame {

    private JTree tree;
    private DefaultTreeModel philosophers;
    private DefaultMutableTreeNode rootNode;

    public MainPhilosophersJTree() {
        super("Favorite Philosophers");

        // get tree of philosopher DefaultMutableTreeNodes
        DefaultMutableTreeNode philosophersNode = getPhilosopherTree();

        // create philosophers DefaultTreeModel
        philosophers = new DefaultTreeModel(philosophersNode);

        // create JTree for philosophers DefaultTreeModel
        tree = new JTree(philosophers);

        // create JButton for adding philosophers
        JButton addButton = new JButton("Add Philosopher");
        addButton.addActionListener(event -> addPhilosopher());

        // create JButton for removing selected philosopher
        JButton removeButton = new JButton("Remove Selected Philosopher");
        removeButton.addActionListener(event -> removeSelectedPhilosopher());

        // lay out GUI components
        JPanel inputPanel = new JPanel();
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        Container container = getContentPane();
        container.add(new JScrollPane(tree), BorderLayout.CENTER);
        container.add(inputPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    // add new philosopher to selected era
    private void addPhilosopher() {
        // get selected era
        DefaultMutableTreeNode parent = getSelectedNode();

        // ensure user selected era first
        if (parent == null) {
            JOptionPane.showMessageDialog(
                    MainPhilosophersJTree.this, "Select an era.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // prompt user for philosopher's name
        String name = JOptionPane.showInputDialog(MainPhilosophersJTree.this, "Enter Name:");

        // add new philosopher to selected era
        philosophers.insertNodeInto(
                new DefaultMutableTreeNode(name),
                parent, parent.getChildCount());
    }

    // remove currently selected philosopher
    private void removeSelectedPhilosopher() {
        // get selected node
        DefaultMutableTreeNode selectedNode = getSelectedNode();

        // remove selectedNode from model
        if (selectedNode != null)
            philosophers.removeNodeFromParent(selectedNode);
    }

    // get currently selected node
    private DefaultMutableTreeNode getSelectedNode() {
        // get selected DefaultMutableTreeNode
        return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    }

    // get tree of philosopher DefaultMutableTreeNodes
    private DefaultMutableTreeNode getPhilosopherTree() {
        // create rootNode
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Philosophers");

        // Ancient philosophers
        DefaultMutableTreeNode ancient = new DefaultMutableTreeNode("Ancient");
        rootNode.add(ancient);

        ancient.add(new DefaultMutableTreeNode("Socrates"));
        ancient.add(new DefaultMutableTreeNode("Plato"));
        ancient.add(new DefaultMutableTreeNode("Aristotle"));

        // Medieval philosophers
        DefaultMutableTreeNode medieval = new DefaultMutableTreeNode("Medieval");
        rootNode.add(medieval);

        medieval.add(new DefaultMutableTreeNode("St. Thomas Aquinas"));

        // Renaissance philosophers
        DefaultMutableTreeNode renaissance = new DefaultMutableTreeNode("Renaissance");
        rootNode.add(renaissance);

        renaissance.add(new DefaultMutableTreeNode("Thomas More"));

        // Early Modern philosophers
        DefaultMutableTreeNode earlyModern = new DefaultMutableTreeNode("Early Modern");
        rootNode.add(earlyModern);

        earlyModern.add(new DefaultMutableTreeNode("John Locke"));

        // Enlightenment Philosophers
        DefaultMutableTreeNode enlightenment = new DefaultMutableTreeNode("Enlightenment");
        rootNode.add(enlightenment);

        enlightenment.add(new DefaultMutableTreeNode("Immanuel Kant"));

        // 19th Century Philosophers
        DefaultMutableTreeNode nineteenth = new DefaultMutableTreeNode("19th Century");
        rootNode.add(nineteenth);

        nineteenth.add(new DefaultMutableTreeNode("Soren Kierkegaard"));
        nineteenth.add(new DefaultMutableTreeNode("Friedrich Nietzsche"));

        // 20th Century Philosophers
        DefaultMutableTreeNode twentieth = new DefaultMutableTreeNode("20th Century");
        rootNode.add(twentieth);

        twentieth.add(new DefaultMutableTreeNode("Hannah Arendt"));

        return rootNode;
    }

    public static void main(String args[]) {
        new MainPhilosophersJTree();
    }

}