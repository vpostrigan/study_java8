// WeatherBean.java
// WeatherBean maintains weather information for one city.
package advanced_java2_deitel.chapter13_rmi.weather;

// Java core packages
import java.io.*;
import java.net.*;
import java.util.*;

// Java extension packages
import javax.swing.*;

public class WeatherBean implements Serializable {

    private String cityName;         // name of city
    private String temperature;      // city's temperature
    private String description;      // weather description
    private ImageIcon image;         // weather image

    private static Properties imageNames;

    // initialize imageNames when class WeatherInfo is loaded into memory
    static {
        imageNames = new Properties(); // create properties table

        // load weather descriptions and image names from properties file
        try {
            // obtain URL for properties file
            URL url = WeatherBean.class.getResource("imagenames.properties");

            // load properties file contents
            imageNames.load(new FileInputStream(url.getFile()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // WeatherBean constructor
    public WeatherBean(String city, String weatherDescription, String cityTemperature) {
        cityName = city;
        temperature = cityTemperature;
        description = weatherDescription.trim();

        URL url = WeatherBean.class.getResource("images/" +
                imageNames.getProperty(description, "noinfo.jpg"));

        // get weather image name or noinfo.jpg if weather description not found
        image = new ImageIcon(url);
    }

    // get city name
    public String getCityName() {
        return cityName;
    }

    // get temperature
    public String getTemperature() {
        return temperature;
    }

    // get weather description
    public String getDescription() {
        return description;
    }

    // get weather image
    public ImageIcon getImage() {
        return image;
    }
}
