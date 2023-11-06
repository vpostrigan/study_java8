package advanced_java2_deitel.chapter13_rmi.weather;

// Java core packages

import java.rmi.*;
import java.util.*;

// WeatherService.java
// WeatherService interface declares a method for obtaining wether information.
public interface WeatherService extends Remote {

    // obtain Vector of WeatherBean objects from server
    public List<WeatherBean> getWeatherInformation() throws RemoteException;

}