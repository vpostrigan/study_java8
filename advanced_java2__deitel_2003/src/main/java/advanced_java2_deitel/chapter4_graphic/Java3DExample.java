package advanced_java2_deitel.chapter4_graphic;

import javax.swing.*;
import java.awt.*;

/**
 * Download and install 'Download Java 3D 1.5.1 Software'
 * https://www.oracle.com/java/technologies/javase/java-3d.html
 *
 * will install data into JDK1.8 install dir
 */
public class Java3DExample extends JFrame {
    private Java3DWorld java3DWorld;
    private JPanel controlPanel;

    public Java3DExample() {
        super("Java 3D Graphic Demo");

        java3DWorld = new Java3DWorld("/advanced_java2_deitel/chapter4_graphic/Help24.gif");
        controlPanel = new ControlPanel(java3DWorld);

        getContentPane().add(java3DWorld, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        Java3DExample application = new Java3DExample();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
        application.pack();
        application.setVisible(true);
    }

}
