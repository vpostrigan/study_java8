package advanced_java2_deitel.chapter26_soap.fig29_05;

// Java core packages

import java.awt.*;

// Java extension packages
import javax.swing.*;

// WeatherCellRenderer.java
// WeatherCellRenderer is a custom ListCellRenderer for WeatherBeans in a JList.
public class WeatherCellRenderer extends DefaultListCellRenderer {

    // returns a WeatherItem object that displays city's weather
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean focus) {
        return new WeatherItem((WeatherBean) value);
    }

}
