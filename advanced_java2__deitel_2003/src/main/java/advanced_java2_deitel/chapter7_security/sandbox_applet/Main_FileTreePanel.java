package advanced_java2_deitel.chapter7_security.sandbox_applet;

// Java core packages

import java.io.*;
import java.awt.*;

// Java extension packages
import javax.swing.*;

// JPanel for displaying file system contents in a JTree using a custom TreeModel.
public class Main_FileTreePanel extends JPanel {

    // JTree for displaying file system
    private JTree fileTree;

    // FileSystemModel TreeModel implementation
    private FileSystemModel fileSystemModel;

    // JTextArea for displaying selected file's details
    private JTextArea fileDetailsTextArea;

    // FileTreePanel constructor
    public Main_FileTreePanel(String directory) {
        // create JTextArea for displaying File information
        fileDetailsTextArea = new JTextArea();
        fileDetailsTextArea.setEditable(false);

        // create FileSystemModel for given directory
        fileSystemModel = new FileSystemModel(new File(directory));

        // create JTree for FileSystemModel
        fileTree = new JTree(fileSystemModel);

        // make JTree editable for renaming Files
        fileTree.setEditable(true);

        // add a TreeSelectionListener display details of newly selected File when selection changes
        fileTree.addTreeSelectionListener(
                event -> {
                    File file = (File) fileTree.getLastSelectedPathComponent();
                    fileDetailsTextArea.setText(getFileDetails(file));
                }
        );

        // put fileTree and fileDetailsTextArea in a JSplitPane
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, true,
                new JScrollPane(fileTree),
                new JScrollPane(fileDetailsTextArea));

        setLayout(new BorderLayout());
        add(splitPane);
    }

    // get FileTreePanel preferred size
    public Dimension getPreferredSize() {
        return new Dimension(400, 200);
    }

    // build a String to display file details
    private String getFileDetails(File file) {
        // do not return details for null Files
        if (file == null)
            return "";

        // put File information in a StringBuffer
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + file.getName() + "\n");
        sb.append("Path: " + file.getPath() + "\n");
        sb.append("Size: " + file.length() + "\n");

        return sb.toString();
    }

    public static void main(String args[]) {
        // ensure that user provided directory name
        if (args.length != 1)
            System.err.println("Usage: java FileTreeFrame <path>");
            // start application using provided directory name
        else {
            // create JFrame and add FileTreePanel
            JFrame frame = new JFrame("JTree FileSystem Viewer");
            Main_FileTreePanel treePanel = new Main_FileTreePanel(args[0]);
            frame.getContentPane().add(treePanel);

            // configure and display JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }

}

