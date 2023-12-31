// DrawingView.java
// DrawingView is a view of a DrawingModel that draws shapes using
// the Java2D API.
package advanced_java2_deitel.chapter5_graphic_with_mvc.view;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;

public class DrawingView extends JPanel implements Observer {

    // model for which this is a view
    private DrawingModel drawingModel;

    public DrawingView(DrawingModel model) {
        // set DrawingModel
        drawingModel = model;

        // set background color
        setBackground(Color.white);

        // enable double buffering to reduce screen flicker
        setDoubleBuffered(true);
    }

    // set DrawingModel for view to given model
    public void setModel(DrawingModel model) {
        if (drawingModel != null)
            drawingModel.deleteObserver(this);

        drawingModel = model;

        // register view as observer of model
        if (model != null) {
            model.addObserver(this);
            repaint();
        }
    }

    // get DrawingModel associated with this view
    public DrawingModel getModel() {
        return drawingModel;
    }

    // repaint view when update received from model
    public void update(Observable observable, Object object) {
        repaint();
    }

    // overridden paintComponent method for drawing shapes
    public void paintComponent(Graphics g) {
        // call superclass paintComponent
        super.paintComponent(g);

        // create Graphics2D object for given Graphics object
        Graphics2D g2D = (Graphics2D) g;

        // enable anti-aliasing to smooth jagged lines
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // enable high-quality rendering in Graphics2D object
        g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // draw all shapes in model
        drawShapes(g2D);
    }

    // draw shapes in model
    public void drawShapes(Graphics2D g2D) {
        // get Iterator for shapes in model
        Iterator iterator = drawingModel.getShapes().iterator();

        // draw each MyShape in DrawingModel
        while (iterator.hasNext()) {
            MyShape shape = (MyShape) iterator.next();
            shape.draw(g2D);
        }
    }

    // get preferred size for this component
    public Dimension getPreferredSize() {
        return new Dimension(320, 240);
    }

    // insist on preferred size for this component
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    // insist on preferred size for this component
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    // add DrawingView as Observer of DrawingModel when
    // DrawingView obtains screen resources
    public void addNotify() {
        super.addNotify();
        drawingModel.addObserver(this);
    }

    // remove DrawingView as Observer of DrawingModel when
    // DrawingView loses screen resources
    public void removeNotify() {
        super.removeNotify();
        drawingModel.deleteObserver(this);
    }
}
