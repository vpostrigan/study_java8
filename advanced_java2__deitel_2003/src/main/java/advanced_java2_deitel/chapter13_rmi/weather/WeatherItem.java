package advanced_java2_deitel.chapter13_rmi.weather;

// Java core packages
import java.awt.*;
import java.net.*;

// Java extension packages
import javax.swing.*;

// WeatherItem.java
// WeatherItem displays a city's weather information in a JPanel.
public class WeatherItem extends JPanel {

    private WeatherBean weatherBean;  // weather information

    // background ImageIcon
    private static ImageIcon backgroundImage;

    // static initializer block loads background image when class
    // WeatherItem is loaded into memory
    static {
        // get URL for background image
        URL url = WeatherItem.class.getResource("images/back.jpg");

        // background image for each city's weather info
        backgroundImage = new ImageIcon(url);
    }

    // initialize a WeatherItem
    public WeatherItem(WeatherBean bean) {
        weatherBean = bean;
    }

    // display information for city's weather
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw background
        backgroundImage.paintIcon(this, g, 0, 0);

        // set font and drawing color,
        // then display city name and temperature
        Font font = new Font("SansSerif", Font.BOLD, 12);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(weatherBean.getCityName(), 10, 19);
        g.drawString(weatherBean.getTemperature(), 130, 19);

        // display weather image
        weatherBean.getImage().paintIcon(this, g, 253, 1);
    }

    // make WeatherItem's preferred size the width and height of the background image
    public Dimension getPreferredSize() {
        return new Dimension(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
    }

}