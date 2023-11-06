package advanced_java2_deitel.chapter7_security.sandbox_applet;

import javax.swing.*;

// A JApplet that browses files on the local file system using a FileTreePanel.
public class FileTreeApplet extends JApplet {

    // initialize JApplet
    public void init() {
        // get rootDirectory from user
        String rootDirectory = JOptionPane.showInputDialog(this, "Please enter a directory name:");

        // create FileTreePanel for browsing user's hard drive
        Main_FileTreePanel panel = new Main_FileTreePanel(rootDirectory);

        getContentPane().add(panel);
    }

}