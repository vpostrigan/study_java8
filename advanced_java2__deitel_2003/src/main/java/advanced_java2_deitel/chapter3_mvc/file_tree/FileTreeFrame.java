package advanced_java2_deitel.chapter3_mvc.file_tree;

import javax.swing.*;
import java.io.File;

public class FileTreeFrame extends JFrame {

    // JTree for displaying file system
    private JTree fileTree;

    // FileSystemModel TreeModel implementation
    private FileSystemModel fileSystemModel;

    // JTextArea for displaying selected file's details
    private JTextArea fileDetailsTextArea;

    public FileTreeFrame(String directory) {
        super("JTree FileSystem Viewer");

        // create JTextArea for displaying File information
        fileDetailsTextArea = new JTextArea();
        fileDetailsTextArea.setEditable(false);

        // create FileSystemModel for given directory
        fileSystemModel = new FileSystemModel(new File(directory));

        // create JTree for FileSystemModel
        fileTree = new JTree(fileSystemModel);
        fileTree.setEditable(true);

        // add a TreeSelectionListener
        // display details of newly selected File when
        // selection changes
        fileTree.addTreeSelectionListener(event -> {
            File file = (File) fileTree.getLastSelectedPathComponent();

            fileDetailsTextArea.setText(getFileDetails(file));
        });

        // put fileTree and fileDetailsTextArea in a JSplitPane
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, true,
                new JScrollPane(fileTree),
                new JScrollPane(fileDetailsTextArea));

        getContentPane().add(splitPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    private String getFileDetails(File file) {
        if (file == null)
            return "";

        // put File information in a StringBuffer
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + file.getName() + "\n");
        buffer.append("Path: " + file.getPath() + "\n");
        buffer.append("Size: " + file.length() + "\n");

        return buffer.toString();
    }

    public static void main(String args[]) {
        // ensure that user provided directory name
        if (args.length != 1)
            System.err.println("Usage: java FileTreeFrame <path>");
            // start application using provided directory name
        else
            new FileTreeFrame(args[0]);
    }

}