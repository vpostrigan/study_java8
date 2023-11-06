// TransferableShape.java
// TransferableShape is a Transferable object that contains a 
// MyShape and the point from which the user dragged that MyShape.
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TransferableShape implements Transferable {

    // the MyShape to transfer from Point origin
    private MyShape shape;
    private Point origin;

    // MIME type that identifies dragged MyShapes
    public static final String MIME_TYPE = "application/x-deitel-shape";

    // DataFlavors that MyShape supports for drag and drop
    private static final DataFlavor[] flavors = new DataFlavor[]{
            new DataFlavor(MIME_TYPE, "Shape")};

    public TransferableShape(MyShape myShape, Point originPoint) {
        shape = myShape;
        origin = originPoint;
    }

    public Point getOrigin() {
        return origin;
    }

    public MyShape getShape() {
        return shape;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    // determine if MyShape supports given data flavor
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        // search for given DataFlavor in flavors array
        for (int i = 0; i < flavors.length; i++)
            if (flavor.equals(flavors[i]))
                return true;
        return false;
    }

    // get data to be transferred for given DataFlavor
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (!isDataFlavorSupported(flavor))
            throw new UnsupportedFlavorException(flavor);
        return this;
    }

}
