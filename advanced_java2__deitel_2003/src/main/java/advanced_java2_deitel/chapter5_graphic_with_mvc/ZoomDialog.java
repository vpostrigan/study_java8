// ZoomDialog.java
// ZoomDialog is a JDialog subclass that shows a zoomed view
// of a DrawingModel.
package advanced_java2_deitel.chapter5_graphic_with_mvc;

// Java core packages

// Java extension packages

import javax.swing.*;

// Deitel packages
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;
import advanced_java2_deitel.chapter5_graphic_with_mvc.view.ZoomDrawingView;

public class ZoomDialog extends JDialog {

    private ZoomDrawingView drawingView;
    private double zoomFactor = 0.5;

    // ZoomDialog constructor
    public ZoomDialog(DrawingModel model, String title) {
        // set ZoomDialog title
        setTitle(title);

        // create ZoomDrawingView for using default zoomFactor
        drawingView = new ZoomDrawingView(model, zoomFactor);

        // add ZoomDrawingView to ContentPane
        getContentPane().add(drawingView);

        // size ZoomDialog to fit ZoomDrawingView's preferred size
        pack();

        // make ZoomDialog visible
        setVisible(true);
    }

    // set JDialog title
    public void setTitle(String title) {
        super.setTitle(title + " [Zoom]");
    }
}