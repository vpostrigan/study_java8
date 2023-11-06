// MyTextController.java
// MyTextController is a MyShapeController subclass for drawing
// MyText objects.
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyText;

import javax.swing.*;
import java.awt.*;

public class MyTextController extends MyShapeController {

    public MyTextController(DrawingModel model, Class shapeClass) {
        // invoke superclass constructor; always use MyText class
        super(model, MyText.class);
    }

    // start drawing MyText object
    public void startShape(int x, int y) {

        // create MyText shape
        MyText currentText = new MyText();

        // set MyText's Point1
        currentText.setPoint1(x, y);

        // create TextInputPanel to get text and properties
        TextInputPanel inputPanel = new TextInputPanel();

        // display TextInputPanel in JOptionPane
        String text = JOptionPane.showInputDialog(null, inputPanel);

        // ensure provided text is not null or empty
        if (text == null || text.equals(""))
            return;

        // set MyText properties (bold, italic, etc.)
        currentText.setBoldSelected(inputPanel.boldSelected());
        currentText.setItalicSelected(inputPanel.italicSelected());
        currentText.setUnderlineSelected(inputPanel.underlineSelected());

        currentText.setFontName(inputPanel.getSelectedFontName());
        currentText.setFontSize(inputPanel.getSelectedFontSize());
        currentText.setColor(getPrimaryColor());

        // set MyText's text
        currentText.setText(text);

        // add MyText object to model
        addShapeToModel(currentText);
    }

    // modify shape currently being drawn
    public void modifyShape(int x, int y) {
    }

    // finish drawing shape
    public void endShape(int x, int y) {
    }

    // JPanel with components for inputting MyText properties
    private static class TextInputPanel extends JPanel {

        private JCheckBox boldCheckBox;
        private JCheckBox italicCheckBox;
        private JCheckBox underlineCheckBox;
        private JComboBox fontComboBox;
        private JComboBox fontSizeComboBox;

        // TextInputPanel constructor
        public TextInputPanel() {
            boldCheckBox = new JCheckBox("Bold");
            italicCheckBox = new JCheckBox("Italic");
            underlineCheckBox = new JCheckBox("Underline");

            // create JComboBox for selecting Font
            fontComboBox = new JComboBox();
            fontComboBox.addItem("SansSerif");
            fontComboBox.addItem("Serif");

            // create JComboBox for selecting Font size
            fontSizeComboBox = new JComboBox();
            fontSizeComboBox.addItem("10");
            fontSizeComboBox.addItem("12");
            fontSizeComboBox.addItem("14");
            fontSizeComboBox.addItem("18");
            fontSizeComboBox.addItem("22");
            fontSizeComboBox.addItem("36");
            fontSizeComboBox.addItem("48");
            fontSizeComboBox.addItem("72");

            setLayout(new FlowLayout());

            add(boldCheckBox);
            add(italicCheckBox);
            add(underlineCheckBox);
            add(fontComboBox);
            add(fontSizeComboBox);
        }

        // get bold property
        public boolean boldSelected() {
            return boldCheckBox.isSelected();
        }

        // get italic property
        public boolean italicSelected() {
            return italicCheckBox.isSelected();
        }

        // get underline property
        public boolean underlineSelected() {
            return underlineCheckBox.isSelected();
        }

        // get font name property
        public String getSelectedFontName() {
            return fontComboBox.getSelectedItem().toString();
        }

        // get font size property
        public int getSelectedFontSize() {
            return Integer.parseInt(fontSizeComboBox.getSelectedItem().toString());
        }
    }

}
