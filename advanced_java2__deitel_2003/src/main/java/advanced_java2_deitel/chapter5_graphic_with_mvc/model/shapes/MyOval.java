// MyOval.java
// MyOval is a MyShape subclass that represents an oval.
package advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes;

import java.awt.*;
import java.awt.geom.*;

// third-party packages
import org.w3c.dom.*;

public class MyOval extends MyShape {

    // draw MyOval on given Graphics2D context
    public void draw(Graphics2D g2D) {
        // configure Graphics2D (gradient, color, etc.)
        configureGraphicsContext(g2D);

        // create Ellipse2D for drawing oval
        Shape shape = new Ellipse2D.Float(getLeftX(),
                getLeftY(), getWidth(), getHeight());

        // if shape is filled, draw filled shape
        if (isFilled())
            g2D.fill(shape);
            // draw shape
        else
            g2D.draw(shape);
    }

    // return true if point falls inside MyOval
    public boolean contains(Point2D point) {
        Ellipse2D.Float ellipse = new Ellipse2D.Float(
                getLeftX(), getLeftY(), getWidth(), getHeight());

        return ellipse.contains(point);
    }

    // get MyOval XML representation
    public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyOval");

        return shapeElement;
    }
}
