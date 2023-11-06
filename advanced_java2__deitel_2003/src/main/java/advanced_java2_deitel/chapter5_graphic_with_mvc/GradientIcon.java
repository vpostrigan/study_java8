// GradientIcon.java
// GradientIcon is an Icon implementation that draws a 16 x 16
// gradient from startColor to endColor.
package advanced_java2_deitel.chapter5_graphic_with_mvc;

import java.awt.*;
import javax.swing.*;

public class GradientIcon implements Icon {

    // Colors to use for gradient
    private Color startColor;
    private Color endColor;

    public GradientIcon(Color start, Color end) {
        setStartColor(start);
        setEndColor(end);
    }

    // set gradient start color
    public void setStartColor(Color start) {
        startColor = start;
    }

    // get gradient start color
    public Color getStartColor() {
        return startColor;
    }

    // set gradient end color
    public void setEndColor(Color end) {
        endColor = end;
    }

    // get gradient end color
    public Color getEndColor() {
        return endColor;
    }

    // get icon width
    public int getIconWidth() {
        return 16;
    }

    // get icon height
    public int getIconHeight() {
        return 16;
    }

    // draw icon at given location on given component
    public void paintIcon(Component component, Graphics g, int x, int y) {
        // get Graphics2D object
        Graphics2D g2D = (Graphics2D) g;

        // set GradientPaint
        g2D.setPaint(new GradientPaint(x, y,
                getStartColor(), 16, 16,
                getEndColor()));

        // fill rectangle with gradient
        g2D.fillRect(x, y, 16, 16);
    }

}

