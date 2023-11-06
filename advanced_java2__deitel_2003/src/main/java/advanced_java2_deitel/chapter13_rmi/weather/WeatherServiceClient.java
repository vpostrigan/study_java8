package advanced_java2_deitel.chapter13_rmi.weather;

// Java core packages

import java.rmi.*;
import java.util.*;

// Java extension packages
import javax.swing.*;

// WeatherServiceClient.java
// WeatherServiceClient uses the WeatherService remote object
// to retrieve weather information.
public class WeatherServiceClient extends JFrame {

    // WeatherServiceClient constructor
    public WeatherServiceClient(String server) {
        super("RMI WeatherService Client");

        // connect to server and get weather information
        try {
            // name of remote server object bound to rmi registry
            String remoteName = "rmi://" + server + "/WeatherService";

            // lookup WeatherServiceImpl remote object
            WeatherService weatherService = (WeatherService) Naming.lookup(remoteName);

            // get weather information from server
            List<WeatherBean> weatherInformation = new ArrayList<>(weatherService.getWeatherInformation());

            // create WeatherListModel for weather information
            ListModel weatherListModel = new WeatherListModel(weatherInformation);

            // create JList, set ListCellRenderer and add to layout
            JList weatherJList = new JList(weatherListModel);
            weatherJList.setCellRenderer(new WeatherCellRenderer());
            getContentPane().add(new JScrollPane(weatherJList));

        } catch (ConnectException connectionException) {
            System.err.println("Connection to server failed. " +
                    "Server may be temporarily unavailable.");

            connectionException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // execute WeatherServiceClient
    public static void main(String[] args) {
        WeatherServiceClient client;

        // if no sever IP address or host name specified,
        // use "localhost"; otherwise use specified host
        if (args.length == 0)
            client = new WeatherServiceClient("localhost");
        else
            client = new WeatherServiceClient(args[0]);

        // configure and display application window
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.pack();
        client.setResizable(false);
        client.setVisible(true);
    }

}

