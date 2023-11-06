// MyRectangle.java
// MyRectangle is a MyShape subclass that represents a 
// rectangle, including an implementation of the draw method
// for drawing the rectangle on a Graphics2D context.
package advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes;

import java.awt.*;
import java.awt.geom.*;

// third-party packages
import org.w3c.dom.*;

public class MyRectangle extends MyShape {

    // draw MyRectangle on given Graphics2D context
    public void draw(Graphics2D g2D) {
        // configure Graphics2D (gradient, color, etc.)
        configureGraphicsContext(g2D);

        // create Rectangle2D for drawing MyRectangle
        Shape shape = new Rectangle2D.Float(getLeftX(), getLeftY(), getWidth(), getHeight());

        // if shape is filled, draw filled shape
        if (isFilled())
            g2D.fill(shape);
        else
            g2D.draw(shape);
    }

    // return true if point falls within MyRectangle
    public boolean contains(Point2D point) {
        Rectangle2D.Float rectangle = new Rectangle2D.Float(
                getLeftX(), getLeftY(), getWidth(), getHeight());

        return rectangle.contains(point);
    }

    // get XML representation of MyRectangle
    public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyRectangle");

        return shapeElement;
    }
}
