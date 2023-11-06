package advanced_java2_deitel.chapter25_jms.fig16_12;

// Java core packages

import advanced_java2_deitel.chapter13_rmi.weather.WeatherBean;
import advanced_java2_deitel.chapter13_rmi.weather.WeatherCellRenderer;
import advanced_java2_deitel.chapter13_rmi.weather.WeatherListModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Java extension packages
import javax.swing.*;

// WeatherDisplay.java
// WeatherDisplay extends JPanel to display results of client's request for weather conditions.
public class WeatherDisplay extends JPanel {

    // WeatherListModel and Map for storing WeatherBeans
    private WeatherListModel weatherListModel;
    private Map weatherItems;

    // WeatherDisplay constructor
    public WeatherDisplay() {
        setLayout(new BorderLayout());

        ImageIcon headerImage = new ImageIcon(WeatherDisplay.class.getResource("images/header.jpg"));
        add(new JLabel(headerImage), BorderLayout.NORTH);

        // use JList to display updated weather conditions for requested cities
        weatherListModel = new WeatherListModel();
        JList weatherJList = new JList(weatherListModel);
        weatherJList.setCellRenderer(new WeatherCellRenderer());

        add(new JScrollPane(weatherJList), BorderLayout.CENTER);

        // maintain WeatherBean items in HashMap
        weatherItems = new HashMap();
    }

    // add WeatherBean item to display
    public void addItem(WeatherBean weather) {
        String city = weather.getCityName();

        // check whether city is already in display
        if (weatherItems.containsKey(city)) {
            // if city is in Map, and therefore in display remove previous WeatherBean object
            WeatherBean previousWeather = (WeatherBean) weatherItems.remove(city);
            weatherListModel.remove(previousWeather);
        }

        // add WeatherBean to Map and WeatherListModel
        weatherListModel.add(weather);
        weatherItems.put(city, weather);
    }

    // clear all cities from display
    public void clearCities() {
        weatherItems.clear();
        weatherListModel.clear();
    }

}
