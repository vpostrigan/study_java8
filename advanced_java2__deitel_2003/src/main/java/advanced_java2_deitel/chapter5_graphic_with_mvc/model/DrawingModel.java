// DrawingModel.java
// DrawingModel is the model for a DeitelDrawing painting. It 
// provides methods for adding and removing shapes from a 
// drawing.
package advanced_java2_deitel.chapter5_graphic_with_mvc.model;

import java.util.*;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;

public class DrawingModel extends Observable {

    // shapes contained in model
    private Collection shapes;

    public DrawingModel() {
        shapes = new ArrayList();
    }

    public void addShape(MyShape shape) {
        shapes.add(shape);

        // send model changed notification
        fireModelChanged();
    }

    public void removeShape(MyShape shape) {
        shapes.remove(shape);

        // send model changed notification
        fireModelChanged();
    }

    // get Collection of shapes in model
    public Collection getShapes() {
        return Collections.unmodifiableCollection(shapes);
    }

    // set Collection of shapes in model
    public void setShapes(Collection newShapes) {
        // copy Collection into new ArrayList
        shapes = new ArrayList(newShapes);

        // send model changed notification
        fireModelChanged();
    }

    // empty the current ArrayList of shapes
    public void clear() {
        shapes = new ArrayList();

        // send model changed notification
        fireModelChanged();
    }

    // send model changed notification
    private void fireModelChanged() {
        // set model changed flag
        setChanged();

        // notify Observers that model changed
        notifyObservers();
    }
}
