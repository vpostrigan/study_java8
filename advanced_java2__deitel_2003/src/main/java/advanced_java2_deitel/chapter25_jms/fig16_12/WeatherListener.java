package advanced_java2_deitel.chapter25_jms.fig16_12;

// Java extension packages

import advanced_java2_deitel.chapter13_rmi.weather.WeatherBean;

import javax.jms.*;
import javax.swing.*;

// WeatherListener.java
// WeatherListener is the MessageListener for a subscription to the Weather topic.
// It implements the specified onMessage method to update the GUI with the corresponding city's weather.
public class WeatherListener implements MessageListener {

    private WeatherDisplay weatherDisplay;

    // WeatherListener constructor
    public WeatherListener(WeatherDisplay display) {
        weatherDisplay = display;
    }

    // receive new message
    public void onMessage(Message message) {
        // retrieve and process message
        try {
            // ensure Message is an ObjectMessage
            if (message instanceof ObjectMessage) {

                // get WeatherBean from ObjectMessage
                ObjectMessage objectMessage = (ObjectMessage) message;
                WeatherBean weatherBean = (WeatherBean) objectMessage.getObject();

                // add WeatherBean to display
                weatherDisplay.addItem(weatherBean);
            } else {
                System.out.println("Expected ObjectMessage, but received " + message.getClass().getName());
            }
        } catch (JMSException jmsException) {
            // process JMS exception from message
            jmsException.printStackTrace();
        }
    }

}
