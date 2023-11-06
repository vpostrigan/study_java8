package advanced_java2_deitel.appG_jni.tint;

// JNIImageFrame.cpp extends JFrame, adds a scroll pane
// and serves as an entry point for the application

// Java core headers

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JNIImageFrame extends JFrame {
    // serves as an entry point, creates new JNIImageFrame
    public static void main(String[] args) {
        JNIImageFrame imageFrame = new JNIImageFrame();
    }

    // constructs a new JNIImageFrame containing JNIPanel
    public JNIImageFrame() {
        super("Deitel Image Processing");

        getContentPane().setLayout(new BorderLayout());

        // adds a new JNIPanel and defines image to process
        getContentPane().add(
                new JScrollPane(new JNIPanel("advjhtp1_small.jpg", new FlowLayout())),
                BorderLayout.CENTER);

        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        dispose();
                        System.exit(0);
                    }
                }
        );
        this.setResizable(false);
        setSize(670, 500);
        setVisible(true);
    }
}
