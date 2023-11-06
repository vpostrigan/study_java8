package advanced_java2_deitel.chapter7_security.jsse__secure_socket_extension;

import java.io.*;
import javax.net.ssl.*;

public class LoginServer {
    private static final String CORRECT_USER_NAME = "Java";
    private static final String CORRECT_PASSWORD = "HowToProgram";

    private SSLServerSocket serverSocket;

    public LoginServer() throws Exception {
        // SSLServerSocketFactory for building SSLServerSockets
        SSLServerSocketFactory socketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        // create SSLServerSocket on specified port
        serverSocket = (SSLServerSocket) socketFactory.createServerSocket(7070);
    }

    // start server and listen for clients
    private void runServer() {
        // perpetually listen for clients
        while (true) {
            System.err.println("Waiting for connection...");
            try (
                    // create new SSLSocket for client
                    SSLSocket socket = (SSLSocket) serverSocket.accept();

                    // open BufferedReader for reading data from client
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    // open PrintWriter for writing data to client
                    PrintWriter output = new PrintWriter(
                            new OutputStreamWriter(socket.getOutputStream()));
            ) {
                String userName = input.readLine();
                String password = input.readLine();

                if (userName.equals(CORRECT_USER_NAME) && password.equals(CORRECT_PASSWORD)) {
                    output.println("Welcome, " + userName);
                } else {
                    output.println("Login Failed. " + userName + " " + password);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        /*
         * TO Use:
         * 1) создать новый сертификат и хранилище ключей
         * C:\Users\admin>keytool -genkey -keystore SSLStore -alias SSLCertificate
         * Enter keystore password:
         * Re-enter new password:
         * What is your first and last name?
         *   [Unknown]:
         * What is the name of your organizational unit?
         *   [Unknown]:
         * What is the name of your organization?
         *   [Unknown]:
         * What is the name of your City or Locality?
         *   [Unknown]:
         * What is the name of your State or Province?
         *   [Unknown]:
         * What is the two-letter country code for this unit?
         *   [Unknown]:
         *
         * Enter key password for <SSLCertificate>
         *         (RETURN if same as keystore password):
         * Re-enter new password:
         */

        // for: $ java -Djavax.net.ssl.keyStore=SSLStore -Djavax.net.ssl.keyStorePassword=password LoginServer
        System.setProperty("javax.net.ssl.keyStore", "SSLStore");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");

        LoginServer server = new LoginServer();
        server.runServer();
    }

}
