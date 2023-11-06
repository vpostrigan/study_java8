package advanced_java2_deitel.chapter26_soap.fig29_01;

// Fig. 29.1: SimpleService.java
// Implementation for the requested method on the server
public class SimpleService {

    public String getWelcome(String message) throws Exception {
        String text = "Welcome to SOAP!\nHere is your message: " + message;
        return text;   // response
    }

}

