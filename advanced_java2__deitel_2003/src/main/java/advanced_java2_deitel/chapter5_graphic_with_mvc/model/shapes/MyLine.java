// MyLine.java
// MyLine is a MyShape subclass that represents a line.
package advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes;

import java.awt.*;
import java.awt.geom.*;

import org.w3c.dom.*;

public class MyLine extends MyShape {

    // draw MyLine object on given Graphics2D context
    public void draw(Graphics2D g2D) {
        // configure Graphics2D (gradient, color, etc.)
        configureGraphicsContext(g2D);

        // create new Line2D.Float
        Shape line = new Line2D.Float(getX1(), getY1(), getX2(), getY2());

        // draw shape
        g2D.draw(line);
    }

    // determine if MyLine contains given Point2D
    public boolean contains(Point2D point) {
        // get Point1 and Point2 coordinates
        float x1 = getX1();
        float x2 = getX2();
        float y1 = getY1();
        float y2 = getY2();

        // determines slope of line
        float slope = (y2 - y1) / (x2 - x1);

        // determines slope from point argument and Point1
        float realSlope = (float) ((point.getY() - y1) / (point.getX() - x1));

        // return true if slope and realSlope are close in value
        return Math.abs(realSlope - slope) < 0.1;
    }

    // get MyLine XML representation
    public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyLine");

        return shapeElement;
    }

}
