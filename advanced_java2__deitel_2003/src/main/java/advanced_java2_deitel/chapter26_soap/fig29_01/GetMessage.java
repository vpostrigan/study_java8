package advanced_java2_deitel.chapter26_soap.fig29_01;

// import Java packages

import java.io.*;
import java.net.*;
import java.util.*;

// import third-party packages
import org.apache.soap.*;
import org.apache.soap.rpc.*;

// Fig. 29.4 : GetMessage.java
// Program that makes a SOAP RPC
public class GetMessage {

    // main method
    public static void main(String[] args) {
        String encodingStyleURI = Constants.NS_URI_SOAP_ENC;
        String message;

        if (args.length != 0)
            message = args[0];
        else
            message = "Thanks!";

        // attempt SOAP remote procedure call
        try {
            URL url = new URL("http://localhost:8080/soap/servlet/rpcrouter");

            // build call
            Call remoteMethod = new Call();
            remoteMethod.setTargetObjectURI("urn:xml-simple-message");
            // set name of remote method to be invoked
            remoteMethod.setMethodName("getWelcome");
            remoteMethod.setEncodingStyleURI(encodingStyleURI);

            // set parameters for remote method
            Vector parameters = new Vector();
            parameters.addElement(new Parameter("message", String.class, message, null));

            remoteMethod.setParams(parameters);

            // invoke remote method
            Response response = remoteMethod.invoke(url, "");

            // get response
            if (response.generatedFault()) {
                Fault fault = response.getFault();

                System.err.println("CALL FAILED:\nFault Code = " + fault.getFaultCode() +
                        "\nFault String = " + fault.getFaultString());
            } else {
                Parameter result = response.getReturnValue();

                // display result of call
                System.out.println(result.getValue());
            }
        } catch (MalformedURLException malformedURLException) {
            // catch malformed URL exception
            malformedURLException.printStackTrace();
            System.exit(1);
        } catch (SOAPException soapException) {
            // catch SOAPException
            System.err.println("Error message: " + soapException.getMessage());
            System.exit(1);
        }
    }

}
