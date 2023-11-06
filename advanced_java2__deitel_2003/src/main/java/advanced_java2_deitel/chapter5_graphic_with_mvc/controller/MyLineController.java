// MyLineController.java
// MyLineController is a MyShapeController subclass for MyLines.
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;

public class MyLineController extends MyShapeController {

    private MyShape currentShape;

    public MyLineController(DrawingModel model, Class shapeClass) {
        super(model, shapeClass);
    }

    // start drawing new shape
    public void startShape(int x, int y) {
        // create new shape
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

        // set current ( x, y ) to Point1
        currentShape.setPoint1(x, y);

        // set Point2 to StartPoint
        currentShape.setPoint2(startX, startY);

        // add shape back into model
        addShapeToModel(currentShape);
    }

    // finish drawing shape
    public void endShape(int x, int y) {
        modifyShape(x, y);
    }
}
