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
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

/*
 * TableFTFEditDemo.java requires one other file:
 *   IntegerEditor.java
 */

/**
 * This is exactly like TableDemo, except that it uses a custom cell editor to validate integer input.
 */
public class TableFTFEditDemo extends JPanel {
    private boolean DEBUG = false;

    public TableFTFEditDemo() {
        super(new GridLayout(1, 0));

        JTable table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up stricter input validation for the integer column.
        table.setDefaultEditor(Integer.class, new IntegerEditor(0, 100));

        //If we didn't want this editor to be used for other
        //Integer columns, we'd do this:
        //table.getColumnModel().getColumn(3).setCellEditor(new IntegerEditor(0, 100));

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
        private Object[][] data = {
                {"Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe", "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White", "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
        };

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"), rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value + " (an instance of " + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableFTFEditDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableFTFEditDemo newContentPane = new TableFTFEditDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}


/*
 * IntegerEditor is used by TableFTFEditDemo.java.
 */

/**
 * Implements a cell editor that uses a formatted text field to edit Integer values.
 */
class IntegerEditor extends DefaultCellEditor {
    JFormattedTextField ftf;
    NumberFormat integerFormat;
    private Integer minimum, maximum;
    private boolean DEBUG = false;

    public IntegerEditor(int min, int max) {
        super(new JFormattedTextField());
        ftf = (JFormattedTextField) getComponent();
        minimum = new Integer(min);
        maximum = new Integer(max);

        //Set up the editor for the integer cells.
        integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter intFormatter = new NumberFormatter(integerFormat);
        intFormatter.setFormat(integerFormat);
        intFormatter.setMinimum(minimum);
        intFormatter.setMaximum(maximum);

        ftf.setFormatterFactory(
                new DefaultFormatterFactory(intFormatter));
        ftf.setValue(minimum);
        ftf.setHorizontalAlignment(JTextField.TRAILING);
        ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);

        //React when the user presses Enter while the editor is
        //active.  (Tab is handled as specified by
        //JFormattedTextField's focusLostBehavior property.)
        ftf.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "check");
        ftf.getActionMap().put("check", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!ftf.isEditValid()) { //The text is invalid.
                    if (userSaysRevert()) { //reverted
                        ftf.postActionEvent(); //inform the editor
                    }
                } else try {              //The text is valid,
                    ftf.commitEdit();     //so use it.
                    ftf.postActionEvent(); //stop editing
                } catch (ParseException exc) {
                }
            }
        });
    }

    //Override to invoke setValue on the formatted text field.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value, boolean isSelected,
                                                 int row, int column) {
        JFormattedTextField ftf =
                (JFormattedTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        ftf.setValue(value);
        return ftf;
    }

    //Override to ensure that the value remains an Integer.
    public Object getCellEditorValue() {
        JFormattedTextField ftf = (JFormattedTextField) getComponent();
        Object o = ftf.getValue();
        if (o instanceof Integer) {
            return o;
        } else if (o instanceof Number) {
            return new Integer(((Number) o).intValue());
        } else {
            if (DEBUG) {
                System.out.println("getCellEditorValue: o isn't a Number");
            }
            try {
                return integerFormat.parseObject(o.toString());
            } catch (ParseException exc) {
                System.err.println("getCellEditorValue: can't parse o: " + o);
                return null;
            }
        }
    }

    //Override to check whether the edit is valid, setting the value if it is and complaining if it isn't.
    //If it's OK for the editor to go away, we need to invoke the superclass's version of this method so that everything gets cleaned up.
    public boolean stopCellEditing() {
        JFormattedTextField ftf = (JFormattedTextField) getComponent();
        if (ftf.isEditValid()) {
            try {
                ftf.commitEdit();
            } catch (ParseException exc) {
            }

        } else { //text is invalid
            if (!userSaysRevert()) { //user wants to edit
                return false; //don't let the editor go away
            }
        }
        return super.stopCellEditing();
    }

    /**
     * Lets the user know that the text they entered is
     * bad. Returns true if the user elects to revert to
     * the last good value.  Otherwise, returns false, indicating that the user wants to continue editing.
     */
    protected boolean userSaysRevert() {
        Toolkit.getDefaultToolkit().beep();
        ftf.selectAll();
        Object[] options = {"Edit", "Revert"};
        int answer = JOptionPane.showOptionDialog(
                SwingUtilities.getWindowAncestor(ftf),
                "The value must be an integer between " + minimum + " and " + maximum + ".\n"
                        + "You can either continue editing " + "or revert to the last valid value.",
                "Invalid Text Entered",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[1]);

        if (answer == 1) { //Revert!
            ftf.setValue(ftf.getValue());
            return true;
        }
        return false;
    }

}
