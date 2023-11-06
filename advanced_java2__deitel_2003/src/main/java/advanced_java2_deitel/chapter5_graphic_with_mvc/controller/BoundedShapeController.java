// BoundedShapeController.java
// BoundedShapeController is a MyShapeController subclass for 
// rectangle-bounded shapes, such as MyOvals and MyRectangles.
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;

public class BoundedShapeController extends MyShapeController {

    private MyShape currentShape;

    public BoundedShapeController(DrawingModel model, Class shapeClass) {
        super(model, shapeClass);
    }

    // start drawing shape
    public void startShape(int x, int y) {
        // get new shape
        currentShape = createNewShape();

        if (currentShape != null) {

            // set location of shape in drawing
            currentShape.setPoint1(x, y);
            currentShape.setPoint2(x, y);
            currentShape.setStartPoint(x, y);

            // add newly created shape to DrawingModel
            addShapeToModel(currentShape);
        }
    }

    // modify shape currently being drawn
    public void modifyShape(int x, int y) {
        // remove shape from DrawingModel
        removeShapeFromModel(currentShape);
        currentShape.setEndPoint(x, y);

        int startX = currentShape.getStartX();
        int startY = currentShape.getStartY();

        // set Point1 to upper-left coordinates of shape
        currentShape.setPoint1(Math.min(x, startX), Math.min(y, startY));
        // set Point2 to lower right coordinates of shape
        currentShape.setPoint2(Math.max(x, startX), Math.max(y, startY));

        addShapeToModel(currentShape);
    }

    // finish drawing shape
    public void endShape(int x, int y) {
        modifyShape(x, y);
    }

}
