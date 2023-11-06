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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

/*
 * ColorChooserDemo2.java requires these files:
 *   CrayonPanel.java
 *   images/red.gif
 *   images/yellow.gif
 *   images/green.gif
 *   images/blue.gif
 */
public class ColorChooserDemo2 extends JPanel implements ActionListener, ChangeListener {
    public JLabel banner;
    public JColorChooser tcc;

    public ColorChooserDemo2() {
        super(new BorderLayout());

        //Set up banner to use as custom preview panel
        banner = new JLabel("Welcome to the Tutorial Zone!", JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));

        //Set up color chooser for setting background color
        JPanel panel = new JPanel(); //use FlowLayout
        JButton bcc = new JButton("Show Color Chooser...");
        bcc.addActionListener(this);
        panel.add(bcc);
        panel.setBorder(BorderFactory.createTitledBorder("Choose Background Color"));

        //Set up color chooser for setting text color
        tcc = new JColorChooser();
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));

        //Remove the preview panel
        tcc.setPreviewPanel(new JPanel());

        //Override the chooser panels with our own
        AbstractColorChooserPanel panels[] = {new CrayonPanel()};
        tcc.setChooserPanels(panels);
        tcc.setColor(banner.getForeground());

        add(bannerPanel, BorderLayout.PAGE_START);
        add(panel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(
                ColorChooserDemo2.this,
                "Choose Background Color",
                banner.getBackground());
        if (newColor != null) {
            banner.setBackground(newColor);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        banner.setForeground(newColor);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ColorChooserDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooserDemo2();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

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

/* Used by ColorChooserDemo2.java. */
class CrayonPanel extends AbstractColorChooserPanel implements ActionListener {
    JToggleButton redCrayon;
    JToggleButton yellowCrayon;
    JToggleButton greenCrayon;
    JToggleButton blueCrayon;

    public void updateChooser() {
        Color color = getColorFromModel();
        if (Color.red.equals(color)) {
            redCrayon.setSelected(true);
        } else if (Color.yellow.equals(color)) {
            yellowCrayon.setSelected(true);
        } else if (Color.green.equals(color)) {
            greenCrayon.setSelected(true);
        } else if (Color.blue.equals(color)) {
            blueCrayon.setSelected(true);
        }
    }

    protected JToggleButton createCrayon(String name, Border normalBorder) {
        JToggleButton crayon = new JToggleButton();
        crayon.setActionCommand(name);
        crayon.addActionListener(this);

        //Set the image or, if that's invalid, equivalent text.
        ImageIcon icon = createImageIcon("images/" + name + ".gif");
        if (icon != null) {
            crayon.setIcon(icon);
            crayon.setToolTipText("The " + name + " crayon");
            crayon.setBorder(normalBorder);
        } else {
            crayon.setText("Image not found. This is the " + name + " button.");
            crayon.setFont(crayon.getFont().deriveFont(Font.ITALIC));
            crayon.setHorizontalAlignment(JButton.HORIZONTAL);
            crayon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        return crayon;
    }

    protected void buildChooser() {
        setLayout(new GridLayout(0, 1));

        ButtonGroup boxOfCrayons = new ButtonGroup();
        Border border = BorderFactory.createEmptyBorder(4, 4, 4, 4);

        redCrayon = createCrayon("red", border);
        boxOfCrayons.add(redCrayon);
        add(redCrayon);

        yellowCrayon = createCrayon("yellow", border);
        boxOfCrayons.add(yellowCrayon);
        add(yellowCrayon);

        greenCrayon = createCrayon("green", border);
        boxOfCrayons.add(greenCrayon);
        add(greenCrayon);

        blueCrayon = createCrayon("blue", border);
        boxOfCrayons.add(blueCrayon);
        add(blueCrayon);
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CrayonPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void actionPerformed(ActionEvent e) {
        Color newColor = null;
        String command = ((JToggleButton) e.getSource()).getActionCommand();
        if ("green".equals(command))
            newColor = Color.green;
        else if ("red".equals(command))
            newColor = Color.red;
        else if ("yellow".equals(command))
            newColor = Color.yellow;
        else if ("blue".equals(command))
            newColor = Color.blue;
        getColorSelectionModel().setSelectedColor(newColor);
    }

    public String getDisplayName() {
        return "Crayons";
    }

    public Icon getSmallDisplayIcon() {
        return null;
    }

    public Icon getLargeDisplayIcon() {
        return null;
    }
}
