package advanced_java2_deitel.chapter7_security.jsse__secure_socket_extension;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class LoginClient {

    public LoginClient() {
        // open SSLSocket connection to server and send login
        try {
            // obtain SSLSocketFactory for creating SSLSockets
            SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try (
                    // create SSLSocket from factory
                    SSLSocket socket = (SSLSocket) socketFactory.createSocket("localhost", 7070);

                    // create PrintWriter for sending login to server
                    PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                    // create BufferedReader for reading server response
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {

                // prompt user for user name
                String userName = JOptionPane.showInputDialog(null, "Enter User Name:");
                // prompt user for password
                String password = JOptionPane.showInputDialog(null, "Enter Password:");

                // send user name to server
                output.println(userName);
                // send password to server
                output.println(password);
                output.flush();

                // read response from server
                String response = input.readLine();
                // display response to user
                JOptionPane.showMessageDialog(null, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        System.setProperty("javax.net.ssl.trustStore", "SSLStore");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        new LoginClient();
    }

}
