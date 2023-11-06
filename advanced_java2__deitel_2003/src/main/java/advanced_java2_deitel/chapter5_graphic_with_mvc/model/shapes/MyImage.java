// MyImage.java
// MyImage is a MyShape subclass that contains a JPEG image.
package advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImage extends MyShape {

    private BufferedImage image;
    private String fileName;

    public void draw(Graphics2D g2D) {
        // draw image on Graphics2D context
        g2D.drawImage(getImage(), getX1(), getY1(), null);
    }

    // return true if Point falls within MyImage
    public boolean contains(Point2D point) {
        Rectangle2D.Float rectangle =
                new Rectangle2D.Float(getX1(), getY1(), getWidth(), getHeight());
        return rectangle.contains(point);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setFileName(String name) {
        try {
            File file = new File(name);
            BufferedImage image = ImageIO.read(file);

            setPoint2(getX1() + image.getWidth(), getY1() + image.getHeight());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyImage");

        // create filename Element
        Element temp = document.createElement("fileName");
        temp.appendChild(document.createTextNode(getFileName()));
        shapeElement.appendChild(temp);

        return shapeElement;
    }

}
