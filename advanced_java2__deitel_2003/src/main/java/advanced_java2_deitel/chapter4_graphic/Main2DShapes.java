package advanced_java2_deitel.chapter4_graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class Main2DShapes extends JFrame {

    public Main2DShapes() {
        super("Drawing 2D shapes");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        // [1]
        graphics2D.setPaint(new GradientPaint(5, 30, Color.blue, 35, 100, Color.yellow, true));
        graphics2D.fill(new Ellipse2D.Double(5, 30, 65, 100));

        // [2]
        graphics2D.setPaint(Color.red);
        graphics2D.setStroke(new BasicStroke(10.0f));
        graphics2D.draw(new Rectangle2D.Double(80, 30, 65, 100));

        // [3]
        BufferedImage bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.yellow);
        graphics.fillRect(0, 0, 10, 10);

        graphics.setColor(Color.black);
        graphics.fillRect(1, 1, 6, 6);

        graphics.setColor(Color.blue);
        graphics.fillRect(1, 1, 3, 3);

        graphics.setColor(Color.red);
        graphics.fillRect(4, 4, 3, 3);

        graphics2D.setPaint(new TexturePaint(bufferedImage, new Rectangle(10, 10)));
        graphics2D.fill(new RoundRectangle2D.Double(155, 30, 75, 100, 50, 50));

        // [4]
        graphics2D.setPaint(Color.white);
        graphics2D.setStroke(new BasicStroke(6.0f));
        graphics2D.draw(new Arc2D.Double(240, 30, 75, 100, 0, 270, Arc2D.PIE));

        // [5]
        graphics2D.setPaint(Color.green);
        graphics2D.draw(new Line2D.Double(395, 30, 320, 150));

        float[] dashes = {10, 2};
        graphics2D.setPaint(Color.yellow);
        graphics2D.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
        graphics2D.draw(new Line2D.Double(320, 30, 395, 150));
    }

    public static void main(String[] args) {
        Main2DShapes mainShapes = new Main2DShapes();
        mainShapes.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainShapes.setSize(430, 170);
        mainShapes.setVisible(true);
    }

}
