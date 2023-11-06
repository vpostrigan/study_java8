// SplashScreen.java
// SplashScreen implements static method showSplash for 
// displaying a splash screen.
package advanced_java2_deitel.chapter5_graphic_with_mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SplashScreen {

    private JWindow window;
    private Timer timer;

    public SplashScreen(Component component) {
        // create new JWindow for splash screen
        window = new JWindow();

        // add provided component to JWindow
        window.getContentPane().add(component);

        // allow user to dismiss SplashScreen by clicking mouse
        window.addMouseListener(new MouseAdapter() {
            // when user presses mouse in SplashScreen,
            // hide and dispose JWindow
            public void mousePressed(MouseEvent event) {
                window.setVisible(false);
                window.dispose();
            }
        });

        // size JWindow for given Component
        window.pack();

        // get user's screen size
        Dimension screenDimension =
                Toolkit.getDefaultToolkit().getScreenSize();

        // calculate x and y coordinates to center splash screen
        int width = window.getSize().width;
        int height = window.getSize().height;
        int x = (screenDimension.width - width) / 2;
        int y = (screenDimension.height - height) / 2;

        // set the bounds of the window to center it on screen
        window.setBounds(x, y, width, height);

    } // end SplashScreen constructor

    // show splash screen for given delay
    public void showSplash(int delay) {

        // display the window
        window.setVisible(true);

        // crate and start a new Timer to remove SplashScreen
        // after specified delay
        timer = new Timer(delay, event -> {
            // hide and dispose of window
            window.setVisible(false);
            window.dispose();
            timer.stop();
        }
        );

        timer.start();

    } // end method showSplash

    // return true if SplashScreen window is visible
    public boolean isVisible() {
        return window.isVisible();
    }
}
