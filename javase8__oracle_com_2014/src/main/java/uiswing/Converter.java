/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package uiswing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

/*
 * A application that requires the following files:
 *   ConversionPanel.java
 *   ConverterRangeModel.java
 *   FollowerRangeModel.java
 *   Unit.java
 */
public class Converter {
    ConversionPanel metricPanel, usaPanel;
    Unit[] metricDistances = new Unit[3];
    Unit[] usaDistances = new Unit[4];
    final static boolean MULTICOLORED = false;

    //Specify the look and feel to use.  Valid values:
    //null (use the default), "Metal", "System", "Motif", "GTK+"
    final static String LOOKANDFEEL = null;

    ConverterRangeModel dataModel = new ConverterRangeModel();
    JPanel mainPane;

    /**
     * Create the ConversionPanels (one for metric, another for U.S.).
     * I used "U.S." because although Imperial and U.S. distance
     * measurements are the same, this program could be extended to
     * include volume measurements, which aren't the same.
     */
    public Converter() {
        //Create Unit objects for metric distances, and then
        //instantiate a ConversionPanel with these Units.
        metricDistances[0] = new Unit("Centimeters", 0.01);
        metricDistances[1] = new Unit("Meters", 1.0);
        metricDistances[2] = new Unit("Kilometers", 1000.0);
        metricPanel = new ConversionPanel(this, "Metric System", metricDistances, dataModel);

        //Create Unit objects for U.S. distances, and then
        //instantiate a ConversionPanel with these Units.
        usaDistances[0] = new Unit("Inches", 0.0254);
        usaDistances[1] = new Unit("Feet", 0.305);
        usaDistances[2] = new Unit("Yards", 0.914);
        usaDistances[3] = new Unit("Miles", 1613.0);
        usaPanel = new ConversionPanel(this, "U.S. System", usaDistances, new FollowerRangeModel(dataModel));

        //Create a JPanel, and add the ConversionPanels to it.
        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        if (MULTICOLORED) {
            mainPane.setOpaque(true);
            mainPane.setBackground(new Color(255, 0, 0));
        }
        mainPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(metricPanel);
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(usaPanel);
        mainPane.add(Box.createGlue());
        resetMaxValues(true);
    }

    public void resetMaxValues(boolean resetCurrentValues) {
        double metricMultiplier = metricPanel.getMultiplier();
        double usaMultiplier = usaPanel.getMultiplier();
        int maximum = ConversionPanel.MAX;

        if (metricMultiplier > usaMultiplier) {
            maximum = (int) (ConversionPanel.MAX * (usaMultiplier / metricMultiplier));
        }

        dataModel.setMaximum(maximum);

        if (resetCurrentValues) {
            dataModel.setDoubleValue(maximum);
        }
    }

    private static void initLookAndFeel() {
        String lookAndFeel = null;

        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (LOOKANDFEEL.equals("GTK+")) { //new in 1.4.2
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: " + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
                UIManager.setLookAndFeel(lookAndFeel);
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:" + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
                System.err.println("Using the default look and feel.");
            } catch (Exception e) {
                System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Set the look and feel.
        initLookAndFeel();

        //Create and set up the window.
        JFrame frame = new JFrame("Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Converter converter = new Converter();
        converter.mainPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(converter.mainPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

}


/*
 * A 1.4 class used by the Converter example.
 */
class ConversionPanel extends JPanel implements ActionListener, ChangeListener, PropertyChangeListener {
    JFormattedTextField textField;
    JComboBox unitChooser;
    JSlider slider;
    ConverterRangeModel sliderModel;
    Converter controller;
    Unit[] units;
    String title;
    NumberFormat numberFormat;

    final static boolean MULTICOLORED = false;
    final static int MAX = 10000;

    ConversionPanel(Converter myController, String myTitle,
                    Unit[] myUnits,
                    ConverterRangeModel myModel) {
        if (MULTICOLORED) {
            setOpaque(true);
            setBackground(new Color(0, 255, 255));
        }
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(myTitle),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        //Save arguments in instance variables.
        controller = myController;
        units = myUnits;
        title = myTitle;
        sliderModel = myModel;

        //Create the text field format, and then the text field.
        numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);//seems to be a no-op --
        //aha -- it changes the value property but doesn't cause the result to
        //be parsed (that happens on focus loss/return, I think).
        //
        textField = new JFormattedTextField(formatter);
        textField.setColumns(10);
        textField.setValue(new Double(sliderModel.getDoubleValue()));
        textField.addPropertyChangeListener(this);

        //Add the combo box.
        unitChooser = new JComboBox();
        for (int i = 0; i < units.length; i++) { //Populate it.
            unitChooser.addItem(units[i].description);
        }
        unitChooser.setSelectedIndex(0);
        sliderModel.setMultiplier(units[0].multiplier);
        unitChooser.addActionListener(this);

        //Add the slider.
        slider = new JSlider(sliderModel);
        sliderModel.addChangeListener(this);

        //Make the text field/slider group a fixed size
        //to make stacked ConversionPanels nicely aligned.
        JPanel unitGroup = new JPanel() {
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            public Dimension getPreferredSize() {
                return new Dimension(150, super.getPreferredSize().height);
            }

            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        unitGroup.setLayout(new BoxLayout(unitGroup, BoxLayout.PAGE_AXIS));
        if (MULTICOLORED) {
            unitGroup.setOpaque(true);
            unitGroup.setBackground(new Color(0, 0, 255));
        }
        unitGroup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        unitGroup.add(textField);
        unitGroup.add(slider);

        //Create a subpanel so the combo box isn't too tall
        //and is sufficiently wide.
        JPanel chooserPanel = new JPanel();
        chooserPanel.setLayout(new BoxLayout(chooserPanel, BoxLayout.PAGE_AXIS));
        if (MULTICOLORED) {
            chooserPanel.setOpaque(true);
            chooserPanel.setBackground(new Color(255, 0, 255));
        }
        chooserPanel.add(unitChooser);
        chooserPanel.add(Box.createHorizontalStrut(100));

        //Put everything together.
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(unitGroup);
        add(chooserPanel);
        unitGroup.setAlignmentY(TOP_ALIGNMENT);
        chooserPanel.setAlignmentY(TOP_ALIGNMENT);
    }

    //Don't allow this panel to get taller than its preferred size.
    //BoxLayout pays attention to maximum size, though most layout managers don't.
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }

    /**
     * Returns the multiplier (units/meter) for the currently selected unit of measurement.
     */
    public double getMultiplier() {
        return sliderModel.getMultiplier();
    }

    public double getValue() {
        return sliderModel.getDoubleValue();
    }

    /**
     * Updates the text field when the main data model is updated.
     */
    public void stateChanged(ChangeEvent e) {
        int min = sliderModel.getMinimum();
        int max = sliderModel.getMaximum();
        double value = sliderModel.getDoubleValue();
        NumberFormatter formatter = (NumberFormatter) textField.getFormatter();

        formatter.setMinimum(new Double(min));
        formatter.setMaximum(new Double(max));
        textField.setValue(new Double(value));
    }

    /**
     * Responds to the user choosing a new unit from the combo box.
     */
    public void actionPerformed(ActionEvent e) {
        //Combo box event. Set new maximums for the sliders.
        int i = unitChooser.getSelectedIndex();
        sliderModel.setMultiplier(units[i].multiplier);
        controller.resetMaxValues(false);
    }

    /**
     * Detects when the value of the text field (not necessarily the same number as you'd get from getText) changes.
     */
    public void propertyChange(PropertyChangeEvent e) {
        if ("value".equals(e.getPropertyName())) {
            Number value = (Number) e.getNewValue();
            sliderModel.setDoubleValue(value.doubleValue());
        }
    }
}


/*
 * Works in 1.1+Swing, 1.4, and all releases in between.
 * Used by the Converter example.
 */

/**
 * Implements a model whose data is actually in another model (the "source model").
 * The follower model adjusts the values obtained
 * from the source model (or set in the follower model) to be in a different unit of measure.
 */
class FollowerRangeModel extends ConverterRangeModel implements ChangeListener {
    ConverterRangeModel sourceModel; //the real model

    /**
     * Creates a FollowerRangeModel that gets its state from sourceModel.
     */
    public FollowerRangeModel(ConverterRangeModel sourceModel) {
        this.sourceModel = sourceModel;
        sourceModel.addChangeListener(this);
    }

    //The only method in the ChangeListener interface.
    public void stateChanged(ChangeEvent e) {
        fireStateChanged();
    }

    public int getMaximum() {
        int modelMax = sourceModel.getMaximum();
        double multiplyBy = sourceModel.getMultiplier() / this.getMultiplier();
        return (int) (modelMax * multiplyBy);
    }

    public void setMaximum(int newMaximum) {
        sourceModel.setMaximum((int) (newMaximum * (this.getMultiplier() / sourceModel.getMultiplier())));
    }

    public int getValue() {
        return (int) getDoubleValue();
    }

    public void setValue(int newValue) {
        setDoubleValue((double) newValue);
    }

    public double getDoubleValue() {
        return sourceModel.getDoubleValue() * sourceModel.getMultiplier() / this.getMultiplier();
    }

    public void setDoubleValue(double newValue) {
        sourceModel.setDoubleValue(newValue * this.getMultiplier() / sourceModel.getMultiplier());
    }

    public int getExtent() {
        return super.getExtent();
    }

    public void setExtent(int newExtent) {
        super.setExtent(newExtent);
    }

    public void setRangeProperties(int value,
                                   int extent,
                                   int min,
                                   int max,
                                   boolean adjusting) {
        double multiplyBy = this.getMultiplier() / sourceModel.getMultiplier();
        sourceModel.setRangeProperties(value * multiplyBy,
                extent, min,
                (int) (max * multiplyBy),
                adjusting);
    }
}


/*
 * Works in 1.1+Swing, 1.4, and all releases in between.
 * Used by the Converter example.
 */
class Unit {
    String description;
    double multiplier;

    Unit(String description, double multiplier) {
        super();
        this.description = description;
        this.multiplier = multiplier;
    }

    public String toString() {
        String s = "Meters/" + description + " = " + multiplier;
        return s;
    }
}
