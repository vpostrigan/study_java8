// MyShapeController.java
// MyShapeController is an abstract base class that represents
// a controller for painting shapes.
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import java.awt.*;
import java.awt.event.*;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;

public abstract class MyShapeController {

    private DrawingModel drawingModel;

    // primary and secondary Colors for drawing and gradients
    private Color primaryColor = Color.black;
    private Color secondaryColor = Color.white;

    // Class object for creating new MyShape-subclass instances
    private Class shapeClass;

    // common MyShape properties
    private boolean fillShape = false;
    private boolean useGradient = false;
    private float strokeSize = 1.0f;

    // indicates whether the user has specified drag mode; if
    // true, MyShapeController should ignore mouse events
    private boolean dragMode = false;

    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;

    // MyShapeController constructor
    public MyShapeController(DrawingModel model, Class myShapeClass) {
        // set DrawingModel to control
        drawingModel = model;

        // set MyShape subclass
        shapeClass = myShapeClass;

        // listen for mouse events
        mouseListener = new MouseAdapter() {

            // when mouse button pressed, create new shape
            public void mousePressed(MouseEvent event) {
                // if not in dragMode, start new shape at given coordinates
                if (!dragMode)
                    startShape(event.getX(), event.getY());
            }

            // when mouse button released, set shape's final coordinates
            public void mouseReleased(MouseEvent event) {
                // if not in dragMode, finish drawing current shape
                if (!dragMode)
                    endShape(event.getX(), event.getY());
            }
        };

        // listen for mouse motion events
        mouseMotionListener = new MouseMotionAdapter() {

            // when mouse is dragged, set coordinates for current
            // shape's Point2
            public void mouseDragged(MouseEvent event) {
                // if not in dragMode, modify current shape
                if (!dragMode)
                    modifyShape(event.getX(), event.getY());
            }
        };
    }

    // set primary color (start color for gradient)
    public void setPrimaryColor(Color color) {
        primaryColor = color;
    }

    // get primary color
    public Color getPrimaryColor() {
        return primaryColor;
    }

    // set secondary color (end color for gradients)
    public void setSecondaryColor(Color color) {
        secondaryColor = color;
    }

    // get secondary color
    public Color getSecondaryColor() {
        return secondaryColor;
    }

    // fill shape
    public void setShapeFilled(boolean fill) {
        fillShape = fill;
    }

    // get shape filled
    public boolean getShapeFilled() {
        return fillShape;
    }

    // use gradient when painting shape
    public void setUseGradient(boolean gradient) {
        useGradient = gradient;
    }

    // get use gradient
    public boolean getUseGradient() {
        return useGradient;
    }

    // set dragMode
    public void setDragMode(boolean drag) {
        dragMode = drag;
    }

    // set stroke size for lines
    public void setStrokeSize(float stroke) {
        strokeSize = stroke;
    }

    // get stroke size
    public float getStrokeSize() {
        return strokeSize;
    }

    // create new instance of current MyShape subclass
    protected MyShape createNewShape() {
        // create new instance of current MyShape subclass
        try {
            MyShape shape = (MyShape) shapeClass.newInstance();

            // set MyShape properties
            shape.setFilled(fillShape);
            shape.setUseGradient(useGradient);
            shape.setStrokeSize(getStrokeSize());
            shape.setStartColor(getPrimaryColor());
            shape.setEndColor(getSecondaryColor());

            // return reference to newly created shape
            return shape;
        } catch (InstantiationException instanceException) {
            instanceException.printStackTrace();
            return null;
        } catch (IllegalAccessException accessException) {
            accessException.printStackTrace();
            return null;
        }
    }

    // get MyShapeController's MouseListener
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    // get MyShapeController's MouseMotionListener
    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }

    // add given shape to DrawingModel
    protected void addShapeToModel(MyShape shape) {
        drawingModel.addShape(shape);
    }

    // remove given shape from DrawingModel
    protected void removeShapeFromModel(MyShape shape) {
        drawingModel.removeShape(shape);
    }

    // start new shape
    public abstract void startShape(int x, int y);

    // modify current shape
    public abstract void modifyShape(int x, int y);

    // finish shape
    public abstract void endShape(int x, int y);
}
