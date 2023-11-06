package advanced_java2_deitel.chapter4_graphic;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;

public class ControlPanel extends JPanel {
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;

    private JCheckBox textureCheckBox;

    private Java3DWorld java3DWorld;

    public ControlPanel(Java3DWorld tempJ3DWorld) {
        java3DWorld = tempJ3DWorld;
        JPanel instructionPanel = new JPanel();

        TitledBorder titledBorder = new TitledBorder("Transformation Instructions");
        titledBorder.setTitleJustification(TitledBorder.CENTER);

        instructionPanel.setBorder(titledBorder);

        JLabel rotationInstructions = new JLabel(
                "Rotation: Left Mouse Button", SwingConstants.CENTER);
        JLabel translationInstructions = new JLabel(
                "Translation: Right Mouse Button", SwingConstants.CENTER);
        JLabel scalingInstructions = new JLabel(
                "Scale: Alt + Left Mouse Button", SwingConstants.CENTER);

        instructionPanel.add(rotationInstructions);
        instructionPanel.add(translationInstructions);
        instructionPanel.add(scalingInstructions);

        TitledBorder textureBorder = new TitledBorder("Texture Controls");
        textureBorder.setTitleJustification(TitledBorder.CENTER);

        textureCheckBox = new JCheckBox("Apply Texture Map to Image");
        textureCheckBox.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED)
                java3DWorld.updateTexture(true);
            else
                java3DWorld.updateTexture(false);
        });

        JPanel texturePanel = new JPanel();
        texturePanel.setBorder(textureBorder);
        texturePanel.add(textureCheckBox);

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        topPanel.add(instructionPanel);
        topPanel.add(texturePanel);

        TitledBorder colorBorder = new TitledBorder("Direct Lighting Color Controls");
        colorBorder.setTitleJustification(TitledBorder.CENTER);

        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        colorPanel.setBorder(colorBorder);

        JLabel redLabel = new JLabel("R");
        JLabel greenLabel = new JLabel("G");
        JLabel blueLabel = new JLabel("B");

        redSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 25);
        redSlider.setMajorTickSpacing(25);
        redSlider.setPaintTicks(true);

        greenSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 25);
        greenSlider.setMajorTickSpacing(25);
        greenSlider.setPaintTicks(true);

        blueSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 25);
        blueSlider.setMajorTickSpacing(25);
        blueSlider.setPaintTicks(true);

        ChangeListener slideListener = event -> {
            Color color = new Color(
                    redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
            java3DWorld.changeColor(color);
        };

        redSlider.addChangeListener(slideListener);
        greenSlider.addChangeListener(slideListener);
        blueSlider.addChangeListener(slideListener);

        colorPanel.add(redLabel);
        colorPanel.add(redSlider);

        colorPanel.add(greenLabel);
        colorPanel.add(greenSlider);

        colorPanel.add(blueLabel);
        colorPanel.add(blueSlider);

        java3DWorld.changeColor(new Color(
                redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));

        setLayout(new GridLayout(2, 1, 0, 20));

        add(topPanel);
        add(colorPanel);
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 150);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

}
