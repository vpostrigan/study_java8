// DragAndDropController.java
// DragAndDropController is a controller for handling drag and
// drop in DeitelDrawing. DragAndDropController implements
// DragGestureListener and DragSourceListener to handle drag
// events and DropTargetListener to handle drop events.
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import java.util.*;
import java.io.*;
import java.awt.Point;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyImage;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;

public class DragAndDropController implements DragGestureListener,
        DragSourceListener, DropTargetListener {

    // model to control
    private DrawingModel drawingModel;

    private boolean dragMode = false;

    public DragAndDropController(DrawingModel model) {
        drawingModel = model;
    }

    public void setDragMode(boolean drag) {
        dragMode = drag;
    }

    // recognize drag operation beginning (method of interface DragGestureListener)
    public void dragGestureRecognized(DragGestureEvent event) {
        // if not in dragMode, ignore drag gesture
        if (!dragMode)
            return;

        // get Point at which drag began
        Point origin = event.getDragOrigin();

        // get MyShapes from DrawingModel
        List shapes = new ArrayList(drawingModel.getShapes());

        // find top-most shape that contains drag origin (i.e.,
        // start at end of ListIterator and work backwards)
        ListIterator shapeIterator = shapes.listIterator(shapes.size());

        while (shapeIterator.hasPrevious()) {
            MyShape shape = (MyShape) shapeIterator.previous();
            if (shape.contains(origin)) {
                // create TransferableShape for dragging shape from Point origin
                TransferableShape transfer = new TransferableShape(shape, origin);

                // start drag operation
                event.startDrag(null, transfer, this);

                break;
            }
        }
    }

    // handle drop events (method of interface DropTargetListener)
    public void drop(DropTargetDropEvent event) {
        // get dropped object
        Transferable transferable = event.getTransferable();
        DataFlavor[] dataFlavors = transferable.getTransferDataFlavors();

        // get DropTargetDropEvent location
        Point location = event.getLocation();

        // process drops for supported types
        for (int i = 0; i < dataFlavors.length; i++) {
            DataFlavor dataFlavor = dataFlavors[i];

            // handle drop of JPEG images
            if (dataFlavor.equals(DataFlavor.javaFileListFlavor)) {

                // accept the drop operation
                event.acceptDrop(DnDConstants.ACTION_COPY);

                // attempt to drop the images and indicate whether drop is complete
                event.dropComplete(dropImages(transferable, location));
            } else if (dataFlavor.isMimeTypeEqual(TransferableShape.MIME_TYPE)) {

                // accept drop of TransferableShape
                event.acceptDrop(DnDConstants.ACTION_MOVE);

                // drop TransferableShape into drawing
                dropShape(transferable, location);

                // complete drop operation
                event.dropComplete(true);
            } else
                event.rejectDrop();
        }
    }

    // drop JPEG images onto drawing
    private boolean dropImages(Transferable transferable, Point location) {
        // boolean indicating successful drop
        boolean success = true;

        // attempt to drop images onto drawing
        try {
            // get list of dropped files
            List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);

            Iterator iterator = fileList.iterator();

            // search for JPEG images
            for (int i = 1; iterator.hasNext(); i++) {
                File file = (File) iterator.next();

                // if dropped file is a JPEG image, decode and add MyImage to drawingModel
                if (fileIsJPEG(file)) {

                    // create MyImage for given JPEG file
                    MyImage image = new MyImage();
                    image.setFileName(file.getPath());
                    image.setPoint1(location.x, location.y);

                    // add to DrawingModel
                    drawingModel.addShape(image);
                } else
                    success = false;
            }
        } catch (UnsupportedFlavorException flavorException) {
            success = false;
            flavorException.printStackTrace();
        } catch (IOException ioException) {
            success = false;
            ioException.printStackTrace();
        }
        return success;
    }

    // return true if File has .jpg or .jpeg extension
    private boolean fileIsJPEG(File file) {
        String fileName = file.getName().toLowerCase();

        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg");
    }

    // drop MyShape object onto drawing
    private void dropShape(Transferable transferable, Point location) {
        try {
            DataFlavor flavor = new DataFlavor(TransferableShape.MIME_TYPE, "Shape");

            // get TransferableShape object
            TransferableShape transferableShape =
                    (TransferableShape) transferable.getTransferData(flavor);

            // get MyShape and origin Point from TransferableShape
            MyShape shape = transferableShape.getShape();
            Point origin = transferableShape.getOrigin();

            // calculate offset for dropping MyShape
            int xOffSet = location.x - origin.x;
            int yOffSet = location.y - origin.y;

            shape.moveByOffSet(xOffSet, yOffSet);

            // add MyShape to target DrawingModel
            drawingModel.addShape(shape);
        } catch (UnsupportedFlavorException flavorException) {
            flavorException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // check for success when drag-and-drop operation ends
    // (method of interface DragSourceListener)
    public void dragDropEnd(DragSourceDropEvent event) {
        // if drop successful, remove MyShape from source DrawingModel
        if (event.getDropSuccess()) {

            // get Transferable object from DragSourceContext
            Transferable transferable =
                    event.getDragSourceContext().getTransferable();

            // get TransferableShape object from Transferable
            try {
                // get TransferableShape object
                TransferableShape transferableShape =
                        (TransferableShape) transferable.getTransferData(
                                new DataFlavor(TransferableShape.MIME_TYPE, "Shape"));

                // get MyShape from TransferableShape object
                // and remove from source DrawingModel
                drawingModel.removeShape(transferableShape.getShape());
            } catch (UnsupportedFlavorException flavorException) {
                flavorException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // required methods of interface DropTargetListener
    public void dragEnter(DropTargetDragEvent event) {
    }

    public void dragExit(DropTargetEvent event) {
    }

    public void dragOver(DropTargetDragEvent event) {
    }

    public void dropActionChanged(DropTargetDragEvent event) {
    }

    // required methods of interface DragSourceListener
    public void dragEnter(DragSourceDragEvent event) {
    }

    public void dragExit(DragSourceEvent event) {
    }

    public void dragOver(DragSourceDragEvent event) {
    }

    public void dropActionChanged(DragSourceDragEvent event) {
    }
}
